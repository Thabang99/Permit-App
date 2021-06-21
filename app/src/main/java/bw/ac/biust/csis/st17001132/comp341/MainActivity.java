package bw.ac.biust.csis.st17001132.comp341;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import bw.ac.biust.csis.st17001132.comp341.CheckPermit.CheckPermitActivity;

public class MainActivity extends AppCompatActivity {

    private EditText departureZoneEdt,departureLocationEdt,destinationZoneEdt,destinationLocationEdt,reasonEdt,purposeEdt;
    private TextView departureLocationTxt, destinationLocationTxt, approvedTxt;
    private String departureZone,departureLocation,destinationZone,destinationLocation, isApproved,reason,purpose;
    private Button applyBtn,checkPermitBtn;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Auth = FirebaseAuth.getInstance();

        departureZoneEdt= findViewById(R.id.departureZone);
        departureLocationEdt= findViewById(R.id.departureLocation);
        destinationLocationEdt= findViewById(R.id.destinationLocation);
        destinationZoneEdt=findViewById(R.id.destinationZone);
        applyBtn=findViewById(R.id.applyPermitBtn);
        checkPermitBtn=findViewById(R.id.checkPermitBtn);
        reasonEdt=findViewById(R.id.reasonEdt);
        purposeEdt=findViewById(R.id.purposeEdt);


        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToBD();

            }
        });


    }

    private void sendToBD() {
        departureZone=departureZoneEdt.getText().toString();
        departureLocation=departureLocationEdt.getText().toString();
        destinationZone=destinationZoneEdt.getText().toString();
        destinationLocation=destinationLocationEdt.getText().toString();
        reason=reasonEdt.getText().toString();
        purpose=purposeEdt.getText().toString();
        String userId= Auth.getCurrentUser().getUid();
        isApproved = "PENDING....";

        DatabaseReference permitDb = FirebaseDatabase.getInstance().getReference().child("Permits").child(userId).child(reason);
        Map userInfo = new HashMap<>();
        userInfo.put("Departure Zone", departureZone);
        userInfo.put("Departure Location", departureLocation);
        userInfo.put("Destination Zone", destinationZone);
        userInfo.put("Destination Location", destinationLocation);
        userInfo.put("Is Approved",isApproved);
        userInfo.put("Reason",reason);
        userInfo.put("Purpose",purpose);
        permitDb.updateChildren(userInfo);
        departureZoneEdt.setText(null);
                departureLocationEdt.setText(null);
        destinationZoneEdt.setText(null);
                destinationLocationEdt.setText(null);
        reasonEdt.setText(null);
                purposeEdt.setText(null);

        Toast.makeText(MainActivity.this, "PERMIT APPLICATION SENT!", Toast.LENGTH_SHORT).show();

    }

    public void check(View view) {

        Intent intent= new Intent(MainActivity.this, CheckPermitActivity.class);
        startActivity(intent);

    }

    public void signOut(View view) {
        Auth.signOut();
        Intent intent= new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        return;

    }
}