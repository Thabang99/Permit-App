package bw.ac.biust.csis.st17001132.comp341.CheckPermit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bw.ac.biust.csis.st17001132.comp341.R;

public class PermitViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView userId, departureZone, departureLocation,destinationZone,destinationLocation,status;

    public PermitViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        userId = itemView.findViewById(R.id.userId);
        departureZone=itemView.findViewById(R.id.departureZoneI);
        departureLocation=itemView.findViewById(R.id.departureLocationI);
        destinationZone=itemView.findViewById(R.id.destinationZoneI);
        destinationLocation=itemView.findViewById(R.id.destinationLocationI);
        status=itemView.findViewById(R.id.statusPermit);

    }


    @Override
    public void onClick(View view) {

    }
}
