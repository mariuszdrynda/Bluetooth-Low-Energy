package com.example.marcin.ble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Archives_details extends AppCompatActivity {
    public Button wsteczB;

    public void init(){
        wsteczB=(Button) findViewById(R.id.wsteczB);
        wsteczB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent toy = new Intent(Activity_Archives_details.this,Activity_Archives.class);

                startActivity(toy);
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archives_details);
        init();
    }
}
