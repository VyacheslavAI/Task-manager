package ru.ivanov.test.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import ru.ivanov.test.api.MyRadio;
import ru.ivanov.test.api.Radio;
import ru.ivanov.test.implement.BestFM;
import ru.ivanov.test.implement.WorstFM;

@Configuration
public class ApplicationConfigTwo {

//    @Bean
//    @Profile("dev")
//    @Primary
////    @Qualifier("best")
//    public Radio getBestFM() {
//        return new BestFM();
//    }

//    @Bean
//    @Profile("dev")
//    @Conditional(MyCondition.class)
//    @Primary
//    @Qualifier("worst")
//    @MyRadio
//    public Radio getWorstFM() {
//        return new WorstFM();
//    }
}
