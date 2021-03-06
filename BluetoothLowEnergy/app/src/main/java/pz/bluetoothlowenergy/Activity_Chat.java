package pz.bluetoothlowenergy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Activity_Chat extends AppCompatActivity {

    public Button wsteczButtonLobby;
    Date timeOfEntry;
    Date timeOfSaving;
    int conversationID;
    int numberOfMassages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chat);
        init();
        initWsteczLobby();
    }

    private void init() { //wstepne dane do rozroznienia konwersacji na potrzeby zapisu do archiwum, eliminacja kopii
        timeOfEntry = Calendar.getInstance().getTime();//czas wejscia do czatu
        Random r = new Random();
        int Low = 1;
        int High = 1000;
        int Result = r.nextInt(High-Low) + Low;
        conversationID = Result;
        // TODO liczba wiadomości z listy ?
        numberOfMassages = 0;
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
    public void initZapisz(View v) throws IOException {

        String DANE = "Tomek: Witaj%nWojtek: Siemka%nTomek: Co tu sie dzieje?";

        String[] temp = DANE.split("%n");
        numberOfMassages = temp.length;
        String fileid = timeOfEntry.toString()+"-"+conversationID+"-"+numberOfMassages;//identyfikator pliku jego unikatowe dane
        String path = Environment.getExternalStorageDirectory().toString()+"/Czat_Archiwum/";//sciezka
        File directory = new File(path);
        File[] files = directory.listFiles();//lista plikow w folderze
        String fullName = fileid+".txt";
        boolean readyToSave = true;
        for(int i = 0; i<=files.length-1; i++)
        {
            String filename = files[i].getName().toString();
            if(files.length==0)
            {
                readyToSave = true;
            }
            else {
                if (filename.equals(fullName)) {//duplikat
                    readyToSave = false;
                }
            }
        }
        if (readyToSave==true)
        {
            saveToFile(temp, path, fileid);
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Ta rozmowa jest juz zapisana";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private void saveToFile(String[] temp, String path, String fileid) throws IOException {
        File file = new File(path, fileid + ".txt");
        FileWriter fstream = new FileWriter(file, true);
        BufferedWriter out = new BufferedWriter(fstream);
        try {
            for (int i = 0;i<temp.length;i++)
            {
                out.write(temp[i]);
                out.newLine();
            }

        } finally {
            out.close();
        }
        Context context = getApplicationContext();
        CharSequence text = "zapisano rozmowe " + fileid;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
