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
    public Call<Void> addAllCards(@Header("Authorization") String token,
                                  @Header("Update-Time") long time,
                                  @Body List<Card> data);

    @POST("/cards/one")
    public Call<Void> addOneCard(@Header("Authorization") String token,
                                 @Header("Update-Time") long time,
                                 @Body Card data);

    @POST("/cards/personal")
    public Call<Void> addPersonalCard(@Header("Authorization") String token,
                                      @Header("Update-Time") long time,
                                      @Body Card data);

    @GET("/cards/update")
    public Call<List<Long>> getCardsLastUpdate(@Header("Authorization") String token);

    @GET("/cards/search")
    public Call<List<Card>> getSearchResult(@Header("Authorization") String token,
                                            @Header("Search") String text);
}
