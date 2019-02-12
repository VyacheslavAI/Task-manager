package ru.ivanov.test.implement;

import ru.ivanov.test.api.Radio;

public class WorstFM implements Radio {

    @Override
    public void listenMusic() {
        System.out.println("worst music");
    }
}
