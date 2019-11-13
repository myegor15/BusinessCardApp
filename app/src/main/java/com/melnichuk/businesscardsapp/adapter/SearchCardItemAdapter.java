package com.melnichuk.businesscardsapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.dialog.CardDialog;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.List;

public class SearchCardItemAdapter extends RecyclerView.Adapter<CardItemViewHolder> {

    private List<Card> cardList;

    public SearchCardItemAdapter(List<Card> cardList) {
        this.cardList = cardList;
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
        cardItemViewHolder.bind(cardList.get(i));
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    private View.OnClickListener initDialog(final ViewGroup viewGroup, final CardItemViewHolder viewHolder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardDialog dialog = new CardDialog();
                dialog.setCard(cardList.get(viewHolder.getAdapterPosition()));
                dialog.showSaveButton();
                dialog.show(((FragmentActivity)viewGroup.getContext()).getSupportFragmentManager(), "CardDialog");
            }
        };
    }
}
