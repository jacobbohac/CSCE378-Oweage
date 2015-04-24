package com.example.jacob.oweage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EventPage extends ActionBarActivity {

    public boolean isIOU = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, MainActivity.CONTACTS);

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.contactName);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View arg1, int index,
                                    long arg3) {
                String contactName = (String) av.getItemAtPosition(index);
                System.out.println(contactName);
                EditText amount = (EditText) findViewById(R.id.amountPaid);

                for (Contact c : MainActivity.contactList) {
                    if (c.getName().equals(contactName) && !isIOU) {
                        //amount.setText(Double.toString(c.getBalance() * -1));
                    }
                }
            }
        });

        textView.setAdapter(adapter);


        String s = getIntent().getExtras().getString("key");


        if (s.equals("1")) {
            /// This indicates a payback event
            /// Auto fill the eventName
            isIOU = false;
            EditText eventName = (EditText) findViewById(R.id.eventName);
            eventName.setText("Payback");
            EditText amount = (EditText) findViewById(R.id.amountPaid);
            amount.setHint("Amount Repaid");
        } else {
            /// IOU event
            isIOU = true;

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_page, menu);
        return true;
    }

    public void finishEvent(View view) {
        String eventName = "";
        double amount = 0.0;
        String contactName = "";
        String dateString = "4/24/2015";

        Date d = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        dateString = sdf.format(d);



        EditText eventTV = (EditText) findViewById(R.id.eventName);
        EditText amountTV = (EditText) findViewById(R.id.amountPaid);
        AutoCompleteTextView contactTV = (AutoCompleteTextView) findViewById(R.id.contactName);

        eventName = eventTV.getText().toString();
        amount = Double.parseDouble(amountTV.getText().toString());
        contactName = contactTV.getText().toString();

        if (isIOU) {
            amount *= -1;
        }
        TransactionEntry entry = new TransactionEntry(dateString, eventName, amount);

        Contact c = new Contact("c");

        for (Contact contact : MainActivity.contactList) {
            if (contactName.equals(contact.getName())) {

                contact.addTransactionEntry(entry);
            }
        }

        new AlertDialog.Builder(this)
                .setMessage("Confirm Transaction?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        for (Contact contact : MainActivity.contactList) {

                        }
                        EventPage.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
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
