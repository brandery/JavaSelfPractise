package com.liutao.services;


import com.liutao.Interfaces.IProductTemplate;
import com.liutao.domain.ProductEntity;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductTemplate extends TemplateBase implements IProductTemplate
{
    @Autowired
    protected void GetJdbcTemplate(@Qualifier("springdbJdbcTemplate")NamedParameterJdbcTemplate para_jdbcTemplate)
    {
        jtm = para_jdbcTemplate;
    }

    public ProductTemplate()
    {
        super("product");
    }

    @Override
    public Iterable<ProductEntity> queryAll()
    {
        val products = super.queryAll(ProductEntity.class);

        return products;
    }

    @Override
    public ProductEntity queryById(int id)
    {
        return (ProductEntity)super.queryById(ProductEntity.class, id);
    }

    public int insert(ProductEntity entity) throws Exception{
        int id = super.insert(entity, SqlType.Create);

        return id;
    }

    @Override
    public ProductEntity insertWithFlush(ProductEntity entity) throws Exception
    {
        ProductEntity ret = (ProductEntity)super.insertWithFlush(entity, SqlType.Create);

        return ret;
    }

}