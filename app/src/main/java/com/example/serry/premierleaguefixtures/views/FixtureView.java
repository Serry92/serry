package com.example.serry.premierleaguefixtures.views;

import com.example.serry.premierleaguefixtures.models.Fixture;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by GreenTech-Android on 3/26/2018.
 */

public interface FixtureView {
    void initViews();

    void hideProgress();

    void setList(List<Fixture> fixtures);
}