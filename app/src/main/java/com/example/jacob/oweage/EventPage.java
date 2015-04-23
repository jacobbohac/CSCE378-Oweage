package com.example.jacob.oweage;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

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
        String date = "TBD";

        EditText eventTV = (EditText) findViewById(R.id.eventName);
        EditText amountTV = (EditText) findViewById(R.id.amountPaid);
        AutoCompleteTextView contactTV = (AutoCompleteTextView) findViewById(R.id.contactName);

        eventName = eventTV.getText().toString();
        amount = Double.parseDouble(amountTV.getText().toString());
        contactName = contactTV.getText().toString();

        if (isIOU) {
            amount *= -1;
        }
        TransactionEntry entry = new TransactionEntry(date, eventName, amount);

        Contact c = new Contact("c");

        for (Contact contact : MainActivity.contactList) {
            if (contactName.equals(contact.getName())) {
                contact.addTransactionEntry(entry);
            }
        }

        System.out.println("Sucessfully added transaction!");

    }

    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goContacts(View view) {
        Intent intent = new Intent(this, ContactsPage.class);
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
