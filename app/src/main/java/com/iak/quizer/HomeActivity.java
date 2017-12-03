package com.iak.quizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    // View edit text username
    private EditText editTextUsername;
    // View button submit
    private Button buttonSubmit;
    // Variable string username
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Temukan view edit text username dengan id edit_text_user_name_activity_home
        editTextUsername = findViewById(R.id.edit_text_user_name_activity_home);

        // Temukan view button submit dengan id button_submit_activity_home
        buttonSubmit = findViewById(R.id.button_submit_activity_home);

        // Beri listener on click ke button submit
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Semua kode berikut akan di-execute ketika button submit
                // di tekan

                // Ambil nilai username dari editTextUsername
                username = editTextUsername.getText().toString();
                if (username.isEmpty()) {
                    // Jika username masih belum di isi
                    Toast.makeText(
                            HomeActivity.this,
                            "Anda belum mengisi username",
                            Toast.LENGTH_LONG
                    ).show();
                } else {
                    // Jika username sudah di isi
                    // Pindah ke activity berikutnya
                    Intent intentQuestion1Activity = new Intent(
                            HomeActivity.this,
                            Question1Activity.class
                    );

                    // Kirimkan variable username ke activity berikutnya
                    intentQuestion1Activity.putExtra(
                            "username",
                            username
                    );

                    // Mulai pindah ke activity berikutnya
                    startActivity(intentQuestion1Activity);
                }
            }
        });
    }
}
