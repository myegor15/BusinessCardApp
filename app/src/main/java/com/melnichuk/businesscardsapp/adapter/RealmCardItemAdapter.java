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
                dialog.setData(getData().get(viewHolder.getAdapterPosition()));
                dialog.show(((FragmentActivity)viewGroup.getContext()).getSupportFragmentManager(), "CardDialog");
            }
        };


    }

    public static class CardItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout cardItem;
        private ImageView imageImgView;
        private TextView nameTxtView;
        private TextView companyTxtView;

        public CardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardItem = itemView.findViewById(R.id.cardItem_linearLayout);
            imageImgView = itemView.findViewById(R.id.image_cardItemView);
            nameTxtView = itemView.findViewById(R.id.name_cardItemView);
            companyTxtView = itemView.findViewById(R.id.company_cardItemView);
        }

        public void bind(Card card) {
            imageImgView.setImageResource(card.getImage());
            nameTxtView.setText(card.getName());
            companyTxtView.setText(card.getCompany());
        }
    }
}