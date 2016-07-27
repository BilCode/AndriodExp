package com.example.bilal_pc.myapplication.dagger2.module;

import com.example.bilal_pc.myapplication.dagger2.Box;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {BoxModule.class})
public interface BoxComponent {
    Box provideBox();
}
