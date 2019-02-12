package ru.ivanov.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import ru.ivanov.test.config.ApplicationConfig;
import ru.ivanov.test.implement.BestFM;
import ru.ivanov.test.implement.Toyota;

public class Application {

    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.getEnvironment().setActiveProfiles("dev");
//        context.register(ApplicationConfig.class);
//        context.refresh();
//        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:config.xml");
//        final ApplicationContext context = new FileSystemXmlApplicationContext("springtest/src/main/resources/config.xml");
//        final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
//        context.getEnvironment().setActiveProfiles("dev");
//        context.load("classpath:config.xml");
//        context.refresh();

//        final Book book = context.getBean(BookImpl.class);
//        book.read();

        final Toyota bean = context.getBean(Toyota.class);
        bean.drive();

//        final ExpressionParser parser = new SpelExpressionParser();
//        final Expression expression = parser.parseExpression("'hello world'");
//        final String value = (String) expression.getValue();
//        System.out.println(value);

//        final BestFM bestFM = new BestFM();
//        bestFM.setName("TestFM");
//        final ExpressionParser parser2 = new SpelExpressionParser();
//        final Expression expression2 = parser2.parseExpression("name");
//        final EvaluationContext context2 = new StandardEvaluationContext(bestFM);
//        final String name = (String) expression2.getValue(context2);
//        System.out.println(name);
    }
}
