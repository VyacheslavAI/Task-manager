package ru.ivanov.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import ru.ivanov.test.api.Car;
import ru.ivanov.test.api.Radio;
import ru.ivanov.test.implement.BestFM;
import ru.ivanov.test.implement.Ferrary;
import ru.ivanov.test.implement.Toyota;

@Configuration
@ComponentScan(basePackages = {"ru.ivanov.test", "ru.ivanov.test.implement"})
//@Import(ApplicationConfigTwo.class)
//@ImportResource("classpath:config2.xml")
//@PropertySource("my.properties")
public class ApplicationConfig {

//    @Bean
//    public Car getToyota() {
//        final Toyota toyota = new Toyota();
//        toyota.setRadio(getRadio());
//        return toyota;
//    }
//
//    @Bean
//    public Car getFerrary() {
//        final Ferrary ferrary = new Ferrary();
//        ferrary.setRadio(getRadio());
//        return ferrary;
//    }
//
//    @Bean
//    public Radio getRadio() {
//        return new BestFM();
//    }
}