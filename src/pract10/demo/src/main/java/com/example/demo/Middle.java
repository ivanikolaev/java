package com.example.demo;

import org.springframework.stereotype.Component;

@Component("middle")
public class Middle implements Programmer {
    @Override
    public void doCoding() {
        System.out.println("Middle programmer is coding...");
    }
}
