#include <jni.h>

//For first API key
JNIEXPORT jstring JNICALL Java_me_ahch_pixabay_1mage_1search_MainActivity_getPaxabayApi(JNIEnv *env, jobject instance) {


return (*env)-> NewStringUTF(env, "28339456-15244e863297ae76d408392b1");
}
