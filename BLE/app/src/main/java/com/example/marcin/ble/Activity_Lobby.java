package com.example.marcin.ble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Lobby extends AppCompatActivity {

    public Button wstecz,History;



    public void init(){
        History=(Button) findViewById(R.id.History);
        History.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent toy = new Intent(Activity_Lobby.this,Activity_Archives.class);

                startActivity(toy);
            }
        });


    }

    public void initt(){
        wstecz=(Button) findViewById(R.id.wstecz);
        wstecz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent toy = new Intent(Activity_Lobby.this,MainActivity.class);

                startActivity(toy);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        init();
        initt();
    }
}
