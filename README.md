#实用技巧——获取验证码的倒计时
##效果图
![G1](http://img.blog.csdn.net/20160512164308209)


**博客：**[CSDN](http://blog.csdn.net/q4878802/article/details/51385339)

##CountDownTimer

> 创建对象，调用start的方法，开始倒计时

```
/**
 * 第一个参数：总时长（毫秒）
 * 第二个参数：多久执行一次回调（毫秒）
 */
new CountDownTimer(10000, 1000) {
    @Override
    public void onTick(long millisUntilFinished) {
        // TODO 显示倒计时
    }

    @Override
    public void onFinish() {
        // TODO 倒计时结束
    }
}.start();
```


##封装

```
package kong.qingwei.countdowntimerdemo;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by kqw on 2016/5/11.
 * TimeCountUtil
 */
public class TimeCountUtil extends CountDownTimer {
    private Button mButton;

    public TimeCountUtil(Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mButton = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // 按钮不可用
        mButton.setEnabled(false);
        String showText = millisUntilFinished / 1000 + "秒后可重新发送";
        mButton.setText(showText);
    }

    @Override
    public void onFinish() {
        // 按钮设置可用
        mButton.setEnabled(true);
        mButton.setText("重新获取验证码");
    }
}
```


##Code(参考)

###测试类

```
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
```

###XML

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="kong.qingwei.countdowntimerdemo.MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@drawable/selector_button"
        android:text="点击发送验证码"/>
</RelativeLayout>
```

###drawable

* selector_button.xml

	```
	<?xml version="1.0" encoding="utf-8"?>
	<selector xmlns:android="http://schemas.android.com/apk/res/android">
	<!-- 可用状态 -->
	<item android:drawable="@drawable/shape_button_type1" android:state_enabled="true" />
	<!-- 不可用状态 -->
	<item android:drawable="@drawable/shape_button_type2" android:state_enabled="false" />
	</selector>
	```

* shape_button_type1.xml

	```
	<?xml version="1.0" encoding="utf-8"?>
	<shape xmlns:android="http://schemas.android.com/apk/res/android">
	    <solid android:color="#FF4583C9"/>
	    <corners android:radius="5dp"/>
	    <stroke android:width="1px" android:color="#FF4583C9" />
	</shape>
	```

* shape_button_type2.xml

	```
	<?xml version="1.0" encoding="utf-8"?>
	<shape xmlns:android="http://schemas.android.com/apk/res/android">
	    <solid android:color="#FFDDDDDD"/>
	    <corners android:radius="5dp"/>
	    <stroke android:width="1px" android:color="#FFDDDDDD" />
	</shape>
	```