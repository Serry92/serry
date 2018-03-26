package com.example.serry.premierleaguefixtures.models;

/**
 * Created by GreenTech-Android on 3/26/2018.
 */

public class Fixture {
    private String date;
    private String status;
    private String homeTeamName;
    private String awayTeamName;
    private Result result;
    private boolean isHeader;

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public Result getResult() {
        return result;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }
}
