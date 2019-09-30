package com.liutao.config;

import com.liutao.domain.UserEntity;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory",transactionManagerRef = "userTransactionManager", basePackages = {"com.liutao.repositories"})
public class UserConfig
{
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("basicinfodbDataSource") DataSource dataSource )
    {
        val entityMFactoryBean = builder.dataSource(dataSource)
                .packages(UserEntity.class)
                .persistenceUnit("user")
                .build();

        return entityMFactoryBean;
    }

    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory)
    {
        return new JpaTransactionManager(userEntityManagerFactory);
    }
}
