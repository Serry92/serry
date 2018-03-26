package com.example.serry.premierleaguefixtures.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.serry.premierleaguefixtures.apis.Apis;
import com.example.serry.premierleaguefixtures.models.Fixture;
import com.example.serry.premierleaguefixtures.models.PremierLeagueResponse;
import com.example.serry.premierleaguefixtures.utilities.Constants;
import com.example.serry.premierleaguefixtures.views.FixtureView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GreenTech-Android on 3/26/2018.
 */

public class FixturePresenterImplementer implements FixturePresenter {
    private FixtureView fixtureView;
    private Context context;
    private String latestDate = "";
    private SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    private SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private String formattedDate = "";

    public FixturePresenterImplementer(FixtureView fixtureView, Context context) {
        this.fixtureView = fixtureView;
        this.context = context;
    }

    @Override
    public void onCreate() {
        fixtureView.initViews();
    }

    @Override
    public void connectWithServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GET_PREMIER_LEAGUE_FIXTURES)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Apis apis = retrofit.create(Apis.class);
        Call<PremierLeagueResponse> listCall = apis.getFixtures();
        listCall.enqueue(new Callback<PremierLeagueResponse>() {
            @Override
            public void onResponse(@NonNull Call<PremierLeagueResponse> call, @NonNull Response<PremierLeagueResponse> response) {
                List<Fixture> fixtureArrayList = response.body().getFixtures();
                for (Fixture fixture : fixtureArrayList) {
                    try {
                        Date date = input.parse(fixture.getDate());
                        formattedDate = outputDate.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (!formattedDate.equals(latestDate)) {
                        latestDate = formattedDate;
                        fixture.setHeader(true);
                    } else
                        fixture.setHeader(false);
                }
                fixtureView.setList(fixtureArrayList);
            }

            @Override
            public void onFailure(@NonNull Call<PremierLeagueResponse> call, @NonNull Throwable t) {
                fixtureView.hideProgress();
            }
        });
    }
}
