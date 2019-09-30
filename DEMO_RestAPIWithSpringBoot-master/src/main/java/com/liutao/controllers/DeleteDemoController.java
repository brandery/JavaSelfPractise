package com.liutao.controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户API
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */
@RestController
@Api(value = "API of user", description = "this is a demo for http delete")
@RequestMapping(value = "/api-demo/", produces = "application/json")
public class DeleteDemoController {

    private Logger logger = LoggerFactory.getLogger(DeleteDemoController.class);


    /**
     * 删除用户信息
     * @return
     */
    @ApiOperation(value = "删除用户信息", response = int.class)
    @DeleteMapping("user")
    public int getUser(
            @RequestParam(value = "name",defaultValue = "liutao")String name,
            @RequestParam("age") int age) {
        logger.debug("name:"+name+",age:"+age);
        return 1;
    }

}
