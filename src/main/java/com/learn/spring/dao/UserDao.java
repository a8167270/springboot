package com.learn.spring.dao;

import com.learn.spring.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
@CacheConfig(cacheNames = {"userDao"})
public interface UserDao {

    @Cacheable(key = "#p0", condition = "#p0 > 0",unless = "#result.id == 0")
    User getDemoById(long id);

    @Cacheable(key = "'allDemo'", sync = true)
    List<User> getAllDemo();

    @CacheEvict(key = "'allDemo'", condition = "#result > 0")
    int insert(User user);

    @Caching(evict = {@CacheEvict(key = "'allDemo'"), @CacheEvict(key = "#p0")})
    void delete(long id);

    @CachePut(key = "#p0.id")
    int updateUser(User user);

    User getUserByName(String name);

    String getRoleByName(String username);
}
