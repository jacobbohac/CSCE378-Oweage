package com.example.jacob.oweage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public boolean isInitialized = false;
    public static ArrayList<Contact> contactList = new ArrayList<Contact>();
    public static final String[] CONTACTS = new String[]{
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

    /*public double getTotalBalance(){
        double balance = 0.0;
        for(Contact c : contactList){

            balance += c.getBalance();
        }
        return balance;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(!isInitialized) {
            System.out.println("ON CREATE!");
            initContacts();
            isInitialized = true;
        }


        /*TextView balance = (TextView)findViewById(R.id.totalBalance);

        balance.setText(Double.toString(getTotalBalance()));
        if(getTotalBalance() < 0){
            balance.setTextColor(Color.RED);
        }else{
            balance.setTextColor(Color.GREEN);
        }*/


    }

    public void initContacts() {

        ArrayList<TransactionEntry> entryList = new ArrayList<TransactionEntry>();

        int j = 20;
        double i = 7.00;
        for (; j < 27; i += .75, j++) {
            entryList.add(new TransactionEntry(new String("04/" + j + "/2015"), "Movie", -i));
        }

        //TransactionEntry entry = new TransactionEntry("4/22/15", "Movie", 12.00);
        for (String name : CONTACTS) {

            if (name != null) {
                Contact c = null;
                if(name.equals("Jeff Bridges")) {
                    c = new Contact(name, "roommate");
                }
                else if(name.equals("Bryan Baelish")) {
                    c = new Contact(name, "co-conspirator");
                }
                else {
                    c = new Contact(name, "friend");
                }
                c.setHistory(entryList);
                contactList.add(c);

            }
        }

        System.out.println("SIZE: " + CONTACTS.length);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goContacts(View view) {
        Intent intent = new Intent(this, ContactsPage.class);
        startActivity(intent);
    }

    public void goSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void startEvent(View view) {
        Intent intent = new Intent(this, EventPage.class);
        System.out.println("--->" + view.getTag());

        // This passes a 0 for IOU event
        //               1 for payback event
        intent.putExtra("key", view.getTag().toString());
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
