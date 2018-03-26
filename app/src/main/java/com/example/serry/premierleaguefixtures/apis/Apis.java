package com.example.serry.premierleaguefixtures.apis;

import com.example.serry.premierleaguefixtures.models.Fixture;
import com.example.serry.premierleaguefixtures.models.PremierLeagueResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by GreenTech-Android on 3/26/2018.
 */

public interface Apis {
    @GET("fixtures")
    Call<PremierLeagueResponse> getFixtures();
}
