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

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.adapter.CardItemAdapter;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.ArrayList;
import java.util.List;

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
        initCardList();
        recyclerView.setAdapter(new CardItemAdapter(cardList));
    }

    private void initCardList() {
        cardList = new ArrayList<>();
        cardList.add(new Card(R.drawable.ic_add_white_24dp, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.drawable.ic_settings_white_24dp, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.mipmap.ic_launcher, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.mipmap.ic_launcher, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.mipmap.ic_launcher, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.mipmap.ic_launcher, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.mipmap.ic_launcher, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.mipmap.ic_launcher, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
        cardList.add(new Card(R.mipmap.ic_launcher, "Мельничук Єгор Юрійович", "Одеський Національний Політехнічний Університет"));
    }
}
