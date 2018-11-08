package com.ititon.datacraw.config;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class SeleniumConfig {


    @Bean
    public ChromeDriverService chromeDriverService() {
        File file = new File("/home/amal/IdeaProjects/datacraw/src/main/resources/chromedriver");
        return new ChromeDriverService.Builder().usingDriverExecutable(file).usingPort(8090).build();
    }


}
