package pz.bluetoothlowenergy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        final ListView listview = (ListView) findViewById(R.id.ListViewArchives);
        String path = Environment.getExternalStorageDirectory().toString()+"/Czat_Archiwum/";//sciezka
        File directory = new File(path);
        File[] files = directory.listFiles();
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < files.length; ++i) {
            list.add(files[i].getName().toString());
        /*String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
            */
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id)
            {


                final String item = (String) parent.getItemAtPosition(position);



                final AlertDialog.Builder alert = new AlertDialog.Builder(Activity_Archives.this);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do your work here
                        list.remove(item);
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                        String path = Environment.getExternalStorageDirectory().toString()+"/Czat_Archiwum/"+item;
                        File file = new File(path);
                        boolean deleted = file.delete();
                        if(deleted)
                        {
                            Context context = getApplicationContext();
                            CharSequence text = "Usunieto Rozmowe: " + item;
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        view.setAlpha(1);
                        dialog.dismiss();
                    }
                });
                view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        alert.show();
                    }
                });


                return true;



            }

        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                final String item = (String) adapter.getItemAtPosition(position);
                Intent appInfo = new Intent(Activity_Archives.this, Activity_Archives_details.class).putExtra("path",item);
                startActivity(appInfo);
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
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}


