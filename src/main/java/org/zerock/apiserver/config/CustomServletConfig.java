package org.zerock.apiserver.config;


import com.querydsl.core.annotations.Config;
import jakarta.servlet.ServletConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.apiserver.controller.formatter.LocalDateFormatter;

import static org.springframework.http.CacheControl.maxAge;


@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer{

    @Override
    public void addFormatters(FormatterRegistry registry) {

        log.info("---------------------");
        log.info("AddForMatters");
        registry.addFormatter(new LocalDateFormatter());
    }
    // 각 컨트롤러마다 해도되나, 생길때마다 해주어야하니 귀찮음. 상속의 목적.
    // preFlight > 하기 전에 되는지 보내는 전령 > OPTIONS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 어떤 경로에 적용?
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // 허용할 HTTP 메소드 지정
                .allowedOrigins("*") // 어디서 오는것을 허용 해줄것인가?
                .maxAge(500);
    }
}
