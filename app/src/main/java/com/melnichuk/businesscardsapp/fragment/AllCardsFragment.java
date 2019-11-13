package com.melnichuk.businesscardsapp.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.adapter.RealmCardItemAdapter;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;

public class AllCardsFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_all_cards;

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