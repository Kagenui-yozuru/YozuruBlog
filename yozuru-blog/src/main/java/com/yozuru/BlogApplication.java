package com.yozuru;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

/**
 * @author Yozuru
 */
@SpringBootApplication
@EnableSwagger2
@EnableCreateCacheAnnotation
@MapperScan("com.yozuru.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BlogApplication.class, args);
    }
}
