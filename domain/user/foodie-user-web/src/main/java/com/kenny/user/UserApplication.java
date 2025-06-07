package com.kenny.user;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// Scan for mybatis common mapper package
@MapperScan(basePackages = "com.kenny.user.mapper")
// Scan all packages and related component packages
@ComponentScan(basePackages = {"com.kenny", "org.n3r.idworker"})
public class UserApplication {
    public static void main(String[] args) {
         new SpringApplicationBuilder(UserApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
