package au.edu.jcu.cp3406.designthinkingiii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button mLogOutBtn;
    private FirebaseAuth mAuth;
    private ImageView videoCallButton;
    private TextView mUserPhone;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogOutBtn = findViewById(R.id.log_out_btn);
        videoCallButton= findViewById(R.id.videocall);
        mAuth = FirebaseAuth.getInstance();
        mUserPhone = findViewById(R.id.text_phone);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser != null;
        String phoneNumber = currentUser.getPhoneNumber();
        String inform = String.format("Your phone number is: %s", phoneNumber);

        mLogOutBtn.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        videoCallButton.setOnClickListener( v -> {
            startActivity(new Intent(MainActivity.this, VideoCallMainActivity.class));
        });

        mUserPhone.setText(inform);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}