package com.example.jacob.oweage;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;


public class ContactInfoPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_page);

        ScrollView scroll = (ScrollView)findViewById(R.id.scrollView);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String s = getIntent().getExtras().getString("name");

        //MainActivity ma = new MainActivity();
        ArrayList<Contact> list = MainActivity.contactList;
        Contact c1 = new Contact("c1");

        System.out.println("list size = " + list.size());

        for(Contact c : list){
            System.err.println("\n\n" + s + "     " + c.getName() + "\n\n");
            if(s.equals(c.getName())){
                c1 = c;
                System.out.println(c1.getRelation());

            }
        }


        for (int i = 0; i < 5; i++) {

            Button b = new Button(this);
            b.setWidth(1000);
            b.setHeight(25);
            b.setTextColor(Color.BLACK);
            b.setText(c1.getRelation());
            b.setId(100 + i);

            linearLayout.addView(b);
        }

        scroll.addView(linearLayout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_info_page, menu);
        return true;
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
