package com.melnichuk.businesscardsapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.melnichuk.businesscardsapp.R;
import com.melnichuk.businesscardsapp.api.NetworkService;
import com.melnichuk.businesscardsapp.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.SharedPreferences.Editor;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_AUTH_TOKEN;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_PASSWORD;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_USERNAME;
import static com.melnichuk.businesscardsapp.Preferences.APP_PREFERENCES_VISITED;

public class LoginActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_login;

    private EditText username;
    private EditText password;
    private Button signIn;
    private Button signUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        username = findViewById(R.id.username_loginActivity);
        password = findViewById(R.id.password_loginActivity);
        signIn = findViewById(R.id.signIn_button);
        signUp = findViewById(R.id.signUp_button);
        progressBar = findViewById(R.id.progressBar_loginActivity);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignIn();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp();
            }
        });
    }

    private void onSignIn() {
        if (username.getText().toString().trim().length() >= 6 ||
                username.getText().toString().trim().length() >= 6) {
            hideButtons();

            NetworkService.getInstance()
                    .getBusinessCardApi()
                    .signIn(new User(username.getText().toString().trim(), password.getText().toString().trim()))
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
                                Editor editor = preferences.edit();
                                editor.putString(APP_PREFERENCES_USERNAME, username.getText().toString().trim());
                                editor.putString(APP_PREFERENCES_PASSWORD, password.getText().toString().trim());
                                editor.putString(APP_PREFERENCES_AUTH_TOKEN, response.headers().get("Authorization"));
                                editor.putBoolean(APP_PREFERENCES_VISITED, true);
                                editor.apply();

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Невірний логін або пароль!!!", Toast.LENGTH_SHORT).show();
                            }

                            showButtons();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                            showButtons();
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "Короткий логій або пароль", Toast.LENGTH_SHORT).show();
        }
    }

    private void onSignUp() {
        if (username.getText().toString().trim().length() >= 6 ||
                username.getText().toString().trim().length() >= 6) {
            hideButtons();

            NetworkService
                    .getInstance()
                    .getBusinessCardApi()
                    .signUp(new User(username.getText().toString().trim(), password.getText().toString().trim()))
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 400) {
                                Toast.makeText(LoginActivity.this, "Аккаунт зареєстровано", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Аккаунт з таком логіном уже існує", Toast.LENGTH_SHORT).show();
                            }

                            showButtons();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                            showButtons();
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "Короткий логій або пароль", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideButtons() {
        signIn.setVisibility(View.GONE);
        signUp.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showButtons() {
        signIn.setVisibility(View.VISIBLE);
        signUp.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
