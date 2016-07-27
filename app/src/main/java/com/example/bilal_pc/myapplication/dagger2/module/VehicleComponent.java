package com.example.bilal_pc.myapplication.dagger2.module;

import com.example.bilal_pc.myapplication.dagger2.Vehicle;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {
    Vehicle provideVehicle();
}
