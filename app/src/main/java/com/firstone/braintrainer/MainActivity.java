package com.firstone.braintrainer;

import android.os.CountDownTimer;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class MainActivity extends AppCompatActivity {
    TextView question;
    TextView timeLeft;
    ListView answerList;
    Button controller;
    int a,b,j,score,totalQuestion;
    TextView scoreBoard;

    public void startgame(){


        Log.i("check","In start game");

        a=(int) (random()*(30)+1);
        b=(int) (random()*(30)+1);
        int c;
        question.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = ?");

        ArrayList<Integer>answer= new ArrayList<Integer>();
        j=(int) (random()*(4));

        for(int i=0;i<4;i++){
            c=(int) (random() * (31));
            if(i!=j&&c!=(a+b)) {
                answer.add(c);

            }
            else
                answer.add(a+b);
        }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_activated_1,answer);
        answerList.setAdapter(arrayAdapter);
       // Log.i("check",Integer.toString(gameState));
        Selection();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreBoard=findViewById(R.id.scoreBoard);
        answerList=findViewById(R.id.answerList);
        question = findViewById(R.id.question);
        controller= findViewById(R.id.controller);
        timeLeft=findViewById(R.id.timeLeft);

        controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("check","botton pressed");
                if (controller.getText().equals("START")){
                    //gameState=1;
                    score=totalQuestion=0;
                    question.setVisibility(View.VISIBLE);
                    answerList.setVisibility(View.VISIBLE);
                    controller.setVisibility(View.GONE);
                    controller.setText("RESET");
                    timeLeft.setText("30s");
                    Timing();
                }
                else{
                    controller.setText("START");
                }
                }
            }
        );

    }

    public void Timing(){
        startgame();
        CountDownTimer timer = new CountDownTimer(10100,1000) {
            @Override
            public void onTick(long l) {
                timeLeft.setText(Long.toString(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                        timeLeft.setText("0s");
                        controller.setVisibility(View.VISIBLE);
                        controller.setText("YOU SCORED "+score+" OUT OF "+totalQuestion+" QUESTIONS");
                        answerList.setVisibility(View.GONE);
                        question.setVisibility(View.GONE);
                        //gameState=0;
            }
        }.start();
    }

    public void Selection(){

        answerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                totalQuestion++;
                if(i==j) {
                    score++;
                    Toast.makeText(MainActivity.this, "Correct answer", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                }
                scoreBoard.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestion));
            startgame();
            }
        });
    }
}
