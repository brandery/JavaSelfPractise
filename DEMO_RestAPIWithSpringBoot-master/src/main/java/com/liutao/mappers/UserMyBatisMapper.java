package com.liutao.mappers;

import com.liutao.domain.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
*Here we use a @Mapper to annotate an interface named StudentMapper, which has a method findById,
* this method is annotated by @Select with a sql statement, just like traditional MyBatis mapper files,
 * but when you use springboot, you don’t need xml now.
 */
@Mapper
public interface UserMyBatisMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    UserEntity findById(@Param("id") int id);
    @Select("SELECT * FROM user ")
    List<UserEntity> findAll();
    @Insert(" INSERT INTO user(number, name, phone) VALUES(#{number}, #{name}, #{phone})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(UserEntity entity);
}