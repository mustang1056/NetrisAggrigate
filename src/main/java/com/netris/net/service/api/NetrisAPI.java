package com.netris.net.service.api;

import java.util.List;

import com.netris.net.service.data.MainData;
import com.netris.net.service.data.Source;
import com.netris.net.service.data.Token;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NetrisAPI {

    @GET("5c51b9dd3400003252129fb5/")
    Call<List<MainData>> loadData(@Query("q") String status);

    @GET
    Call<Source> getSource(@Url String url);

    @GET
    Call<Token> getToken(@Url String url);

}