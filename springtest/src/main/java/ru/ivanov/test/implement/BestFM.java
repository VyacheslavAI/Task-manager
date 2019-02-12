package ru.ivanov.test.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ivanov.test.api.Radio;

@Component(value = "radio")
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BestFM implements Radio {

    @Value("best")
    private String name;

    @Override
    public void listenMusic() {
        System.out.println("cool music");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
