package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication//스프링부트 자동설정, 스프링bean 읽기와 생성 /특히 여기부터 읽어나감
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);//SpringApplication.run을 통해 내장 was 실행
    }
}
