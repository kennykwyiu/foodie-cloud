package com.kenny.cart;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// Scan for mybatis common mapper package
@MapperScan(basePackages = "com.kenny.cart.mapper")
// Scan all packages and related component packages
@ComponentScan(basePackages = {"com.kenny", "org.n3r.idworker"})
public class CartApplication {
    public static void main(String[] args) {
         new SpringApplicationBuilder(CartApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
