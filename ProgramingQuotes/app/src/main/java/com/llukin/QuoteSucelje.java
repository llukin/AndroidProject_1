package com.llukin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteSucelje {

    @GET("/quotes/random")
    Call<Quote> dohvatiQuote();
}
