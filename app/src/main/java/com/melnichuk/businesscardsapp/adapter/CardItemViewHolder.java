package com.melnichuk.businesscardsapp.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

public class CardItemViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout cardItem;
    private TextView name;
    private TextView company;

    public CardItemViewHolder(@NonNull View itemView) {
        super(itemView);
        cardItem = itemView.findViewById(R.id.cardItem_linearLayout);
        name = itemView.findViewById(R.id.name_cardItemView);
        company = itemView.findViewById(R.id.company_cardItemView);
    }

    public LinearLayout getCardItem() {
        return cardItem;
    }

    public void bind(Card card) {
        name.setText(card.getFirstName() +
                (card.getSecondName() != null ? " " + card.getSecondName() : "") +
                (card.getLastName() != null ? " " + card.getLastName() : ""));
        company.setText(card.getCompany());

        if (company.length() == 0) company.setVisibility(View.GONE);
    }
}
