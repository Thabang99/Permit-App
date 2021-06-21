package bw.ac.biust.csis.st17001132.comp341;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private TextView toLoginTv;
    private EditText firstNameEdt,surnameEdt,phoneEdt,dateOfBirthEdt,idEdt,nationalityEdt,physicalAddressEdt,emailRegEdt,passwordRegEdt;
    private Button registerBtn;
    private String name,surname,phone,dateOfBirth,id,nationality,physicalAddress,email,password;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Auth = FirebaseAuth.getInstance();

        toLoginTv=findViewById(R.id.toLoginTv);
        firstNameEdt=findViewById(R.id.fullnamesEdt);
        surnameEdt=findViewById(R.id.surnameEdt);
        phoneEdt= findViewById(R.id.phoneNoEdt);
        dateOfBirthEdt= findViewById(R.id.dobEdt);
        idEdt= findViewById(R.id.idEdt);
        nationalityEdt= findViewById(R.id.nationalityEdt);
        physicalAddressEdt= findViewById(R.id.addressEdt);
        emailRegEdt=findViewById(R.id.emailRegEdt);
        passwordRegEdt=findViewById(R.id.passwordRegEdt);
        registerBtn=findViewById(R.id.signUpBtn);

        toLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               email = emailRegEdt.getText().toString();
                 password = passwordRegEdt.getText().toString();
                name = firstNameEdt.getText().toString();
                surname = surnameEdt.getText().toString();
                phone = phoneEdt.getText().toString();
                dateOfBirth = dateOfBirthEdt.getText().toString();
                id = idEdt.getText().toString();
                nationality = nationalityEdt.getText().toString();
                physicalAddress = physicalAddressEdt.getText().toString();


                Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                        }else{
                            String userId = Auth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                            Map userInfo = new HashMap<>();
                            userInfo.put("name", name);
                            userInfo.put("surname", surname);
                            userInfo.put("phone", phone);
                            userInfo.put("date of birth", dateOfBirth);
                            userInfo.put("id Number", id);
                            userInfo.put("nationality", nationality);
                            userInfo.put("physical address", physicalAddress);
                            currentUserDb.updateChildren(userInfo);


                            Auth.signOut();
                            Toast.makeText(RegistrationActivity.this, "successfully registered, proceed to log in", Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    }
                });



            }
        });



    }
}