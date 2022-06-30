#include <jni.h>


JNIEXPORT jstring JNICALL
Java_me_ahch_image_1search_1data_repository_SearchRepositoryImpl_getPixabayApi(JNIEnv *env,
                                                                               jobject thiz) {
    return (*env)->NewStringUTF(env, "28339456-15244e863297ae76d408392b1");
}