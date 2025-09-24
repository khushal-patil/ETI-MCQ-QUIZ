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

public class Chapter2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_chapter2);

        question = findViewById(R.id.question);

        no_counter = findViewById(R.id.no_counter);
        options_layout = findViewById(R.id.options_layout);
        next_btn = findViewById(R.id.next_btn);
        backgroundMusic = MediaPlayer.create(this, R.raw.backgroundmusic2);
        playerCorrect = MediaPlayer.create(this, R.raw.right);
        playerWrong = MediaPlayer.create(this,R.raw.wrong);

//        backgroundMusic.setLooping(true);
//        backgroundMusic.start();

        list = new ArrayList<>();
        //        Question 01
        list.add(new QuestionModel
                (       "IoT devices are various types, for instance______________",
                        "Wearable sensors",
                        "Wearable sensors",
                        "LED lights",
                        "All of the above",
                        "All of the above"));

        //        Question 02
        list.add(new QuestionModel
                (       "______ is a collection of WLAN communication standards.",
                        "IEEE 802.3",
                        "IEEE 802.11",
                        "IEEE 802.16",
                        "IEEE 802.15.4",
                        "IEEE 802.11"));

        //        Question 03
        list.add(new QuestionModel
                (       "What is the size of the IPv6 Address?",
                        "32 bits",
                        "64 bits",
                        "128 bits",
                        "256 bits",
                        "128 bits"));

        //        Question 04
        list.add(new QuestionModel
                (       "TCP and UDP are called?",
                        "Application protocols",
                        "Session protocols",
                        "Transport protocols",
                        "Network protocols",
                        "Transport protocols"));

        //        Question 05
        list.add(new QuestionModel
                (       "Which is not an IoT communication model",
                        "Request-Response",
                        "Publish-Subscribe",
                        "Push-Producer",
                        "Exclusive Pair",
                        "Push-Producer"));


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
                    Intent scoreintent = new Intent(Chapter2.this, ScoreActivity.class);
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
            //  StyleableToast.makeText(getApplicationContext(), "Right Answer", R.style.rightToast1).show();
        } else {
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctoption = (Button) options_layout.findViewWithTag(list.get(position).getCorrectAns());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e9913")));
            playerWrong.start();
            //  StyleableToast.makeText(getApplicationContext(), "Wrong Answer", R.style.wrongToast).show();
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Chapter2.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    }
