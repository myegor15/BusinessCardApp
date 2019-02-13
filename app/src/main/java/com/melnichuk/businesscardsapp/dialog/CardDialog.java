package com.melnichuk.businesscardsapp.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.Realm;

public class CardDialog extends DialogFragment implements View.OnClickListener {

    private static final int LAYOUT = R.layout.dialog_card;

    private ImageView image;
    private TextView name;
    private TextView phoneNum1;
    private TextView phoneNum2;
    private TextView fax;
    private TextView email;
    private TextView company;
    private TextView profession;
    private TextView address;
    private TextView web;
    private TextView facebook;
    private TextView twitter;
    private TextView instagram;
    private Button save;

    private Card card;
    private boolean saveButtonVisible;

    public CardDialog() {
        super();
        this.saveButtonVisible = false;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void showSaveButton() {
        this.saveButtonVisible = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        image = view.findViewById(R.id.image_dialogCard);
        name = view.findViewById(R.id.name_dialogCard);
        phoneNum1 = view.findViewById(R.id.phoneNum1_dialogCard);
        phoneNum2 = view.findViewById(R.id.phoneNum2_dialogCard);
        fax = view.findViewById(R.id.fax_dialogCard);
        email = view.findViewById(R.id.email_dialogCard);
        company = view.findViewById(R.id.company_dialogCard);
        profession = view.findViewById(R.id.profession_dialogCard);
        address = view.findViewById(R.id.address_dialogCard);
        web = view.findViewById(R.id.web_dialogCard);
        facebook = view.findViewById(R.id.facebook_dialogCard);
        twitter = view.findViewById(R.id.twitter_dialogCard);
        instagram = view.findViewById(R.id.instagram_dialogCard);
        save = view.findViewById(R.id.save_dialogCard);

        initInformation();
        setInformationVisibility();
        initSaveButton();

        return view;
    }

    private void initInformation() {
        image.setImageResource(card.getImage());
        name.setText(card.getFirstName() +
                (card.getPatronymic() != null ? " " + card.getPatronymic() : "") +
                (card.getLastName() != null ? " " + card.getLastName() : ""));
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

    private void setInformationVisibility() {
        if (phoneNum1.length() == 0) phoneNum1.setVisibility(View.GONE);
        if (phoneNum2.length() == 0) phoneNum2.setVisibility(View.GONE);
        if (fax.length() == 0) fax.setVisibility(View.GONE);
        if (email.length() == 0) email.setVisibility(View.GONE);
        if (company.length() == 0) company.setVisibility(View.GONE);
        if (profession.length() == 0) profession.setVisibility(View.GONE);
        if (address.length() == 0) address.setVisibility(View.GONE);
        if (web.length() == 0) web.setVisibility(View.GONE);
        if (facebook.length() == 0) facebook.setVisibility(View.GONE);
        if (twitter.length() == 0) twitter.setVisibility(View.GONE);
        if (instagram.length() == 0) instagram.setVisibility(View.GONE);
    }

    private void initSaveButton() {
        if (saveButtonVisible) {
            save.setVisibility(View.VISIBLE);
            save.setOnClickListener(this);
        } else {
            save.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        Realm realm = Realm.getDefaultInstance();
        try {
            if(card != null) {
                Number maxId = realm.where(Card.class).max("id");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                card.setId(nextId);
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(card);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getContext(), "Збережено", Toast.LENGTH_SHORT).show();
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                getDialog().cancel();
            }
        } finally {
            realm.close();
        }
    }
}