package bw.ac.biust.csis.st17001132.comp341.CheckPermit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import bw.ac.biust.csis.st17001132.comp341.R;

public class CheckPermitActivity extends AppCompatActivity {

    private RecyclerView mRecicyleView;
    private RecyclerView.Adapter permitAdapter;
    private RecyclerView.LayoutManager permitsLayout;
    private FirebaseAuth mAuth;
    private String userId,date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permit);



        mRecicyleView = findViewById(R.id.recyclerView);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mAuth = FirebaseAuth.getInstance();
        mRecicyleView.setNestedScrollingEnabled(false);
        mRecicyleView.setHasFixedSize(true);
        permitsLayout =new LinearLayoutManager(CheckPermitActivity.this);
        mRecicyleView.setLayoutManager(permitsLayout);
        permitAdapter = new PermitAdapter(getDatasetPermits(), CheckPermitActivity.this);
        mRecicyleView.setAdapter(permitAdapter);

        getUserId();

    }

    private void getUserId() {

        DatabaseReference userIdCheck = FirebaseDatabase.getInstance().getReference().child("Permits").child(userId);
        userIdCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot id : snapshot.getChildren()){
                        FetchInformation(id.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void FetchInformation(String key) {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Permits").child(userId).child(key);
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    // String userId = snapshot.getValue().toString();
                    String userId = snapshot.getKey();
                    String departureZone = "";
                    String departureLocation = "";
                    String destinationZone = "";
                    String destinationLocation = "";
                    String status = "";

                    //if (snapshot.child("Make").getValue().toString() != null){
                    //  userId = snapshot.child("Make").getValue().toString();
                    // }

                    if (snapshot.child("Departure Zone").getValue().toString() != null){
                        departureZone = snapshot.child("Departure Zone").getValue().toString();
                    }

                    if (snapshot.child("Departure Location").getValue().toString() != null){
                        departureLocation = snapshot.child("Departure Location").getValue().toString();
                    }
                    if (snapshot.child("Destination Zone").getValue().toString() != null){
                        destinationZone = snapshot.child("Destination Zone").getValue().toString();
                    }
                    if (snapshot.child("Destination Location").getValue().toString() != null){
                        destinationLocation = snapshot.child("Destination Location").getValue().toString();
                    }
                    if (snapshot.child("Is Approved").getValue().toString() != null){
                        status = snapshot.child("Is Approved").getValue().toString();
                    }



                    PermitObject obj = new PermitObject(userId,departureZone,departureLocation,destinationZone,destinationLocation, status);
                    resultPermits.add(obj);

                    permitAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }

    private ArrayList<PermitObject> resultPermits = new ArrayList<PermitObject>();
    private List<PermitObject> getDatasetPermits() {
        return resultPermits;
    }

}
