package com.iak.quizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    private TextView textViewScore;
    private TextView textViewUsername;
    private ImageView imageViewRightAnswer1;
    private ImageView imageViewWrongAnswer1;
    private ImageView imageViewRightAnswer2;
    private ImageView imageViewWrongAnswer2;
    private String username;
    private int totalRightAnswer;
    private int totalWrongAnswer;
    private ArrayList<Integer> listChooseAnswerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Ambil nilai dari activity sebelumnya
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        totalRightAnswer = bundle.getInt("totalRightAnswer");
        totalWrongAnswer = bundle.getInt("totalWrongAnswer");
        listChooseAnswerUser = bundle.getIntegerArrayList("listChooseAnswerUser");

        // Temukan TextView dengan id text_view_score_actvity_score
        textViewScore = findViewById(R.id.text_view_score_activity_score);
        // Temukan TextView dengan id text_view_username_score_activity
        textViewUsername = findViewById(R.id.text_view_username_score_activity);
        // Temukan ImageView dengan id image_view_right_answer_question_1
        imageViewRightAnswer1 = findViewById(R.id.image_view_right_answer_question_1);
        // Temukan ImageView dengan id image_view_wrong_answer_question_1
        imageViewWrongAnswer1 = findViewById(R.id.image_view_wrong_answer_question_1);
        // Temukan ImageView dengan id image_view_right_answer_question_2
        imageViewRightAnswer2 = findViewById(R.id.image_view_right_answer_question_2);
        // Temukan ImageView dengan id image_view_wrong_answer_question_2
        imageViewWrongAnswer2 = findViewById(R.id.image_view_wrong_answer_question_2);

        // Rumus menghitung final score si user
        // score = (totalJawabanBenar / totalSoal) * 100
        int totalSoal = totalRightAnswer + totalWrongAnswer;
        double score = ((double) totalRightAnswer / totalSoal) * 100;

        // Untuk menghindari nilai koma yang banyak maka, gunakan Math.round untuk membulatkan nilainya
        score = Math.round(score);

        // Tampilkan nilai score ke dalam textViewScore
        String strScore = "Score: " + score;
        textViewScore.setText(strScore);

        // Tampilkan nilai username ke dalam textViewUsername
        textViewUsername.setText(username);

        // Gunakan perulangan untuk mengecek nilai setiap jawaban si pengguna
        // agar si aplikasi bisa tahu soal ke berapa jawaban si pengguna benar atau salah
        for (int a = 0; a < listChooseAnswerUser.size(); a++) {
            int tipeJawaban = listChooseAnswerUser.get(a);
            if (a == 0) {
                // Ini untuk soal nomor 1
                if (tipeJawaban == 1) {
                    // Jawaban pengguna benar
                    imageViewRightAnswer1.setVisibility(View.VISIBLE);
                    imageViewWrongAnswer1.setVisibility(View.INVISIBLE);
                } else {
                    // Jawaban pengguna salah
                    imageViewWrongAnswer1.setVisibility(View.VISIBLE);
                    imageViewRightAnswer1.setVisibility(View.INVISIBLE);
                }
            } else if (a == 1) {
                // Ini untuk soal nomor 2
                if (tipeJawaban == 1) {
                    // Jawaban pengguna benar
                    imageViewRightAnswer2.setVisibility(View.VISIBLE);
                    imageViewWrongAnswer2.setVisibility(View.INVISIBLE);
                } else {
                    // Jawaban pengguna salah
                    imageViewWrongAnswer2.setVisibility(View.VISIBLE);
                    imageViewRightAnswer2.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        /** nothing to do in here */
        // Agar si pengguna tidak bisa kembali ke activity sebelumnya
    }
}
