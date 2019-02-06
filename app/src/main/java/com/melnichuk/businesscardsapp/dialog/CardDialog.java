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
import android.widget.ImageView;
import android.widget.TextView;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

public class CardDialog extends DialogFragment {

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

    private Card data;

    public void setData(Card data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initInformation(view);
        setInformation();
        setInformationVisibility();

        return view;
    }

    private void initInformation(View view) {
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
    }

    private void setInformation() {
        image.setImageResource(data.getImage());
        name.setText(data.getName());
        phoneNum1.setText(data.getPhoneNum1());
        phoneNum2.setText(data.getPhoneNum2());
        fax.setText(data.getFax());
        email.setText(data.getEmail());
        company.setText(data.getCompany());
        profession.setText(data.getProfession());
        address.setText(data.getAddress());
        web.setText(data.getWeb());
        facebook.setText(data.getFacebook());
        twitter.setText(data.getTwitter());
        instagram.setText(data.getInstagram());
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
}