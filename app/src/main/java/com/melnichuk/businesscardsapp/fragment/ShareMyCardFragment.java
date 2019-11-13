package com.melnichuk.businesscardsapp.fragment;

import android.graphics.Bitmap;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.Hashtable;

import io.realm.Realm;

public class ShareMyCardFragment extends Fragment implements NfcAdapter.CreateNdefMessageCallback {

    private static final int LAYOUT = R.layout.fragment_share_my_card;

    private ImageView qrCode;

    private Realm realm;
//    private Card myCard;
//    private Gson gson;
    private String myCard2Json;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        View view = inflater.inflate(LAYOUT, container, false);

        qrCode = view.findViewById(R.id.qrCode_myCard);

//        myCard = realm.where(Card.class).equalTo("id", 0).findFirst();
        //initQrCode();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Card myCard = realm.where(Card.class).equalTo("id", 0).findFirst();
        if (myCard != null) {
            Gson gson = new Gson();
            myCard2Json = gson.toJson(realm.copyFromRealm(myCard));

            initQrCode();
            initNfc();
        } else {
            //добавить надпись о незаполненой своей карты
            Toast.makeText(getContext(), "Персональна карта не заповнена", Toast.LENGTH_SHORT).show();
        }
//        initShare();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    private void initShare() {
        Card myCard = realm.where(Card.class).equalTo("id", 0).findFirst();
        if (myCard != null) {
            Gson gson = new Gson();
            myCard2Json = gson.toJson(realm.copyFromRealm(myCard));

            initQrCode();
            initNfc();
        } else {
            //добавить надпись о незаполненой своей карты
            Toast.makeText(getContext(), "Персональна карта не заповнена", Toast.LENGTH_SHORT).show();
        }
    }

    private void initQrCode(){
//        Card myCard = realm.where(Card.class).equalTo("id", 0).findFirst();
//            Gson gson = new Gson();
//            String myCard2Qr = gson.toJson(realm.copyFromRealm(myCard));

//            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            try {
//                BitMatrix bitMatrix = multiFormatWriter.encode(myCard2Qr, BarcodeFormat.QR_CODE, 500, 500, hints);
//                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//                qrCode.setImageBitmap(bitmap);
//            } catch (WriterException e) {
//                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(myCard2Json, BarcodeFormat.QR_CODE, 500, 500, hints);
                qrCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
//            Gson gson = new Gson();
//            String myCardToNfc = gson.toJson(realm.copyFromRealm(myCard));
            NdefRecord ndefRecord = NdefRecord.createMime("text/plain", myCard2Json.getBytes());
            NdefMessage ndefMessage = new NdefMessage(ndefRecord);

            return ndefMessage;
    }

    private void initNfc() {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getContext());
        if (nfcAdapter == null) {
//            Toast.makeText(getContext(), "Цей пристрій не підтримує NFC", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (!nfcAdapter.isEnabled()) {
//            Toast.makeText(getContext(), "Увімкніть NFC", Toast.LENGTH_SHORT).show();
//        }
        nfcAdapter.setNdefPushMessageCallback(this, getActivity());
    }
}
