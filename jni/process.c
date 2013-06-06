#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include <string.h>
#include "com_jerome_jni_JNIProcess.h"
#include <android/log.h>
#include <malloc.h>
#define LOG_TAG "System.out.c"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

/**
 * 工具方法
 * 返回值 char* 这个代表char数组的首地址
 *  Jstring2CStr 把java中的jstring的类型转化成一个c语言中的char 字符串
 */
char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
{
	 char*   rtn   =   NULL;
	 jclass   clsstring   =   (*env)->FindClass(env,"java/lang/String");
	 jstring   strencode   =   (*env)->NewStringUTF(env,"GB2312");
	 jmethodID   mid   =   (*env)->GetMethodID(env,clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
	 jbyteArray   barr=   (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); 
	 jsize   alen   =   (*env)->GetArrayLength(env,barr);
	 jbyte*   ba   =   (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
	 if(alen   >   0)
	 {
	  rtn   =   (char*)malloc(alen+1);         
	  memcpy(rtn,ba,alen);
	  rtn[alen]=0;
	 }
	 (*env)->ReleaseByteArrayElements(env,barr,ba,0);  
	 return rtn;
}

/*
 * Class:     com_jerome_jni_JNIProcess
 * Method:    getInfo
 * Signature: (Ljava/lang/String;I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_jerome_jni_JNIProcess_getInfo
  (JNIEnv * env, jclass jcls, jstring jname, jint jage)
{
	char* cname = Jstring2CStr(env,jname);
	char hello[7] = {'-','H','e','l','l','o','\0'};
	strcat(cname,hello);
	char age[10];
	int cage = jage;
	//itoa(cage,age,10);
	//strcat(cname,age);
	return (*env)->NewStringUTF(env,cname);
}

/*
 * Class:     com_jerome_jni_JNIProcess
 * Method:    getReverseByte
 * Signature: (B)I
 */
JNIEXPORT jint JNICALL Java_com_jerome_jni_JNIProcess_getReverseByte
	(JNIEnv * env, jclass jcls, jbyte b){
}