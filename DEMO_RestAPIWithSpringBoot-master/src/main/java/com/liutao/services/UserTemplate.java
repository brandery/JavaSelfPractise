package com.liutao.services;

import com.liutao.Interfaces.IUserTemplate;
import com.liutao.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTemplate extends TemplateBase implements IUserTemplate
{
    @Autowired
    protected void getNamedParameterJdbcTemplate(@Qualifier("basicinfodbJdbcTemplate") NamedParameterJdbcTemplate para_jdbcTemplate){
        jtm = para_jdbcTemplate;
    }
    static int instanceCount = 0;

    public UserTemplate()
    {
        super("user");
        System.out.println(String.format("UserTemplate constructor.......%d", ++instanceCount));
        //FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/db.properties");
    }

    // this is just for demo
    @Override
    protected void finalize() throws Throwable {
        System.out.println("UserTemplate is about to be destroyed.....");
        super.finalize();
    }

    @Override
    public List<UserEntity> queryAll()
    {
        return super.queryAll(UserEntity.class);
    }

    @Override
    public UserEntity queryById(int id)
    {
        return (UserEntity)super.queryById(UserEntity.class, id);
    }

    @Override
    public int insert(UserEntity entity) throws Exception
    {
        int id = super.insert(entity, SqlType.Create);

        return id;
    }

    @Override
    public UserEntity insertWithFlush(UserEntity entity) throws Exception
    {
        UserEntity ret = (UserEntity)super.insertWithFlush(entity, SqlType.Create);

        return ret;
    }
}
