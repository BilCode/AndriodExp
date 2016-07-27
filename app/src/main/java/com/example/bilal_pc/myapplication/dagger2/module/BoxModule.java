package com.example.bilal_pc.myapplication.dagger2.module;

import com.example.bilal_pc.myapplication.dagger2.Box;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BoxModule {

    @Provides
    @Singleton
    Box provideBox(){
        return (new Box());
    }

}
