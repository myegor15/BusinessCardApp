package com.melnichuk.businesscardsapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.Realm;

public class ShareMyCardFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_share_my_card;

    private ImageView qrCode;

    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        View view = inflater.inflate(LAYOUT, container, false);

        qrCode = view.findViewById(R.id.qrCode_myCard);

        //initQrCode();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initQrCode();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    private void initQrCode(){
        Card myCard = realm.where(Card.class).equalTo("id", 0).findFirst();
        if(myCard != null) {
            Gson gson = new Gson();
            String myCard2Qr = gson.toJson(realm.copyFromRealm(myCard));

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(myCard2Qr, BarcodeFormat.QR_CODE, 500, 500);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qrCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            //добавить надпись о незаполненой своей карты
        }
    }
}
