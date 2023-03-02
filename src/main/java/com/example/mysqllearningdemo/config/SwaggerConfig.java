package com.example.mysqllearningdemo.config;

import com.example.mysqllearningdemo.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
//Swagger的配置文件
public class SwaggerConfig {

    //配置Swagger的信息 apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("谷歌总裁办公室","none","1482089698@qq.com");
        return new ApiInfo(
                "MySQL Learning",
                "MySQL Learning",
                "1.0",
                "http://localhost:8085/doc.html",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

}