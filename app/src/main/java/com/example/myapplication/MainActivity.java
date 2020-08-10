package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import Adapter.ViewPager2Adapter;
import Data.Database;
import Model.Contact;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import android.graphics.Color;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private TextView home_tab, settings_tab;
    private final int ORANGE = 0xFFFF3300;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database*************************************************************************************
        Database database = new Database(this);


        //Insert contacts
        Log.d("Insert: " , "Inserting............");
        database.addContact(new Contact("Munu","+91 84865 71181"));
        database.addContact(new Contact("Deuta", "+91 98643 31062"));
        database.addContact(new Contact("xyirouaf", "+91 79359437405"));


        //show contacts
         List<Contact> contactList = database.getAllContact();

        //to iterate through the contacts we have to look into it
        for(Contact c: contactList){
            String log = "ID: " + c.getId() + "Name: " + c.getName() + "Phone No.: " + c.getPhoneNumber();
            Log.d("Name: ", log);
        }

        // update contact method --- Here we are making an object of Contact model class because its for only one contact and
        // here in order to get all the contact we first used a different method and now we are ready to do it one by one

        Contact oneContact = database.getContact(2);

        //update the contact
        int singleContact = database.updateContact(oneContact);
        oneContact.setPhoneNumber("+91 98633 31062");
        oneContact.setName("Jiten Kalita");

        String log = "ID: " + oneContact.getId() + "Name: " + oneContact.getName() + "Phone No.: " + oneContact.getPhoneNumber();
        Log.d("Update Contact ", log);



        //*********************************************************************************************

        viewPager2 = (ViewPager2) findViewById(R.id.viewpagermine);
        //tabLayout = (TabLayout) findViewById(R.id.tablayout);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.mytoolbar);
        home_tab = (TextView) findViewById(R.id.home_tab);
        settings_tab = (TextView) findViewById(R.id.settings_tab);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);


        //toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        viewPager2.setAdapter(new ViewPager2Adapter(this));
        setSupportActionBar(toolbar);

        settings_tab.setTextColor(Color.parseColor("#B7AEB8"));
        settings_tab.setText("Settings");
        settings_tab.setTextSize(1,23);



        Typeface typeface = ResourcesCompat.getFont(this,R.font.comforta_light);
        home_tab.setTypeface(typeface);
        settings_tab.setTypeface(typeface);

        /*TabLayoutMediator tabLayoutMediator =  new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        tab.setText("Home");
                        break;

                    case 1:
                        tab.setText("Settings");
                        break;
                }

            }
        });*/

        //tabLayoutMediator.attach();

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    home_tab.setText("Home");
                    home_tab.setTextSize(1,23);
                    settings_tab.setTextColor(Color.parseColor("#B7AEB8"));
                    home_tab.setTextColor(Color.parseColor("#000000"));

                }
                else{
                    settings_tab.setText("Settings");
                    settings_tab.setTextSize(1,23);
                    home_tab.setTextColor(Color.parseColor("#B7AEB8"));
                    settings_tab.setTextColor(Color.parseColor("#000000"));

                }
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });







    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
}