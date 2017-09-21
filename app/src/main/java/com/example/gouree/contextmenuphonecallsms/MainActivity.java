package com.example.gouree.contextmenuphonecallsms;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.gouree.contextmenuphonecallsms.R.id.parent;

public class MainActivity extends AppCompatActivity {

     ListView ls;

    //names that appear on the text view
    String[] names =
            {"Gouree", "Vihaan", "Vidhi", "Sanket", "Sonam", "nilam", "jhelam", "wani"};

    //contact numbers appearing on text view
    String[] contacts =
            {
                    "1234567890",
                    "0123456789",
                    "8374837871",
                    "3477748811",
                    "7873888394",
                    "7763767377",
                    "6536661212",
                    "6776776761"
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //list view object
        ls = (ListView) findViewById(R.id.list_view);

        //adapters
        Adapter adapter = new Adapter(this, names, contacts);

        ls.setAdapter(adapter);


        //Events

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "clicked -->" + names[position] + "\nnumber -->" + contacts[position], Toast.LENGTH_LONG).show();

            }
        });

        registerForContextMenu(ls);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //inflating the context menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //storing the position of selected item from list in listposition
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;

        String phone1 = "tel:" + contacts[listPosition];
        String phone2 = contacts[listPosition];

        switch (item.getItemId()) {

            case R.id.call:
                Toast.makeText(this, "call to " + names[listPosition], Toast.LENGTH_SHORT).show();
                //call function makecall
                makecall(phone1,listPosition);
                 break;
            case R.id.sms:
                Toast.makeText(this, "Sending message to " + names[listPosition], Toast.LENGTH_SHORT).show();
                String message = "Happy navratri";
                //call function sendmessage
                sendmessage(phone2, message, listPosition);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void makecall(String phone, int listPosition) {
        try {
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse(phone));
            startActivity(i);
             }
        catch (Exception e){
            Toast.makeText(this, "calling to " + names[listPosition]+"failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendmessage(String phone, String message, int listPosition) {
        try {

            //Getting intent and PendingIntent instance
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

            //Get the SmsManager instance and call the sendTextMessage method to send message
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(phone, null, message, pi,null);

            Toast.makeText(getApplicationContext(), "Message Sent successfully to "+names[listPosition],
                    Toast.LENGTH_LONG).show();

            /*SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(this, "Sms sent to " + names[listPosition], Toast.LENGTH_SHORT).show();*/
        } catch (Exception e) {
            Toast.makeText(this, "Sms sending failed to " + names[listPosition], Toast.LENGTH_SHORT).show();
        }
    }


    }