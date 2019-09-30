package com.liutao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="Product")
@Data
public class ProductEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated product ID")
    private Integer id;

    @Version
    @ApiModelProperty(notes = "The auto-generated version of the product")
    private Integer version;

    @ApiModelProperty(notes = "The application-specific product ID")
    private String productId;

    @ApiModelProperty(notes = "The product description")
    private String description;

    @ApiModelProperty(notes = "The image URL of the product")
    private String imageUrl;

    @ApiModelProperty(notes = "The price of the product", required = true)
    private BigDecimal price;

    @Column(name = "delflag", columnDefinition = "tinyint not null default 0")
    @JsonIgnore()
    private int delflag = 0;

    @Column(name = "updatetime", columnDefinition = "datetime not null default now()")
    @JsonIgnore()
    private LocalDateTime updatetime = LocalDateTime.now();

    @Column(name = "createtime", columnDefinition = "datetime not null default now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createtime = LocalDateTime.now();
}