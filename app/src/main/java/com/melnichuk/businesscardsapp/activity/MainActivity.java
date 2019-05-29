package com.melnichuk.businesscardsapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.adapter.CardsFragmentAdapter;
import com.melnichuk.businesscardsapp.dialog.CardDialog;
import com.melnichuk.businesscardsapp.fragment.AllCardsFragment;
import com.melnichuk.businesscardsapp.fragment.ShareMyCardFragment;
import com.melnichuk.businesscardsapp.pojo.Card;

import io.realm.Realm;

import static android.content.SharedPreferences.Editor;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_VISITED;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar toolbar;

    private SharedPreferences preferences;
    private Realm realm;
    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hasVisited();

        realm = Realm.getDefaultInstance();

        setContentView(LAYOUT);

        initToolbar();
        initViewPager();
        initNavigationView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initScan();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (card != null) {
            CardDialog dialog = new CardDialog();
            dialog.setCard(card);
            dialog.showSaveButton();
            dialog.show(getSupportFragmentManager(), "dialog");
            card = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You canceled the scanning", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    Gson gson = new Gson();
                    card = gson.fromJson(result.getContents(), Card.class);
//                    card.setId(new Random().nextInt());
//                    realm.executeTransactionAsync(new Realm.Transaction() {
//                        @Override
//                        public void execute(Realm realm) {
//                            realm.copyToRealm(card);
//                        }
//                    });
                } catch (JsonSyntaxException e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void initViewPager() {
        final ViewPager viewPager = findViewById(R.id.viewPager);

        CardsFragmentAdapter adapter = new CardsFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllCardsFragment());
        adapter.addFragment(new ShareMyCardFragment());

        viewPager.setAdapter(adapter);
    }

    private void initNavigationView(){
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openNavigation, R.string.closeNavigation);
        toggle.syncState();
        toggle.setDrawerSlideAnimationEnabled(false);
        drawerLayout.addDrawerListener(toggle);

        final NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawer(navigationView);
                switch (menuItem.getItemId()){
                    case R.id.myCard_menu:
                        startActivity(new Intent(MainActivity.this, MyCardActivity.class));
                        break;
                    case R.id.settings_menu:
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings_sync:
                        syncData();
                        break;
                    case R.id.settings_logout:
                        Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    private void syncData() {

    }

    private void initScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Відскануйте QR Code");
        integrator.setBeepEnabled(false);
//        integrator.setOrientationLocked(true);
//        integrator.setCameraId(0);
//        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    private void hasVisited() {
        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа
        boolean isVisited = preferences.getBoolean(APP_PREFERENCES_VISITED, false);

        if (!isVisited) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}