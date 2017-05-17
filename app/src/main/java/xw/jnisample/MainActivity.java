package xw.jnisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "JNI SAMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);

        JniUtils jniUtils = new JniUtils();

        tv.setText(jniUtils.stringFromJNI());
        List l = jniUtils.createUserList(10);

        User user = new User(18, "java");
        jniUtils.modifyUser(user);
        Log.e(TAG, l.toString());
        Log.e(TAG, "user--> " + user.toString());
    }


}
