package kong.qingwei.countdowntimerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TimeCountUtil mTimeCountUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mTimeCountUtil = new TimeCountUtil(mButton, 5000, 1000);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeCountUtil.start();
                // TODO 请求验证码
                Toast.makeText(getApplicationContext(), "请求验证码", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
