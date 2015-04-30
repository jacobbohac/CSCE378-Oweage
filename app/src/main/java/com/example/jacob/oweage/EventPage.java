package com.example.jacob.oweage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EventPage extends ActionBarActivity {

    public boolean isIOU = true;
    public static String name = "";

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


        final EditText mEditPrice = (EditText) findViewById(R.id.amountPaid);
    mEditPrice.addTextChangedListener(new TextWatcher() {
        DecimalFormat dec = new DecimalFormat("0.00");
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if(!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
            {
                String userInput= ""+s.toString().replaceAll("[^\\d]", "");
                if (userInput.length() > 0) {
                    Float in=Float.parseFloat(userInput);
                    float percen = in/100;
                    mEditPrice.setText("$"+dec.format(percen));
                    mEditPrice.setSelection(mEditPrice.getText().length());
                }
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });




        textView.setAdapter(adapter);


        String name = getIntent().getExtras().getString("name");
        EditText contact = (EditText) findViewById(R.id.contactName);
        contact.setText(name);
        if(name != null) textView.dismissDropDown();

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

         /* Check if contact matches */
        boolean contactExists = false;
        boolean amountExists = true;
        boolean eventExits = false;


        eventName = eventTV.getText().toString();
        contactName = contactTV.getText().toString();



        for(Contact contact : MainActivity.contactList){
            if(contactName.equals(contact.getName())){
                contactExists = true;
            }
        }

        if(!contactExists){

            new AlertDialog.Builder(this)
                    .setMessage("Contact doesn't exist")
                    .setCancelable(false)
                    .setPositiveButton("Ok", null)
                    .show();
        }


        if(contactExists) {
            this.name = contactName;
            try {
                amount = Double.parseDouble(amountTV.getText().toString().substring(1));
                System.out.println(amount + "!!!!!!!!!!!!!!!!");
            } catch (Exception e) {
            /* Handle bad parse input */
                 amountExists = false;
                new AlertDialog.Builder(this)
                        .setMessage("Amount invalid")
                        .setCancelable(false)
                        .setPositiveButton("Ok", null)
                        .show();
            }
        }







        if(contactExists && amountExists) {
            if (isIOU) {
                amount *= -1;
            }
            TransactionEntry entry = new TransactionEntry(dateString, eventName, amount);

           for (Contact contact : MainActivity.contactList) {
                if (contactName.equals(contact.getName())) {
                    contact.addTransactionEntry(entry);
                }
            }

            new AlertDialog.Builder(this)
                    .setMessage("Confirm Event?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                               Intent intent = new Intent(getApplicationContext(), ContactInfoPage.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        intent.putExtra("name", EventPage.name);
                                                        startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goContacts(View view) {
        Intent intent = new Intent(this, ContactsPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
