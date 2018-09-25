package cn.readsense.com.calendardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @description: TODO
 * @author: dongyiming
 * @date: 2018/9/25 2:08
 * @version: v1.0
 */
public class StartActivity extends AppCompatActivity {

    private TextView time1;
    private TextView time2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        TextView btn = (Button) findViewById(R.id.btn);
        time1.setText("2018年6月15日");
        time2.setText("2018年7月19日");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startActivity(StartActivity.this
                        , time1.getText().toString()
                        , time2.getText().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String startTime = data.getStringExtra("startTime");
            String endTime = data.getStringExtra("endTime");
            time1.setText(startTime);
            time2.setText(endTime);
        }
    }
}
