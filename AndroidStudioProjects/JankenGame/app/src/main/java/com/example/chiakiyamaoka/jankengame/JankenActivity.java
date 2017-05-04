package com.example.chiakiyamaoka.jankengame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class JankenActivity extends AppCompatActivity {

    Button mSelectRockButton;
    Button mSelectScissorsButton;
    Button mSelectPaperButton;
    Button mCancelButton;
    TextView mEnemyHand;
    TextView mCountText;

    String mJankenSet[] = {
            "ぐー",
            "チョキ",
            "パー"
    };

    int mEnemyHandNum;
    int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janken);

        mSelectRockButton = (Button) findViewById(R.id.rock);
        mSelectScissorsButton = (Button) findViewById(R.id.scissors);
        mSelectPaperButton = (Button) findViewById(R.id.paper);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mEnemyHand = (TextView) findViewById(R.id.enemy_hand);
        mCountText = (TextView) findViewById(R.id.count);

        setEnemyHand();

        mSelectRockButton.setOnClickListener(onClickHand);
        mSelectScissorsButton.setOnClickListener(onClickHand);
        mSelectPaperButton.setOnClickListener(onClickHand);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setEnemyHand() {
        Random rand = new Random();
        mEnemyHandNum = rand.nextInt(mJankenSet.length);
        mEnemyHand.setText(mJankenSet[mEnemyHandNum]);
    }


    private View.OnClickListener onClickHand = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rock:
                    checkBattle(1);
                    break;
                case R.id.scissors:
                    checkBattle(2);
                    break;
                case R.id.paper:
                    checkBattle(0);
                    break;
                default:
                    break;
            }
            setEnemyHand();
        }
    };

    private void checkBattle(int i) {
        if (i == mEnemyHandNum) {
            mCount++;
            mCountText.setText(String.valueOf(mCount) + "勝目");
        }
    }

}
