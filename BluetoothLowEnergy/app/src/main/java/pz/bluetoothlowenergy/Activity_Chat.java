package pz.bluetoothlowenergy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Chat extends AppCompatActivity {

    public Button wsteczButtonLobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chat);
        initWsteczLobby();
    }

    private void initWsteczLobby() {
        wsteczButtonLobby = (Button) findViewById(R.id.wsteczLobby);
        wsteczButtonLobby.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent toy = new Intent(Activity_Chat.this,Activity_Lobby.class);
                startActivity(toy);
            }
        });
    }
}
