package com.liutao.Interfaces;


import com.liutao.domain.UserEntity;

import java.util.List;

public interface IUserTemplate {
    int insert(UserEntity entity) throws Exception;
    UserEntity insertWithFlush(UserEntity entity) throws Exception;
    UserEntity queryById(int id);
    List<UserEntity> queryAll();
}
