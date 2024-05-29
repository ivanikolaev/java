package com.example.demo;

import org.springframework.stereotype.Component;

@Component("junior")
public class Junior implements Programmer {
    @Override
    public void doCoding() {
        System.out.println("Junior programmer is coding...");
    }
}