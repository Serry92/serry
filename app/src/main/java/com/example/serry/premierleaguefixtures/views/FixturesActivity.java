package com.example.serry.premierleaguefixtures.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.serry.premierleaguefixtures.R;
import com.example.serry.premierleaguefixtures.adapters.FixturesAdapter;
import com.example.serry.premierleaguefixtures.models.Fixture;
import com.example.serry.premierleaguefixtures.presenters.FixturePresenterImplementer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GreenTech-Android on 3/26/2018.
 */

public class FixturesActivity extends AppCompatActivity implements FixtureView {
    RecyclerView rvList;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    FixturePresenterImplementer fixturePresenterImplementer;
    FixturesAdapter fixturesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_fixtures);
        fixturePresenterImplementer = new FixturePresenterImplementer(this, this);
        fixturePresenterImplementer.onCreate();
    }

    @Override
    public void initViews() {
        rvList = findViewById(R.id.rv_list);
        progressBar = findViewById(R.id.pb_list);
        linearLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(linearLayoutManager);
        fixturePresenterImplementer.connectWithServer();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setList(List<Fixture> fixtures) {
        progressBar.setVisibility(View.GONE);
        fixturesAdapter = new FixturesAdapter(fixtures, this);
        rvList.setAdapter(fixturesAdapter);
    }
}
