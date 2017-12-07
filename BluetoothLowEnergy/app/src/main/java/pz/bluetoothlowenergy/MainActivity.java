package pz.bluetoothlowenergy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button login;
    public EditText Nick;
    public static String text;
    public void init(){
        login=(Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!Nick.toString().equals("")){
                    text = Nick.toString();
                    Intent toy = new Intent(MainActivity.this,Activity_Lobby.class);
                    startActivity(toy);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nick = (EditText) findViewById(R.id.editText1);
        init();
    }
}
