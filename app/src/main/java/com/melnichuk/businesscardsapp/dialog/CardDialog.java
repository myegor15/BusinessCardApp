package com.melnichuk.businesscardsapp.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.api.NetworkService;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.Date;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_AUTH_TOKEN;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_UPDATE_CARDS;

public class CardDialog extends DialogFragment implements View.OnClickListener, View.OnLongClickListener {

    private static final int LAYOUT = R.layout.dialog_card;

    private LinearLayout header;
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

    private Realm realm;
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
        realm = Realm.getDefaultInstance();

        View view = inflater.inflate(LAYOUT, container, false);

//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        header = view.findViewById(R.id.header_dialogCard);
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

        header.setOnLongClickListener(this);

        initInformation();
//        setInformationVisibility();
        initSaveButton();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    private void initInformation() {
//        image.setImageResource(card.getImage());
        name.setText(card.getFirstName() +
                (card.getSecondName() != null ? " " + card.getSecondName() : "") +
                (card.getLastName() != null ? " " + card.getLastName() : ""));
//        phoneNum1.setText(card.getPhoneNum1());
//        phoneNum2.setText(card.getPhoneNum2());
//        fax.setText(card.getFax());
//        email.setText(card.getEmail());
//        company.setText(card.getCompany());
//        profession.setText(card.getProfession());
//        address.setText(card.getAddress());
//        web.setText(card.getWeb());
//        facebook.setText(card.getFacebook());
//        twitter.setText(card.getTwitter());
//        instagram.setText(card.getInstagram());

        setInformation(phoneNum1, card.getPhoneNum1());
        setInformation(phoneNum2, card.getPhoneNum2());
        setInformation(fax, card.getFax());
        setInformation(email, card.getEmail());
        setInformation(company, card.getCompany());
        setInformation(profession, card.getProfession());
        setInformation(address, card.getAddress());
        setInformation(web, card.getWeb());
        setInformation(facebook, card.getFacebook());
        setInformation(twitter, card.getTwitter());
        setInformation(instagram, card.getInstagram());

    }

//    private void setInformationVisibility() {
//        if (phoneNum1.length() == 0) phoneNum1.setVisibility(View.GONE);
//        if (phoneNum2.length() == 0) phoneNum2.setVisibility(View.GONE);
//        if (fax.length() == 0) fax.setVisibility(View.GONE);
//        if (email.length() == 0) email.setVisibility(View.GONE);
//        if (company.length() == 0) company.setVisibility(View.GONE);
//        if (profession.length() == 0) profession.setVisibility(View.GONE);
//        if (address.length() == 0) address.setVisibility(View.GONE);
//        if (web.length() == 0) web.setVisibility(View.GONE);
//        if (facebook.length() == 0) facebook.setVisibility(View.GONE);
//        if (twitter.length() == 0) twitter.setVisibility(View.GONE);
//        if (instagram.length() == 0) instagram.setVisibility(View.GONE);
//    }

    private void setInformation(TextView textView, String information){
        if (information == null) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(information);
        }
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
        if (card != null) {
            Number maxId = realm.where(Card.class).max("id");
            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
            card.setId(nextId);
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(card);
                }
            }/*, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getContext(), "Збережено", Toast.LENGTH_SHORT).show();
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }*/);

                SharedPreferences preferences = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                Date date = new Date(System.currentTimeMillis());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(APP_PREFERENCES_UPDATE_CARDS, date.getTime());
                editor.apply();

                NetworkService
                        .getInstance()
                        .getBusinessCardApi()
                        .addOneCard(preferences.getString(APP_PREFERENCES_AUTH_TOKEN, ""),
                                date.getTime(),
                                card)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
//                                if (response.code() != 200) {
//                                    Toast.makeText(getActivity(), "Помилка зюереження на сервер", Toast.LENGTH_SHORT).show();
//                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
//                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            getDialog().cancel();
    }

    @Override
    public boolean onLongClick(View v) {
        SharedPreferences preferences = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Видалення візитки")
                .setMessage("Ви впевнені, що хочете видалити дану візитку?")
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Editor editor = preferences.edit();
                        Date date = new Date(System.currentTimeMillis());
                        editor.putLong(APP_PREFERENCES_UPDATE_CARDS, date.getTime());
                        editor.apply();

                        int id = card.getId();
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                Card card1 = realm.where(Card.class).equalTo("id", id).findFirst();
                                card1.deleteFromRealm();

//                                добавить пост/делит запрос на удаление
                                NetworkService
                                        .getInstance()
                                        .getBusinessCardApi()
                                        .addAllCards(preferences.getString(APP_PREFERENCES_AUTH_TOKEN, ""),
                                                date.getTime(),
                                                realm.copyFromRealm(realm.where(Card.class).notEqualTo("id", 0).findAll()))
                                        .enqueue(new retrofit2.Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
//                                                if (response.code() != 200) {
//                                                    Toast.makeText(getActivity(), "Помилка зюереження на сервер", Toast.LENGTH_SHORT).show();
//                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
//                                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });

                        getDialog().cancel();
                    }
                })
                .setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "No", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();

        return false;
    }
}