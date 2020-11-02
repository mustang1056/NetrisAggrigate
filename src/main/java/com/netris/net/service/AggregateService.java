package com.netris.net.service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.netris.net.service.api.NetrisAPI;
import com.netris.net.service.data.MainData;
import com.netris.net.service.data.Source;
import com.netris.net.service.data.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//@Component
public class AggregateService implements Callback<List<MainData>> {

    static final String BASE_URL = "http://www.mocky.io/v2/";

    private NetrisAPI netrisAPI;

    private int check_count = 0;
    private int finish_count = 0;

    private String result = "";
    private AtomicBoolean processed;

    public AggregateService(AtomicBoolean processed){
        this.processed = processed;
    }

    public void start() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        netrisAPI = retrofit.create(NetrisAPI.class);

        Call<List<MainData>> call = netrisAPI.loadData("status:open");

        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<List<MainData>> main_call, Response<List<MainData>> main_response) {

        if(main_response.isSuccessful()) {
            check_count++;

            List<MainData> changesList = main_response.body();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List jsonList = new ArrayList();


            for(MainData mainData : changesList){

                if(check_count == 1) {

                    String id = mainData.getId();

                    Call<Source> callSource = netrisAPI.getSource(mainData.getSourceDataUrl());
                    Call<Token> callToken = netrisAPI.getToken(mainData.getTokenDataUrl());

                    callSource.enqueue(new Callback<Source>() {

                        @Override
                        public void onResponse(Call<Source> call, Response<Source> responseSource) {

                            String urlType = responseSource.body().getUrlType();
                            String videoUrl = responseSource.body().getVideoUrl();

                            callToken.enqueue(new Callback<Token>() {
                                @Override
                                public void onResponse(Call<Token> call, Response<Token> responseToken) {

                                    finish_count++;

                                    Map<String, String> map = new LinkedHashMap();

                                    String ttl = responseToken.body().getTtl();
                                    String value = responseToken.body().getValue();

                                    map.put("id", id);
                                    map.put("urlType", urlType);
                                    map.put("videoUrl", videoUrl);
                                    map.put("ttl", ttl);
                                    map.put("value", value);

                                    jsonList.add(map);

                                    if(finish_count == changesList.size()) {

                                        String prettyJson = gson.toJson(jsonList);
                                        result = prettyJson;

                                        synchronized(processed) {
                                            processed.notifyAll();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<Token> call, Throwable t) {

                                    System.out.println(t.toString());

                                }
                            });

                        }
                        @Override
                        public void onFailure(Call<Source> call, Throwable t) {

                            System.out.println(t.toString());

                        }
                    });

                }

            }

        } else {
            System.out.println(main_response.errorBody());
        }

    }


    @Override
    public void onFailure(Call<List<MainData>> call, Throwable t) {
        t.printStackTrace();
    }

    public String getResult(){
        return this.result;
    }


}