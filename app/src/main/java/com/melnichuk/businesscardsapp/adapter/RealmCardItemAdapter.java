package com.melnichuk.businesscardsapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnichuk.businesscardsapp.dialog.CardDialog;
import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RealmCardItemAdapter extends RealmRecyclerViewAdapter<Card, CardItemViewHolder> {

    public RealmCardItemAdapter(@Nullable OrderedRealmCollection<Card> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_view, viewGroup, false);

        CardItemViewHolder viewHolder = new CardItemViewHolder(view);
        viewHolder.getCardItem().setOnClickListener(initDialog(viewGroup, viewHolder));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemViewHolder cardItemViewHolder, int i) {
        cardItemViewHolder.bind(getData().get(i));
    }

    private View.OnClickListener initDialog(final ViewGroup viewGroup, final CardItemViewHolder viewHolder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardDialog dialog = new CardDialog();
                dialog.setCard(getData().get(viewHolder.getAdapterPosition()));
                dialog.show(((FragmentActivity)viewGroup.getContext()).getSupportFragmentManager(), "CardDialog");
            }
        };


    }
}