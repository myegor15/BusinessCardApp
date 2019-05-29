package com.melnichuk.businesscardsapp.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.melnichuk.businesscardsapp.dialog.CardDialog;
import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RealmCardItemAdapter extends RealmRecyclerViewAdapter<Card, RealmCardItemAdapter.CardItemViewHolder> {

    public RealmCardItemAdapter(@Nullable OrderedRealmCollection<Card> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_view, viewGroup, false);

        CardItemViewHolder viewHolder = new CardItemViewHolder(view);
        viewHolder.cardItem.setOnClickListener(initDialog(viewGroup, viewHolder));

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

    public static class CardItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout cardItem;
        private TextView name;
        private TextView company;

        public CardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardItem = itemView.findViewById(R.id.cardItem_linearLayout);
            name = itemView.findViewById(R.id.name_cardItemView);
            company = itemView.findViewById(R.id.company_cardItemView);
        }

        public void bind(Card card) {
//            image.setImageResource(card.getImage());
            name.setText(card.getFirstName() +
                    (card.getPatronymic() != null ? " " + card.getPatronymic() : "") +
                    (card.getLastName() != null ? " " + card.getLastName() : ""));
            company.setText(card.getCompany());

            if (company.length() == 0) company.setVisibility(View.GONE);
        }
    }
}