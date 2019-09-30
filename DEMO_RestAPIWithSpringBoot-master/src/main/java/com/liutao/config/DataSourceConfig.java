package com.liutao.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{
    @Bean(name = "basicinfodbDataSource")
    @Primary
    @ConfigurationProperties(prefix = "basicinfo.datasource")
    public DataSource dataSourceMydb()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "springdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSourceSpring()
    {
        val dataSrc = DataSourceBuilder.create().build();

        return dataSrc;
    }

    @Bean(name = "basicinfodbJdbcTemplate")
    @Primary
    public NamedParameterJdbcTemplate firstJdbcTemplate(@Qualifier("basicinfodbDataSource")DataSource dataSource)
    {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "springdbJdbcTemplate")
    public NamedParameterJdbcTemplate secondJdbcTemplate(@Qualifier("springdbDataSource") DataSource dataSource)
    {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
