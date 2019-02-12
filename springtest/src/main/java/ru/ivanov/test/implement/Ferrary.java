package ru.ivanov.test.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.test.api.Car;
import ru.ivanov.test.api.Radio;

//@Component
public class Ferrary implements Car {

    @Autowired
    private Radio radio;

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void drive() {
        System.out.println("Ferrary driving");
    }
}
