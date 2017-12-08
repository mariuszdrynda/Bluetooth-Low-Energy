package pz.bluetoothlowenergy;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "PermissionDemo";
    private static final int REQUEST_WRITE_STORAGE = 112;
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
        checkPermissionAndCreatingFolder();
    }

    private void checkPermissionAndCreatingFolder() {
        if(canCreateFolder())
        {
            CreateFolder();
        }
        else
        {
            CheckPermision();
        }
    }

    private void CheckPermision() {


        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Permission to access the SD-CARD is required for this app to Save Chat in Archive.")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "Clicked");
                        makeRequest();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                makeRequest();
            }
        }
    }
    private void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user");
                    checkPermissionAndCreatingFolder();

                } else {

                    Log.i(TAG, "Permission has been granted by user"); //GRANTED
                    CreateFolder();

                }
                return;
            }
        }
    }
    private boolean hasPermission(String permissionString) {
        return (ContextCompat.checkSelfPermission(this, permissionString) == PackageManager.PERMISSION_GRANTED);
    }
    private boolean canCreateFolder() {
        return (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    private void CreateFolder() {
        File mFolder = new File(Environment.getExternalStorageDirectory().getPath(), "Czat_Archiwum");
        if (!mFolder.exists()) {
            mFolder.mkdir();
            mFolder.setExecutable(true);
            mFolder.setReadable(true);
            mFolder.setWritable(true);
            String s = mFolder.getPath();
            Context context = getApplicationContext();
            CharSequence text = ""+s;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }


}
