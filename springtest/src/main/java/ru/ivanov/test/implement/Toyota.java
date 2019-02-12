package ru.ivanov.test.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.test.api.Car;
import ru.ivanov.test.api.Radio;

import java.util.List;

@Component
public class Toyota implements Car {

    //    @Value("#{1}")
//    @Value("#{radio.getName()}")
//    @Value("#{radio.getName().toUpperCase()}")
//    @Value("#{radio.getName().toUpperCase()}")
//    @Value("#{radio.name}")
//    @Value("Max")
//    @Value("#{systemProperties['java.home']}")
//    @Value("#{3.14159}")
//    @Value("#{'Hello'}")
//    @Value("#{'Hello world")
//    @Value("#{'Hello World'.concat('!')}")
//    @Value("#{'Hello World'.bytes.length}")
//    @Value("#{true}")
//    @Value("#{T(java.lang.Math).PI}")
//    @Value("#{1 + 2}")
//    @Value("#{1 < 2}")
//    @Value("#{true and false or true}")
//    @Value("#{1 eq 2}")
//    @Value("#{1 == 2}")
//    @Value("#{radio.name eq 'best'}")
//    @Value("#{radio.name == 'best'}")
//    @Value("#{radio.name == 'best' ? 'this is true' : 'this is false'}")
//    @Value("#{radio.name matches '[a-z]*'}")
//    @Value("#{radio.name matches '[0-9]*'}")
//    @Value("#{toyota.list[0]}")
//    @Value("#{'This is a test'[0]}")
    private String name;

    @Autowired
    private Radio radio;



    public Radio getRadio() {
        return radio;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void drive() {
        radio.listenMusic();
        System.out.println("driving by " + name);
    }
}
