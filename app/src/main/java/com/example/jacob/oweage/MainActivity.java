package com.example.jacob.oweage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<Contact> contactList;
    public static final String[] CONTACTS = new String[] {
            "Aaron Aardvark",
            "Anita Addams",
            "Dexter Addams",
            "Digit Addams",
            "Gomez Addams",
            "Granny Addams",
            "Sir Agravaine",
            "Molly Azog",
            "Susan Baaa-sheep",
            "Bryan Baelish",
            "Christian Bale",
            "Jeff Bridges",
            "Hagrid Carmichael",
            "Michael Chaggart",
            "Lily Dagger",
            "Angelina Darkside",
            "Ezekiel Doggy",
            "Stan Kubrick",
            "Rodrigo Lopez",
            "Alejandro Studman",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initContacts();
    }

    public void initContacts(){

        for(String name : CONTACTS){
            contactList.add(new Contact(name));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Button btn = (Button)findViewById(R.id.button3);

    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, ContactsPage.class);
        startActivity(intent);
    }

    /*
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, ContactsPage.class));
        }
    });  */


    public void startEvent(View view){
        Intent intent = new Intent(this, EventPage.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
