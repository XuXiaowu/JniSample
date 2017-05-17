#include <jni.h>
#include <string>

/**
 * 修改User对象
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_xw_jnisample_JniUtils_modifyUser(JNIEnv *env, jobject instance, jobject user) {

    jclass userClass = env->FindClass("xw/jnisample/User");
    jmethodID setAgeMID = env->GetMethodID(userClass, "setAge", "(I)V");
    jmethodID setNameMID = env->GetMethodID(userClass, "setName", "(Ljava/lang/String;)V");
    jint age = 19;
    jstring name = env->NewStringUTF("native");
    env->CallVoidMethod(user, setAgeMID, age);
    env->CallVoidMethod(user, setNameMID, name);
    return user;
}

/**
 * 创建User的List集合
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_xw_jnisample_JniUtils_createUserList(JNIEnv *env, jobject instance, jint size) {

    jclass listClass = env->FindClass("java/util/ArrayList");
    if (listClass == NULL) {
        printf("listClass is NULL \n");
    }
    jmethodID list_construct = env->GetMethodID(listClass, "<init>", "()V");
    jobject listObj = env->NewObject(listClass, list_construct);
    jmethodID listAdd = env->GetMethodID(listClass, "add", "(Ljava/lang/Object;)Z");
    jclass userClass = env->FindClass("xw/jnisample/User");
    jmethodID user_construct = env->GetMethodID(userClass, "<init>", "(ILjava/lang/String;)V");

    for (int i = 0; i < size; ++i) {
        jstring name = env->NewStringUTF("Native" + i);
        jobject user = env->NewObject(userClass, user_construct, i, name);
        env->CallBooleanMethod(listObj, listAdd, user);
    }
    return listObj;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_xw_jnisample_JniUtils_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
