package com.liutao.controllers;

import com.liutao.Interfaces.IProductService;
import com.liutao.Interfaces.IProductTemplate;
import com.liutao.domain.ProductEntity;
import com.liutao.util.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/product", produces = "application/json")
@Api(value = "onlinestore", description = "Operations pertaining to products in Online Store")
public class ProductController
{
    private IProductService productService;
    private IProductTemplate productTemplate;

    @ApiOperation(value = "View a list of available products", response = Iterable.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(Model model)
    {
        var productList = productService.listAllProducts();
        log.info("productList: ", productList);

        return ResponseBuilder.buildOkDataResponse(productList);
    }

    @ApiOperation(value = "Search a product with an ID", response = ProductEntity.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public Object showProduct(@PathVariable Integer id, Model model)
    {
        var product = productService.getProductById(id);
        return ResponseBuilder.buildOkDataResponse(product);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object saveProduct(@RequestBody ProductEntity product)
    {
        log.info("add:"+String.valueOf(product));
        productService.saveProduct(product);
        return ResponseBuilder.buildOkDataResponse(product);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Object updateProduct(@PathVariable Integer id, @RequestBody ProductEntity product)
    {
        ProductEntity storedProduct = productService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());
        productService.saveProduct(storedProduct);

        return product;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id)
    {
        log.warn("delete id:" + id );
        productService.deleteProduct(id);
        return ResponseBuilder.buildOkDataResponse(String.format("id:%d delete successfully!",id));
    }

    @GetMapping(value = "/template/all")
    public Object restTemplate()
    {
        var ret = productTemplate.queryAll();
        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @GetMapping(value="/template/{id}")
    public Object queryByIdWithTemplate(@PathVariable Integer id) throws Exception
    {
        if(id < 1){
            log.error("query id invalid:"+String.valueOf(id));
            throw new Exception("test for global exception catpture....");
        }
        var ret = productTemplate.queryById(id);

        return ResponseBuilder.buildOkDataResponse(ret);
    }

    @PostMapping(value="/template/add")
    public Object queryByIdWithTemplate(@RequestBody ProductEntity entity) throws Exception
    {
        log.info("add:"+String.valueOf(entity));
        var ret = productTemplate.insertWithFlush(entity);

        var res = ResponseBuilder.buildOkDataResponse(ret);
        return res;
    }
}