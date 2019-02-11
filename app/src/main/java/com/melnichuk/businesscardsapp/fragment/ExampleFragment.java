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
import com.melnichuk.businesscardsapp.adapter.RealmCardItemAdapter;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;

public class ExampleFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_example;

    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        View view = inflater.inflate(LAYOUT, container, false);
        initRecyclerView(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RealmCardItemAdapter(getCardList(), true));
    }

    private OrderedRealmCollection<Card> getCardList(){
        RealmResults<Card> cardList = realm.where(Card.class).notEqualTo("id", 0).findAll();
        return cardList;
    }
}