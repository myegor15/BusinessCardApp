package com.melnichuk.businesscardsapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.List;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder> {

    private List<Card> cardItemList;

    public CardItemAdapter(List<Card> cardItemList) {
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.card_item_view, viewGroup, false);
        return new CardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemViewHolder cardItemViewHolder, int i) {
        cardItemViewHolder.bind(cardItemList.get(i));
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public class CardItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageImgView;
        private TextView personNameTxtView;
        private TextView organisationNameTxtView;

        public CardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageImgView = itemView.findViewById(R.id.image_cardItemView);
            personNameTxtView = itemView.findViewById(R.id.personName_cardItemView);
            organisationNameTxtView = itemView.findViewById(R.id.organisationName_cardItemView);
        }

        public void bind(Card card) {
            imageImgView.setImageResource(card.getImage());
            personNameTxtView.setText(card.getPersonName());
            organisationNameTxtView.setText(card.getOrganisationName());
        }
    }
}