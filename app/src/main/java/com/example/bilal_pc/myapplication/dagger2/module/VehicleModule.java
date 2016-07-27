package com.example.bilal_pc.myapplication.dagger2.module;

import com.example.bilal_pc.myapplication.dagger2.Motor;
import com.example.bilal_pc.myapplication.dagger2.Vehicle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VehicleModule {

    @Provides
    @Singleton
    Motor provideMotor(){
        return new Motor();
    }

    @Provides @Singleton
    Vehicle provideVehicle(){
        return new Vehicle(new Motor());
    }
}
