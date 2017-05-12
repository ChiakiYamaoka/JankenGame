package com.example.chiakiyamaoka.jankengame;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class JankenActivity extends AppCompatActivity {

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
    long mLimitTime = 45000l;

    Handler handler = new Handler();
    Runnable updateTimer;

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

        startTimer();
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

    public void startTimer() {
        // startTimeの取得
        mStartTime = SystemClock.elapsedRealtime(); // 起動してからの経過時間（ミリ秒）

        // 一定時間ごとに現在の経過時間を表示
        // Handler -> Runnable(処理) -> UI
        updateTimer = new Runnable() {
            @Override
            public void run() {
                long diff =SystemClock.elapsedRealtime() - mStartTime;
                long t = mLimitTime - diff; // ミリ秒
                SimpleDateFormat sdf = new SimpleDateFormat("ss.SS", Locale.US);
                mTimeText.setText("Time:" + sdf.format(t));
                handler.removeCallbacks(updateTimer);
                handler.postDelayed(updateTimer, 10);
                if (diff >= 44990l){
                    handler.removeCallbacks(updateTimer);
                }
            }
        };
        handler.postDelayed(updateTimer, 10);
    }

}
