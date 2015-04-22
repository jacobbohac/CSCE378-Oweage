package com.example.jacob.oweage;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class ContactsPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_page);

        ScrollView scroll = (ScrollView)findViewById(R.id.scrollView);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        int i=0;

        for (String name : MainActivity.CONTACTS) {
            if(name != null) {
                Button b = new Button(this);
                b.setWidth(1000);
                b.setHeight(25);
                b.setTextColor(Color.BLACK);
                b.setText(name);


                b.setTag(name);
                b.setId(100 + i);
                b.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(ContactsPage.this, ContactInfoPage.class);

                        intent.putExtra("name",(String) v.getTag());

                        startActivity(intent);
                    }
                });
                i++;


                linearLayout.addView(b);
            }
        }
       // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        scroll.addView(linearLayout);
     //   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_page, menu);




        return true;
    }

    public void goContactInfo(View view){
        String name = (String) view.getTag();

        Intent intent = new Intent(this, ContactInfoPage.class);
        intent.putExtra("name", name);
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
