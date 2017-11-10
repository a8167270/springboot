package com.learn.spring.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumTypeHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {
    private Class<E> type;
    private E [] enums;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        this.type = type;
        this.enums = type.getEnumConstants();

        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum baseEnum, JdbcType jdbcType) throws SQLException {
        //BaseTypeHandler已经帮我们做了parameter的null判断
        ps.setObject(i,(String)baseEnum.getValue(), jdbcType.TYPE_CODE);
    }

    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        String i = resultSet.getString(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位PersonType子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        String i = resultSet.getString(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位PersonType子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public BaseEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        String i = callableStatement.getString(columnIndex);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位PersonType子类
            return locateEnumStatus(i);
        }
    }

    private BaseEnum locateEnumStatus(String value) {
        for(E e : enums) {
            if(e.getValue().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + value + ",请核对" + type.getSimpleName());
    }
}
