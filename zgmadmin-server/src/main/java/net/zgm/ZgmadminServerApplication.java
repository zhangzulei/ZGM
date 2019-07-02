package net.zgm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("net.zgm.mapper")
public class ZgmadminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZgmadminServerApplication.class, args);
    }

}
