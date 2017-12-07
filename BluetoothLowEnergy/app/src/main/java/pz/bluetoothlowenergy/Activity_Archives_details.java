package pz.bluetoothlowenergy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        String s = getIntent().getStringExtra("path");
        Context context = getApplicationContext();
        CharSequence text = "Wczytuje: " + s;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        BufferedReader reader = null;

        String path = Environment.getExternalStorageDirectory().toString()+"/Czat_Archiwum/"+s;
        File file = new File(path);
        StringBuilder textToPut = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                textToPut.append(line);
                textToPut.append('\n');
            }
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        TextView output=(TextView) findViewById(R.id.textViewZawartosc);
        //output.setMovementMethod(new ScrollingMovementMethod());
        // Assuming that 'output' is the id of your TextView
        for (int i = 0; i < 7; i++)
            textToPut.append(textToPut.toString());
        output.setText(textToPut);


        }

}
