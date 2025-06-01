package com.kenny.item;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// Scan for mybatis common mapper package
@MapperScan(basePackages = "com.kenny.item.mapper")
// Scan all packages and related component packages
@ComponentScan(basePackages = {"com.kenny", "org.n3r.idworker"})
public class ItemApplication {
    public static void main(String[] args) {
         new SpringApplicationBuilder(ItemApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
