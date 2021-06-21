package bw.ac.biust.csis.st17001132.comp341.CheckPermit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bw.ac.biust.csis.st17001132.comp341.R;

public class PermitAdapter extends RecyclerView.Adapter<PermitViewHolder> {
    private List<PermitObject> permitsList;
    private Context context;

    public PermitAdapter(List<PermitObject> permitsList, CheckPermitActivity checkPermitActivity) {
        this.permitsList = permitsList;
        this.context = context;

    }


    @NonNull
    @Override
    public PermitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permit, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutView.setLayoutParams(layoutParams);
        PermitViewHolder PermitsViewHolder = new PermitViewHolder(layoutView);

        return PermitsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PermitViewHolder holder, int position) {
        holder.userId.setText(permitsList.get(position).getUserId());
        holder.departureZone.setText(permitsList.get(position).getDepartureZone());
        holder.departureLocation.setText(permitsList.get(position).getDepartureLocation());
        holder.destinationZone.setText(permitsList.get(position).getDestinationZone());
        holder.destinationLocation.setText(permitsList.get(position).getDestinationLocation());
        holder.status.setText(permitsList.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return  this.permitsList.size();
    }
}
