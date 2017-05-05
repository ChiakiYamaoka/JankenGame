package com.example.chiakiyamaoka.jankengame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Random;

public class JankenActivity extends AppCompatActivity implements Runnable {

    Button mSelectRockButton;
    Button mSelectScissorsButton;
    Button mSelectPaperButton;
    Button mCancelButton;
    TextView mEnemyHand;
    TextView mCountText;
    TextView mTimeText;

    String mJankenSet[] = {
            "ぐー",
            "チョキ",
            "パー"
    };

    int mEnemyHandNum;
    int mCount = 0;
    long mStartTime;
    long mLimitTime = 6L;

    Thread thread = null;
    Handler handler = new Handler();
    SimpleDateFormat mDataFormat = new SimpleDateFormat("ss.SS");
    private long mPeriod = 10;
    private boolean mStopRun = false;

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
        mTimeText = (TextView) findViewById(R.id.time);

        startTime();
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

    private void startTime() {
        thread = new Thread(this);
        thread.start();
        mStartTime = System.currentTimeMillis();
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

    @Override
    public void run() {

        while (!mStopRun) {
            try {
                Thread.sleep(mPeriod);
            } catch (InterruptedException e) {
                e.printStackTrace();
                mStopRun = true;
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long endTime = System.currentTimeMillis();
                    long elapseTime = (mLimitTime - endTime - mStartTime);
                    /*if (elapseTime < 0L) {
                        mStopRun = true;
                    }*/
                    mTimeText.setText("Time:" + mDataFormat.format(elapseTime));
                }
            });
        }
    }
}
