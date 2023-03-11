package com.stivizu.tutorial.spring.scenario_05.service;

public class DependentJavaService {

    private final JavaService javaService;

    public DependentJavaService(JavaService javaService) {
        this.javaService = javaService;
    }

    public void sayHello() {
        javaService.sayHello();
    }

    public JavaService getJavaService() {
        return javaService;
    }

}
