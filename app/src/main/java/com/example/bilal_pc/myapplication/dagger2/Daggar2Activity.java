package com.example.bilal_pc.myapplication.dagger2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.bilal_pc.myapplication.R;
import com.example.bilal_pc.myapplication.dagger2.module.BoxComponent;
import com.example.bilal_pc.myapplication.dagger2.module.BoxModule;
import com.example.bilal_pc.myapplication.dagger2.module.DaggerBoxComponent;
import com.example.bilal_pc.myapplication.dagger2.module.DaggerVehicleComponent;
import com.example.bilal_pc.myapplication.dagger2.module.VehicleComponent;
import com.example.bilal_pc.myapplication.dagger2.module.VehicleModule;


public class Daggar2Activity extends AppCompatActivity {

    Vehicle vehicle;
    Box box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daggar2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action"+box.getSize(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        BoxComponent boxcomponent = DaggerBoxComponent.builder().boxModule(new BoxModule()).build();
        vehicle = component.provideVehicle();
        box= boxcomponent.provideBox();
        vehicle.increaseSpeed(10);

        Toast.makeText(this, String.valueOf(vehicle.getSpeed()), Toast.LENGTH_SHORT).show();
    }

}


