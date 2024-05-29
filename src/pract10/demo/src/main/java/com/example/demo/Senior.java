package com.example.demo;

import org.springframework.stereotype.Component;

@Component("senior")
public class Senior implements Programmer {
    @Override
    public void doCoding() {
        System.out.println("Senior programmer is coding...");
    }
}
