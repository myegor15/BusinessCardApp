package com.melnichuk.businesscardsapp.adapter;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.List;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder> {

    private List<Card> cardItemList;

    private Dialog dialog;

    public CardItemAdapter(List<Card> cardItemList) {
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.card_item_view, viewGroup, false);

        final CardItemViewHolder viewHolder = new CardItemViewHolder(view);

        //init Dialog
        dialog = new Dialog(viewGroup.getContext());
        dialog.setContentView(R.layout.dialog_card);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewHolder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //утечка памяти
                ImageView imageDialog = dialog.findViewById(R.id.image_dialogCard);
                TextView personNameDialog = dialog.findViewById(R.id.personName_dialogCard);
                TextView organisationNameDialog = dialog.findViewById(R.id.organisationName_dialogCard);

                imageDialog.setImageResource(cardItemList.get(viewHolder.getAdapterPosition()).getImage());
                personNameDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getPersonName());
                organisationNameDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getOrganisationName());

                dialog.show();
            }
        });

        return viewHolder;
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

        private LinearLayout cardItem;
        private ImageView imageImgView;
        private TextView personNameTxtView;
        private TextView organisationNameTxtView;

        public CardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardItem = itemView.findViewById(R.id.cardItem_linearLayout);
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