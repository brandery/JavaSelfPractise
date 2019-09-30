package com.liutao.Interfaces;

import com.liutao.domain.ProductEntity;

public interface IProductService {
    Iterable<ProductEntity> listAllProducts();

    ProductEntity getProductById(Integer id);

    ProductEntity saveProduct(ProductEntity product);

    void deleteProduct(Integer id);
}
