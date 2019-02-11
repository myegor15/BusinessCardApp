package com.melnichuk.businesscardsapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.Realm;

public class MyCardActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

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

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();

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

        initInformation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(firstName.getText().toString().trim().length() != 0 &&
                phoneNum1.getText().toString().trim().length() != 0) {
            final Card card = new Card();
            card.setId(0);
            card.setFirstName(firstName.getText().toString().trim());
            card.setPatronymic(patronymic.getText().toString().trim());
            card.setLastName(lastName.getText().toString().trim());
            card.setPhoneNum1(phoneNum1.getText().toString().trim());
            card.setPhoneNum2(phoneNum2.getText().toString().trim());
            card.setFax(fax.getText().toString().trim());
            card.setEmail(email.getText().toString().trim());
            card.setCompany(company.getText().toString().trim());
            card.setProfession(profession.getText().toString().trim());
            card.setAddress(address.getText().toString().trim());
            card.setWeb(web.getText().toString().trim());
            card.setFacebook(facebook.getText().toString().trim());
            card.setTwitter(twitter.getText().toString().trim());
            card.setInstagram(instagram.getText().toString().trim());

            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(card);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Toast.makeText(MyCardActivity.this, "Збережено", Toast.LENGTH_SHORT).show();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Toast.makeText(MyCardActivity.this, "Помилка збереження", Toast.LENGTH_SHORT).show();
                }
            });

            onBackPressed();
        } else {
            Toast.makeText(this, "Введіть ім'я та номер телефону", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_myCard);
//        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(R.menu.menu_my_card);
        toolbar.setOnMenuItemClickListener(this);
    }

    private void initInformation() {
        Card card = realm.where(Card.class).equalTo("id", 0).findFirst();
        if(card != null){
            firstName.setText(card.getFirstName());
            patronymic.setText(card.getPatronymic());
            lastName.setText(card.getLastName());
            phoneNum1.setText(card.getPhoneNum1());
            phoneNum2.setText(card.getPhoneNum2());
            fax.setText(card.getFax());
            email.setText(card.getEmail());
            company.setText(card.getCompany());
            profession.setText(card.getProfession());
            address.setText(card.getAddress());
            web.setText(card.getWeb());
            facebook.setText(card.getFacebook());
            twitter.setText(card.getTwitter());
            instagram.setText(card.getInstagram());
        }
    }
}