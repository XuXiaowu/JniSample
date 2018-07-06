package xw.jnisample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "JNI SAMPLE";

    private Button mCompressBtn;
    private ImageView mTestImageView1;
    private ImageView mTestImageView2;
    private JniUtils mJniUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        mCompressBtn = (Button) findViewById(R.id.compress_btn);
        mTestImageView1 = (ImageView) findViewById(R.id.test_img1);
        mTestImageView2 = (ImageView) findViewById(R.id.test_img2);

        mJniUtils = new JniUtils();

        tv.setText(mJniUtils.stringFromJNI());
        List l = mJniUtils.createUserList(10);

        User user = new User(18, "java");
        mJniUtils.modifyUser(user);
        Log.e(TAG, l.toString());
        Log.e(TAG, "user---> " + user.toString());

        mCompressBtn.setOnClickListener(mCompressBtnClickListener);
    }

    private View.OnClickListener mCompressBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img240);
            Log.e(TAG, "begin");
            Bitmap gsBitmap = mJniUtils.gaussBlurUseGauss(bitmap, 120);
            Bitmap avgBitmap = mJniUtils.gaussBlurUseAvg(bitmap, 120);
            Log.e(TAG, "end");
            mTestImageView1.setImageBitmap(gsBitmap);
            mTestImageView2.setImageBitmap(avgBitmap);


//            String sourcePath = "/storage/emulated/0/compress_test/source.jpg";
//            String targetPath = "/storage/emulated/0/compress_test/compress.jpg";
//            File file = new File(targetPath);
//            if (!file.exists()) {
//                try {
//                    file.createNewFile();
//                    Log.e("FK", file.length() + "");
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//            mJniUtils.compressBitmap(sourcePath, targetPath);
        }
    };


}
