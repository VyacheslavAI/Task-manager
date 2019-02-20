package ru.ivanov.todoproject.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class CalculatorImpl {

    @WebMethod
    public double sum(final Double a, final Double b) {
        return a + b;
    }
}
