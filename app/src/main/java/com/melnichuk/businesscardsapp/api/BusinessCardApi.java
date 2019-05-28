package com.melnichuk.businesscardsapp.api;

import com.melnichuk.businesscardsapp.pojo.Card;
import com.melnichuk.businesscardsapp.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BusinessCardApi {

    @POST("/login")
    public Call<Void> signIn(@Body User user);

    @POST("/users/sign-up")
    public Call<Void> signUp(@Body User user);

    @GET("/cards")
    public Call<List<Card>> getAllCards(@Header("Authorization") String token);

    @GET("/cards/personal")
    public Call<Card> getPersonalCard(@Header("Authorization") String token);

    @POST("/cards")
    public Call<List<Card>> addAllCards(@Header("Authorization") String token, @Body List<Card> data);

    @POST("/cards/one")
    public Call<Card> addOneCard(@Header("Authorization") String token, @Body Card data);

    @POST("/personal")
    public Call<Card> addPersonalCard(@Header("Authorization") String token, @Body Card data);
}
