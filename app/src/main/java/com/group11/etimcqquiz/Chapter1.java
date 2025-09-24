package com.group11.etimcqquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Chapter1 extends AppCompatActivity {
    private TextView question, no_counter;
    private LinearLayout options_layout;
    private Button next_btn;
    private int count = 0;
    private List<QuestionModel> list;
    private int position = 0;
    private int score;
    private MediaPlayer backgroundMusic, playerCorrect, playerWrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter1);

        question = findViewById(R.id.question);
        no_counter = findViewById(R.id.no_counter);
        options_layout = findViewById(R.id.options_layout);
        next_btn = findViewById(R.id.next_btn);
        backgroundMusic = MediaPlayer.create(this, R.raw.backgroundmusic2);
        playerCorrect = MediaPlayer.create(this, R.raw.right);
        playerWrong = MediaPlayer.create(this,R.raw.wrong);

        list = new ArrayList<>();
        //        Question 01
        list.add(new QuestionModel
                (       "Which of these schools was not among the early leaders in AI research?",
                        "Dartmouth University",
                        "Harvard University",
                        "Massachusetts Institute of Technology",
                        "Stanford University",
                        "Harvard University"));

        //        Question 02
        list.add(new QuestionModel
                (       "What is the term used for describing the judgmental or commonsense part of the problem solving?",
                        "Heuristic",
                        "Value-based",
                        "Critical",
                        "Analytical",
                        "Heuristic"));

        //        Question 03
        list.add(new QuestionModel
                (       "The conference that launched the AI revolution in 1956 was held at:",
                        "Dartmouth",
                        "Harvard",
                        "New York",
                        "None of the above",
                        "Dartmouth"));

        //        Question 04
        list.add(new QuestionModel
                (       "What is Artificial intelligence?",
                        "Putting your intelligence into Computer",
                        "Programming with your own intelligence",
                        "Making a Machine intelligent",
                        "Putting more memory into Computer",
                        "Making a Machine intelligent"));

        //        Question 05
        list.add(new QuestionModel
                (       "Who is a father of AI?",
                        "Alain Colmerauer",
                        "John McCarthy",
                        "Nicklaus Wirth",
                        "Seymour Papert",
                        "John McCarthy"));

        for (int i = 0; i < 4; i++) {
            options_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer((Button) view);
                }
            });
        }

        playAnim(question, 0, list.get(position).getQuestion());
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_btn.setEnabled(false);
                next_btn.setAlpha(0.07f);
                enableoption(true);
                position++;
                if (position == list.size()) {
                    Intent scoreintent = new Intent(Chapter1.this, ScoreActivity.class);
                    finish();
                    scoreintent.putExtra("score", score);
                    scoreintent.putExtra("total", list.size());
                    startActivity(scoreintent);
                    return;
                }
                count = 0;
                playAnim(question, 0, list.get(position).getQuestion());
            }
        });
    }

    private void enableoption(boolean enable) {

        for (int i =0;i <4;i++){
            options_layout.getChildAt(i).setEnabled(enable);
            if (enable){
                options_layout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3d3636")));
            }
        }
    }

    private void playAnim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(1).setDuration(500).setStartDelay(50)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (value == 0 && count < 4) {
                            String option = "";
                            if (count == 0) {
                                option = list.get(position).getOptionA();
                            } else if (count == 1) {
                                option = list.get(position).getOptionB();
                            } else if (count == 2) {
                                option = list.get(position).getOptionC();
                            } else if (count == 3) {
                                option = list.get(position).getOptionD();
                            }
                            playAnim(options_layout.getChildAt(count), 0, option);
                            count++;
                        }

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value == 0) {
                            try {
                                ((TextView) view).setText(data);
                                no_counter.setText(position + 1 + "/" + list.size());
                            } catch (ClassCastException ex) {
                                ((Button) view).setText(data);
                            }
                            view.setTag(data);
                            playAnim(view, 1, data);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
    }

    private void checkAnswer(Button selectedoption) {
        enableoption(false);
        next_btn.setEnabled(true);
        next_btn.setAlpha(1);
        if (selectedoption.getText().toString().equals(list.get(position).getCorrectAns())) {
            score++;
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e9913")));
            playerCorrect.start();
        } else {
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctoption = (Button) options_layout.findViewWithTag(list.get(position).getCorrectAns());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e9913")));
            playerWrong.start();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Chapter1.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
