package com.example.chiakiyamaoka.jankengame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int mCount;
    Button mRetryButton;
    Button mCloseButton;
    TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        mCount = intent.getIntExtra("COUNT", 0);

        mRetryButton = (Button) findViewById(R.id.retry_button);
        mCloseButton = (Button) findViewById(R.id.close_button);
        mResultText = (TextView) findViewById(R.id.result_text);

        mResultText.setText(String.valueOf(mCount) + "勝");

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, JankenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
