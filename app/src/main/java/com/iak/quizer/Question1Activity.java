package com.iak.quizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Question1Activity extends AppCompatActivity {

    private RadioGroup radioGroupAnswer;
    private String username;
    private boolean isChooseAnswer;
    private boolean isRightAnswer;
    private int totalRightAnswer;
    private int totalWrongAnswer;
    private ArrayList<Integer> listChooseAnswerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        // Ambil nilai username dari activity sebelumnya
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

        radioGroupAnswer = findViewById(R.id.radio_group_answer_activity_question1);

        // Set default false -> Awalnya belum memilih jawaban sama sekali
        isChooseAnswer = false;

        // Ini question pertama jadi, nilai totalRightAnswer
        // dan totalWrongAnswer itu sama dengan nol
        totalRightAnswer = 0;
        totalWrongAnswer = 0;

        // Dan karena ini question pertama jadi, nilai objek collection listChooseAnswerUser menjadi kosong
        listChooseAnswerUser = new ArrayList<>();


        // Beri listener on checked change
        radioGroupAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.radio_button_answer_a_activity_question1:
                        isRightAnswer = true;
                        isChooseAnswer = true;
                        break;
                    case R.id.radio_button_answer_b_activity_question1:
                        isRightAnswer = false;
                        isChooseAnswer = true;
                        break;
                    case R.id.radio_button_answer_c_activity_question1:
                        isRightAnswer = false;
                        isChooseAnswer = true;
                        break;
                    case R.id.radio_button_answer_d_activity_question1:
                        isRightAnswer = false;
                        isChooseAnswer = true;
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_next_question:
                if (!isChooseAnswer) {
                    Toast.makeText(
                            Question1Activity.this,
                            "Anda belum memilih jawabannya",
                            Toast.LENGTH_LONG
                    ).show();
                } else {
                    if (isRightAnswer) {
                        totalRightAnswer = totalRightAnswer + 1;

                        // Anggap jika jawaban si pengguna benar maka,
                        // masukkan nilai 1 ke dalam listChooseAnswer
                        // Ini nanti dipakai ketika di ScoreActivity
                        // agar si aplikasi tahu mana-mana saja jawaban si pegguna yang benar
                        listChooseAnswerUser.add(1);
                    } else {
                        totalWrongAnswer = totalWrongAnswer + 1;

                        // Anggap jika jawaban si pengguna salah maka,
                        // masukkan nilai 0 ke dalam listChooseAnswer
                        // Ini nanti dipakai ketika di ScoreActivity
                        // agar si aplikasi tahu mana-mana saja jawaban si pengguna yang salah
                        listChooseAnswerUser.add(0);
                    }

                    Intent intentQuestion2Activity = new Intent(
                            Question1Activity.this,
                            Question2Activity.class
                    );
                    intentQuestion2Activity.putExtra("username", username);
                    intentQuestion2Activity.putExtra("totalRightAnswer", totalRightAnswer);
                    intentQuestion2Activity.putExtra("totalWrongAnswer", totalWrongAnswer);
                    intentQuestion2Activity.putIntegerArrayListExtra("listChooseAnswerUser", listChooseAnswerUser);
                    startActivity(intentQuestion2Activity);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        /** nothing to do in here */
    }
}
