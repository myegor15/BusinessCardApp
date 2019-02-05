package com.melnichuk.businesscardsapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.adapter.RealmCardItemAdapter;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.ArrayList;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;

public class ExampleFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_example;

    private RecyclerView recyclerView;

    private List<Card> cardList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.fragment_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        initCardList();
//        recyclerView.setAdapter(new CardItemAdapter(getCardList()));
        recyclerView.setAdapter(new RealmCardItemAdapter(getCardList(), true));
    }

    private void initCardList() {
        cardList = new ArrayList<>();
        cardList.add(new Card(R.mipmap.ic_launcher_round,
                "Мельничук Єгор",
                "0956301039",
                "",
                "",
                "myegor15@gmail.com",
                "ОНПУ",
                "студент",
                "",
                "vk.com",
                "",
                "@melnichuk_inc",
                "@melnichuk_inc"
        ));
        cardList.add(new Card(R.mipmap.ic_launcher_round,
                "Дмитро Дідик",
                null,
                null,
                null,
                null,
                "ДНМУ",
                null,
                null,
                null,
                null,
                null,
                null
        ));
    }

    private OrderedRealmCollection<Card> getCardList(){
        Realm realm = Realm.getDefaultInstance();
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Card card = realm.createObject(Card.class);
//                card.setName("dima");
//                card.setCompany("DNMU");
//                card.setInstagram("@instagram_lol");
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });


        RealmResults<Card> cardList = realm.where(Card.class).findAll();

        //realm.close();

        return cardList;
    }
}