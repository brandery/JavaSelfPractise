package com.liutao.Interfaces;

import com.liutao.domain.ProductEntity;

public interface IProductTemplate
{
    Iterable<ProductEntity> queryAll();
    ProductEntity queryById(int id);
    int insert(ProductEntity entity) throws Exception;
    ProductEntity insertWithFlush(ProductEntity entity) throws Exception;
}
