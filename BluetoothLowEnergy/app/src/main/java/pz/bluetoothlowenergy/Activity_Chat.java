package pz.bluetoothlowenergy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.charset.Charset;

public class Activity_Chat extends AppCompatActivity {

    public Button wsteczButtonLobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chat);
        initWsteczLobby();

        incomingMessage = (TextView) findViewById(R.id.incomingMessage);
        messages = new StringBuilder();
        btnSend = (Button) findViewById(R.id.btnSend);
        etSend = (EditText) findViewById(R.id.editText);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter("incomingMessage"));
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] bytes = etSend.getText().toString().getBytes(Charset.defaultCharset());
                Activity_Lobby.mBluetoothConnection.write(bytes);
                etSend.setText("");
            }
        });
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
    StringBuilder messages;
    TextView incomingMessage;
    Button btnSend;
    EditText etSend;
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("theMessage");
            messages.append(text+"\n");
            incomingMessage.setText(messages);
        }
    };
}
