package xw.jnisample;

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

}
