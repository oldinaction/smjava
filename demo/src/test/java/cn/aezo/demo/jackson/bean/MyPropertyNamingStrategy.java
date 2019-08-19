package cn.aezo.demo.jackson.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class MyPropertyNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {
    @Override
    public String translate(String input) {
        if (input == null) return input;
        return input.toUpperCase();
    }

}
