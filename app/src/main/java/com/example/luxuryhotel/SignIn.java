package com.example.luxuryhotel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private String CHANNEL_ID = "Channel 1";
    private FirebaseAuth authentication;

    SharedPreferences preferences;
    EditText TxtEmail, TxtPassword, TxtNama, TxtUsername;
    Button btnSignUp, btnSignIn;
    String emailstring, namastring, usernamestring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        loadPreferences();
        setProfile();

        authentication = FirebaseAuth.getInstance();

        TxtEmail = findViewById(R.id.editTxtEmail);
        TxtPassword = findViewById(R.id.editTxtPassword);
        TxtNama = findViewById(R.id.editTxtNama);
        TxtUsername = findViewById(R.id.editTxtUsername);
        preferences = getSharedPreferences("UserHotel", Context.MODE_PRIVATE);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!TxtEmail.getText().toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    }else if (TxtPassword.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please Fill Password", Toast.LENGTH_SHORT).show();
                    }else if (TxtPassword.getText().toString().length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password Too Short", Toast.LENGTH_SHORT).show();
                    }else if (!TxtEmail.getText().toString().matches("") && TxtPassword.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }else if(TxtNama.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please fill Name Correctly!", Toast.LENGTH_SHORT).show();
                    }else if(TxtUsername.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please fill Username Correctly!", Toast.LENGTH_SHORT).show();
                    } else {
                        signUp();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namastring = TxtNama.getText().toString();
                usernamestring = TxtUsername.getText().toString();
                emailstring = TxtEmail.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                try {
                    if (!TxtEmail.getText().toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    }else if (TxtPassword.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please Fill Password", Toast.LENGTH_SHORT).show();
                    }else if (TxtPassword.getText().toString().length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password Too Short", Toast.LENGTH_SHORT).show();
                    }else if (!TxtEmail.getText().toString().matches("") && TxtPassword.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }else if(TxtNama.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please fill Name Correctly!", Toast.LENGTH_SHORT).show();
                    }else if(TxtUsername.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please fill Username Correctly!", Toast.LENGTH_SHORT).show();
                    } else {
                        signIn();
                        editor.putString("Nama", namastring);
                        editor.putString("Email",emailstring);
                        editor.putString("Username", usernamestring);
                        editor.commit();
                        Toast.makeText(SignIn.this, "Profile saved", Toast.LENGTH_SHORT).show();
                        savePreferences();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void setProfile(){
        EditText usernameTxt = findViewById(R.id.editTxtUsername);
        EditText namaTxt = findViewById(R.id.editTxtNama);
        EditText emailTxt = findViewById(R.id.editTxtEmail);
        usernameTxt.setText(usernamestring);
        namaTxt.setText(namastring);
        emailTxt.setText(emailstring);
    }

    private void loadPreferences(){
        preferences = getSharedPreferences("UserHotel", Context.MODE_PRIVATE);
        if(preferences!=null){
            namastring = preferences.getString("Nama", "");
            emailstring = preferences.getString("Email", "");
            usernamestring = preferences.getString("Username", "");
        }
    }

    private void savePreferences() {
        final EditText usernameTxt = findViewById(R.id.editTxtUsername);
        final EditText namaTxt = findViewById(R.id.editTxtNama);
        final EditText emailTxt = findViewById(R.id.editTxtEmail);
        SharedPreferences.Editor editor = preferences.edit();
        final Intent Main = new Intent(this, MainActivity.class);
        if(!namaTxt.getText().toString().isEmpty() && !usernameTxt.getText().toString().isEmpty()
        && !emailTxt.getText().toString().isEmpty()) {
            editor.putString("Nama", namaTxt.getText().toString());
            editor.putString("Email",emailTxt.getText().toString());
            editor.putString("Username",usernameTxt.getText().toString());
        }
    }

    private void signIn() {
        //showProgressDialog();
        String email = TxtEmail.getText().toString();
        String password = TxtPassword.getText().toString();
        String nama = TxtNama.getText().toString();
        String username = TxtUsername.getText().toString();

        authentication.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //hideProgressDialog();

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Yes, login was succes :)", Toast.LENGTH_SHORT).show();
                            createNotificationChannel();
                            addSignInNotif();
                            Intent i = new Intent(SignIn.this, HomePage.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "No, login was failed :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signUp() {
        String email = TxtEmail.getText().toString();
        String password = TxtPassword.getText().toString();
        String nama = TxtNama.getText().toString();
        String username = TxtUsername.getText().toString();

        authentication.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //hideProgressDialog();

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Congratulations, your register complete :)", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Ooohh noo, your register failed :(", Toast.LENGTH_SHORT).show();
                        }

                        TxtEmail.getText().clear();
                        TxtPassword.getText().clear();
                        TxtNama.getText().clear();;
                        TxtUsername.getText().clear();
                    }
                });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addSignInNotif() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Login was success")
                .setContentText("Enjoy using this application")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
