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

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdt,passwordEdt;
    private Button loginBtn;
    private TextView signTv;
    private String email,password;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Auth=FirebaseAuth.getInstance();


        this.firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
            }
        };

        emailEdt=findViewById(R.id.emailEdt);
        passwordEdt=findViewById(R.id.passwordEdt);
        signTv=findViewById(R.id.toSignUpTv);
        loginBtn=findViewById(R.id.loginBtn);

        signTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=emailEdt.getText().toString();
                password=passwordEdt.getText().toString();

                if(email.isEmpty()&&password.isEmpty()){
                    emailEdt.setError("ENTER YOUR EMAIL");
                    emailEdt.setError("ENTER YOUR PASSWORD");
                    Toast.makeText(LoginActivity.this, "YOU FORGOT TO ENTER YOUR EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
                }else{


                    Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "ERROR SIGNING IN", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                return;

                            }
                        }
                    });
                }

            }
        });


    }

    public void onStart() {
        super.onStart();
        Auth.addAuthStateListener(this.firebaseAuthStateListener);
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
       Auth.removeAuthStateListener(this.firebaseAuthStateListener);
    }
}