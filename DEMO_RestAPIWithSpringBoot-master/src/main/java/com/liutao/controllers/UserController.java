package com.liutao.controllers;

import com.liutao.Interfaces.IUserTemplate;
import com.liutao.domain.UserEntity;
import com.liutao.mappers.UserMyBatisMapper;
import com.liutao.repositories.UserRepository;
import com.liutao.util.ResponseBuilder;
import com.liutao.util.RestfulDataResponse;
import com.wordnik.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
@Api(value = "用户信息相关", description = "用户信息相关description")
public class UserController
{
    private UserRepository userRepo;
    private IUserTemplate userTemplate;
    private UserMyBatisMapper userMyBatis;

    @RequestMapping(value = {"/hibernate/{id}"}, method={RequestMethod.GET})
    public Object getUserUsingHibernate(@PathVariable int id)
    {
        var ret = userRepo.getOne(id);
        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @RequestMapping(value = {"/hibernate/add"}, method={RequestMethod.POST})
    public Object getUserUsingHibernate(@RequestBody UserEntity entity) throws Exception
    {
        val ret = userRepo.saveAndFlush(entity);
        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @RequestMapping(value = {"/hibernate/all"}, method={RequestMethod.GET})
    public Object getUserAllUsingHibernate()
    {
        var ret = userRepo.findAll();
        return ResponseBuilder.buildOkDataResponse(ret);
    }


    @RequestMapping(value = {"/template/{id}"}, method={RequestMethod.GET})
    public UserEntity getUser(@PathVariable int id)
    {
        return  userTemplate.queryById(id);
    }

    @RequestMapping(value = {"/template/add"}, method={RequestMethod.POST})
    public Object getUserUsingJdbcTemplate(@RequestBody UserEntity entity) throws Exception
    {
        val ret = userTemplate.insertWithFlush(entity);

        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @RequestMapping(value = {"/template/all"}, method={RequestMethod.GET})
    public Object getUsersUsingJdbcTemplate() throws Exception
    {
        val ret = userTemplate.queryAll();

        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @RequestMapping(value = {"/mybatis/{id}"}, method={RequestMethod.GET})
    public Object getUserUsingMyBatis(@PathVariable int id) throws Exception
    {
        val ret = userMyBatis.findById(id);

        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @RequestMapping(value = {"/mybatis/all"}, method={RequestMethod.GET})
    public Object getUsersUsingMyBatis() throws Exception
    {
        val ret =  userMyBatis.findAll();

        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @RequestMapping(value = {"/mybatis/add"}, method={RequestMethod.POST})
    public RestfulDataResponse<UserEntity> getUserUsingMyBatis(@RequestBody UserEntity entity) throws Exception
    {
       int rows = userMyBatis.insert(entity);

        return ResponseBuilder.buildOkDataResponse(String.valueOf(rows), entity);
    }
}
