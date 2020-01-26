package com.llukin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);



        Button btn = (Button) findViewById(R.id.getQuoteButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_main);
                textViewResult = findViewById(R.id.text_view_result);
                ucitaj();

                Button btn2 = (Button) findViewById(R.id.GetQuoteAgain);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ucitaj();
                    }
                });

            }
        });

    }




    private void ucitaj(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://programming-quotes-api.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuoteSucelje quoteSucelje = retrofit.create(QuoteSucelje.class);
        Call<Quote> call = quoteSucelje.dohvatiQuote();

        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {

                if(!response.isSuccessful()){
                    return;

                }

                Quote qoute = response.body();

                String contentAuthor = "";
                String content = "";
                content += qoute.getEn();
                contentAuthor += qoute.getAuthor();

                textViewResult.append('"'+ content + '"');
                textViewResult.append("\n");
                textViewResult.append("\n");
                textViewResult.append("Author: " + contentAuthor);
                textViewResult.append("\n");
                textViewResult.append("\n");


                Log.d("radi", "radi");

            }



            @Override
            public void onFailure(Call<Quote> call, Throwable t) {


                Log.d("aaaaaaaaaaaa", t.toString());


            }
        });


    }
}
