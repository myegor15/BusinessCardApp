package com.melnichuk.businesscardsapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.adapter.SearchCardItemAdapter;
import com.melnichuk.businesscardsapp.api.NetworkService;
import com.melnichuk.businesscardsapp.pojo.Card;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_AUTH_TOKEN;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private static final int LAYOUT = R.layout.activity_search;

    private EditText searchText;
    private RecyclerView recyclerView;

    private List<Card> cardList = new ArrayList<>();
    private SearchCardItemAdapter searchCardItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initRecyclerView();
        searchText = findViewById(R.id.text_searchActivity);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_search);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(R.menu.menu_search);
        toolbar.setOnMenuItemClickListener(this);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.searchActivity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchCardItemAdapter = new SearchCardItemAdapter(cardList);
        recyclerView.setAdapter(searchCardItemAdapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        NetworkService
                .getInstance()
                .getBusinessCardApi()
                .getSearchResult(preferences.getString(APP_PREFERENCES_AUTH_TOKEN, ""),
                        searchText.getText().toString().trim())
                .enqueue(new Callback<List<Card>>() {
                    @Override
                    public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                        if (response.code() == 200) {
                            cardList.clear();
                            if (response.body() != null) {
                                cardList.addAll(response.body());
                            } else {
                                Toast.makeText(SearchActivity.this, "Нічого не знайдено", Toast.LENGTH_SHORT).show();
                            }
                            searchCardItemAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(SearchActivity.this, "Помилка пошуку", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Card>> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        return true;
    }
}