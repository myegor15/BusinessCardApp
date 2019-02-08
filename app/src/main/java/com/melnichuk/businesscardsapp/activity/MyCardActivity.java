package com.melnichuk.businesscardsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.R;

public class MyCardActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_my_card;

    private ImageView image;
    private EditText firstName;
    private EditText patronymic;
    private EditText lastName;
    private EditText phoneNum1;
    private EditText phoneNum2;
    private EditText fax;
    private EditText email;
    private EditText company;
    private EditText profession;
    private EditText address;
    private EditText web;
    private EditText facebook;
    private EditText twitter;
    private EditText instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();

        image = findViewById(R.id.image_myCard);
        firstName = findViewById(R.id.firstName_myCard);
        patronymic = findViewById(R.id.patronymic_myCard);
        lastName = findViewById(R.id.lastName_myCard);
        phoneNum1 = findViewById(R.id.telNum1_myCard);
        phoneNum2 = findViewById(R.id.telNum2_myCard);
        fax = findViewById(R.id.fax_myCard);
        email = findViewById(R.id.email_myCard);
        company = findViewById(R.id.company_myCard);
        profession = findViewById(R.id.profession_myCard);
        address = findViewById(R.id.address_myCard);
        web = findViewById(R.id.web_myCard);
        facebook = findViewById(R.id.facebook_myCard);
        twitter = findViewById(R.id.twitter_myCard);
        instagram = findViewById(R.id.instagram_myCard);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_myCard);
//        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.menu_my_card);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MyCardActivity.this, "Save", Toast.LENGTH_SHORT).show();
                onBackPressed();
                return true;
            }
        });
    }
}