package com.melnichuk.businesscardsapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.Preferences;
import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.api.NetworkService;
import com.melnichuk.businesscardsapp.pojo.Card;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCardActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private static final int LAYOUT = R.layout.activity_my_card;

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
            card.setFirstName(getInfo(firstName));
            card.setPatronymic(getInfo(patronymic));
            card.setLastName(getInfo(lastName));
            card.setPhoneNum1(getInfo(phoneNum1));
            card.setPhoneNum2(getInfo(phoneNum2));
            card.setFax(getInfo(fax));
            card.setEmail(getInfo(email));
            card.setCompany(getInfo(company));
            card.setProfession(getInfo(profession));
            card.setAddress(getInfo(address));
            card.setWeb(getInfo(web));
            card.setFacebook(getInfo(facebook));
            card.setTwitter(getInfo(twitter));
            card.setInstagram(getInfo(instagram));

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

            SharedPreferences preferences = getSharedPreferences(Preferences.APP_PREFERENCES, MODE_PRIVATE);

//            Date date = new Date(System.currentTimeMillis());
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putLong(Preferences.APP_PREFERENCES_UPDATE_PERSONAL_CARD, date.getTime());

            NetworkService
                    .getInstance()
                    .getBusinessCardApi()
                    .addPersonalCard(preferences.getString(Preferences.APP_PREFERENCES_AUTH_TOKEN, ""), card)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() != 200) {
                                Toast.makeText(MyCardActivity.this, "Помилка збереження на сервер", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(MyCardActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

    private String getInfo(EditText editText){
        String text = editText.getText().toString().trim();
        if(text.length() == 0) {
            return null;
        }
        else {
            return text;
        }
    }
}