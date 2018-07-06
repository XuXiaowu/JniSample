package xw.jnisample;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by xuxiaowu on 2017/5/12.
 */

public class JniUtils {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native List<User> createUserList(int size);

    public native User modifyUser(User user);

    private native void initCBlur1(int[] pix, int w, int h, int r);

    private native void initCBlur2(int[] pix, int w, int h, int r);

    //分别在x轴 和 y轴方向上进行高斯模糊
    public Bitmap gaussBlurUseGauss(Bitmap bitmap, int radius) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        //生成一张新的图片
        Bitmap outBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);

        //定义一个临时数组存储原始图片的像素 值
        int[] pix = new int[w * h];

        //将图片像素值写入数组
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        //进行模糊
        initCBlur1(pix, w, h, radius);

        //将数据写入到 图片
        outBitmap.setPixels(pix, 0, w, 0, 0, w, h);

        //返回结果
        return outBitmap;
    }

    //利用均值模糊 逼近 高斯模糊
    public Bitmap gaussBlurUseAvg(Bitmap bitmap, int radius) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        //生成一张新的图片
        Bitmap outBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        //定义一个临时数组存储原始图片的像素 值
        int[] pix = new int[w * h];

        //将图片像素值写入数组
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        //进行模糊
        initCBlur2(pix, w, h, radius);

        //将数据写入到 图片
        outBitmap.setPixels(pix, 0, w, 0, 0, w, h);

        //返回结果
        return outBitmap;
    }


}
