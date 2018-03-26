package com.example.serry.premierleaguefixtures.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serry.premierleaguefixtures.R;
import com.example.serry.premierleaguefixtures.models.Fixture;
import com.example.serry.premierleaguefixtures.utilities.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by GreenTech-Android on 3/26/2018.
 */

public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.TeamsViewHolder> {
    private List<Fixture> fixtureList;
    private Context context;
    private SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    private SimpleDateFormat outputTime = new SimpleDateFormat("HH:mm", Locale.US);
    private SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public FixturesAdapter(List<Fixture> fixtureList, Context context) {
        this.fixtureList = fixtureList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_fixture, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {

        holder.tvHomeTeam.setText(fixtureList.get(position).getHomeTeamName());
        holder.tvAwayTeam.setText(fixtureList.get(position).getAwayTeamName());
        holder.tvStatus.setText(fixtureList.get(position).getStatus());
        switch (fixtureList.get(position).getStatus()) {
            case Constants.FIXTURE_FINISHED:
                holder.tvResultOrTime.setText(String.format("%s - %s", fixtureList.get(position).getResult().getGoalsHomeTeam(), fixtureList.get(position).getResult().getGoalsAwayTeam()));
                break;
            case Constants.FIXTURE_TIMED:
            case Constants.FIXTURE_POSTPONED:
                String formattedTime = "";
                try {
                    Date time = input.parse(fixtureList.get(position).getDate());
                    formattedTime = outputTime.format(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                holder.tvResultOrTime.setText(formattedTime);

                break;
        }
        if (fixtureList.get(position).isHeader()) {
            String formattedDate = "";
            try {
                Date time = input.parse(fixtureList.get(position).getDate());
                formattedDate = outputDate.format(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.tvDate.setVisibility(View.VISIBLE);
            holder.tvDate.setText(formattedDate);
        }

    }

    @Override
    public int getItemCount() {
        return fixtureList.size();
    }

    class TeamsViewHolder extends RecyclerView.ViewHolder {
        TextView tvHomeTeam, tvAwayTeam, tvResultOrTime, tvStatus;
        TextView tvDate;

        private TeamsViewHolder(View itemView) {
            super(itemView);
            tvHomeTeam = itemView.findViewById(R.id.tv_team_home);
            tvAwayTeam = itemView.findViewById(R.id.tv_team_away);
            tvResultOrTime = itemView.findViewById(R.id.tv_result_time);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
