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

public class Question2Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_question2);

        // Ambil nilai username dari activity sebelumnya
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

        // Ambil nilai totalRightAnswer dan
        // totalWrongAnswer dari activity sebelumnya
        totalRightAnswer = bundle.getInt("totalRightAnswer");
        totalWrongAnswer = bundle.getInt("totalWrongAnswer");

        // Ambil nilai listChooseAnswerUser dari activity sebelumnya
        listChooseAnswerUser = bundle.getIntegerArrayList("listChooseAnswerUser");

        radioGroupAnswer = findViewById(R.id.radio_group_answer_activity_question2);

        isChooseAnswer = false;

        // Beri listener on checked change  ke RadioGroup Answer
        radioGroupAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.radio_button_answer_a_activity_question2:
                        // Jika user memilih jawaban yang salah
                        isRightAnswer = false;
                        isChooseAnswer = true;
                        break;
                    case R.id.radio_button_answer_b_activity_question2:
                        // Jika user memilih jawaban yang salah
                        isRightAnswer = false;
                        isChooseAnswer = true;
                        break;
                    case R.id.radio_button_answer_c_activity_question2:
                        // Jika user memilih jawaban yang benar
                        isRightAnswer = true;
                        isChooseAnswer = true;
                        break;
                    case R.id.radio_button_answer_d_activity_question2:
                        // Jika user memilih jawaban yang salah
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
                            Question2Activity.this,
                            "Anda belum memilih jawaban",
                            Toast.LENGTH_LONG
                    ).show();
                } else {
                    if (isRightAnswer) {
                        totalRightAnswer = totalRightAnswer + 1;
                        listChooseAnswerUser.add(1);
                    } else {
                        totalWrongAnswer = totalWrongAnswer + 1;
                        listChooseAnswerUser.add(0);
                    }

                    Intent intentScoreActivity = new Intent(
                            Question2Activity.this,
                            ScoreActivity.class
                    );
                    intentScoreActivity.putExtra("username", username);
                    intentScoreActivity.putExtra("totalRightAnswer", totalRightAnswer);
                    intentScoreActivity.putExtra("totalWrongAnswer", totalWrongAnswer);
                    intentScoreActivity.putIntegerArrayListExtra("listChooseAnswerUser", listChooseAnswerUser);
                    startActivity(intentScoreActivity);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        /** nothing to do in here */
        // Agar si user tidak bisa kembali ke activity sebelumnya
    }
}
