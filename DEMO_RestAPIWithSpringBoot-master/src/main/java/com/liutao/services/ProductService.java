package com.liutao.services;

import com.liutao.Interfaces.IProductService;
import com.liutao.domain.ProductEntity;
import com.liutao.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<ProductEntity> listAllProducts() {
        logger.debug("listAllProducts called");
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Integer id) {
        logger.debug("getProductById called");
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        logger.debug("saveProduct called");
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        logger.debug("deleteProduct called");
        productRepository.deleteById(id);
    }
}