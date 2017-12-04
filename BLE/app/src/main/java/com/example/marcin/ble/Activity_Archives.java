package com.example.marcin.ble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Archives extends AppCompatActivity {



    public Button wsteczA, archive_details;

    public void init(){
        wsteczA=(Button) findViewById(R.id.wsteczA);
        wsteczA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent toy = new Intent(Activity_Archives.this,Activity_Lobby.class);

                startActivity(toy);
            }
        });


    }

    public void initt(){
        archive_details=(Button) findViewById(R.id.archive_details);
        archive_details.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent toy = new Intent(Activity_Archives.this,Activity_Archives_details.class);

                startActivity(toy);
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archives);
        init();
        initt();
    }
}
