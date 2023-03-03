
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

Java_com_islam_tasks_core_ArticlesKeys_geApiUrl(JNIEnv *env, jobject object) {
    return env->NewStringUTF("https://newsapi.org/v1/");
}

extern "C" JNIEXPORT jstring

Java_com_islam_tasks_core_ArticlesKeys_geApiKey(JNIEnv *env, jobject object) {
    return env->NewStringUTF("533af958594143758318137469b41ba9");
}