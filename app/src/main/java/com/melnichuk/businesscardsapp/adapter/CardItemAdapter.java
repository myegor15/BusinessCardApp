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
        viewHolder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog(viewGroup, viewHolder);
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

    private void initDialog(ViewGroup viewGroup, CardItemViewHolder viewHolder) {
        Dialog dialog = new Dialog(viewGroup.getContext());
        dialog.setContentView(R.layout.dialog_card);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageDialog = dialog.findViewById(R.id.image_dialogCard);
        TextView nameDialog = dialog.findViewById(R.id.name_dialogCard);
        TextView phoneNum1Dialog = dialog.findViewById(R.id.phoneNum1_dialogCard);
        TextView phoneNum2Dialog = dialog.findViewById(R.id.phoneNum2_dialogCard);
        TextView faxDialog = dialog.findViewById(R.id.fax_dialogCard);
        TextView emailDialog = dialog.findViewById(R.id.email_dialogCard);
        TextView companyDialog = dialog.findViewById(R.id.company_dialogCard);
        TextView professionDialog = dialog.findViewById(R.id.profession_dialogCard);
        TextView addressDialog = dialog.findViewById(R.id.address_dialogCard);
        TextView webDialog = dialog.findViewById(R.id.web_dialogCard);
        TextView facebookDialog = dialog.findViewById(R.id.facebook_dialogCard);
        TextView twitterDialog = dialog.findViewById(R.id.twitter_dialogCard);
        TextView instagramDialog = dialog.findViewById(R.id.instagram_dialogCard);

        imageDialog.setImageResource(cardItemList.get(viewHolder.getAdapterPosition()).getImage());
        nameDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getFirstName());
        phoneNum1Dialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getPhoneNum1());
        phoneNum2Dialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getPhoneNum2());
        faxDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getFax());
        emailDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getEmail());
        companyDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getCompany());
        professionDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getProfession());
        addressDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getAddress());
        webDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getWeb());
        facebookDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getFacebook());
        twitterDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getTwitter());
        instagramDialog.setText(cardItemList.get(viewHolder.getAdapterPosition()).getInstagram());

        if (phoneNum1Dialog.length() == 0) phoneNum1Dialog.setVisibility(View.GONE);
        if (phoneNum2Dialog.length() == 0) phoneNum2Dialog.setVisibility(View.GONE);
        if (faxDialog.length() == 0) faxDialog.setVisibility(View.GONE);
        if (emailDialog.length() == 0) emailDialog.setVisibility(View.GONE);
        if (companyDialog.length() == 0) companyDialog.setVisibility(View.GONE);
        if (professionDialog.length() == 0) professionDialog.setVisibility(View.GONE);
        if (addressDialog.length() == 0) addressDialog.setVisibility(View.GONE);
        if (webDialog.length() == 0) webDialog.setVisibility(View.GONE);
        if (facebookDialog.length() == 0) facebookDialog.setVisibility(View.GONE);
        if (twitterDialog.length() == 0) twitterDialog.setVisibility(View.GONE);
        if (instagramDialog.length() == 0) instagramDialog.setVisibility(View.GONE);

        dialog.show();
    }

    public class CardItemViewHolder extends RecyclerView.ViewHolder {

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
            nameTxtView.setText(card.getFirstName());
            companyTxtView.setText(card.getCompany());
        }
    }
}