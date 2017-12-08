package pz.bluetoothlowenergy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button login;
    public EditText loginEditText;
    public static String text;
    public void init(){
        login=(Button) findViewById(R.id.login);
        loginEditText=(EditText) findViewById(R.id.editText1);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                text = loginEditText.getText().toString().trim();
                if(text.length() != 0){
                    Intent toy = new Intent(MainActivity.this,Activity_Lobby.class);
                    startActivity(toy);
                }
                else{

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
