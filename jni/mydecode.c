#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include <string.h>
#include "com_example_webdemo_util_H5Decode.h"
#include <android/log.h>
#include <malloc.h>
#include "md5c.h"
#define LOG_TAG "System.out.c"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
/*
 *jstring 2 char*
 */
char* Jstring2CStr(JNIEnv* env, jstring jstr) {
	char* rtn = NULL;
	jclass clsstring = (*env)->FindClass(env, "java/lang/String");
	jstring strencode = (*env)->NewStringUTF(env, "utf-8");
	jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes",
			"(Ljava/lang/String;)[B");
	jbyteArray barr = (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid,
			strencode);
	jsize alen = (*env)->GetArrayLength(env, barr);
	jbyte* ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
	if (alen > 0) {
		rtn = (char*) malloc(alen + 1);
		memcpy(rtn, ba, alen);
		rtn[alen] = 0;
	}
	(*env)->ReleaseByteArrayElements(env, barr, ba, 0);
	return rtn;
}

JNIEXPORT jstring JNICALL Java_com_example_webdemo_util_H5Decode_decode(
		JNIEnv* env, jobject obj, jstring in, jstring account, jstring device) {
	char* cin = Jstring2CStr(env, in);
	char* cAccount = Jstring2CStr(env, account);
	char* cDeviceId = Jstring2CStr(env, device);
	//输入/////////////////
	LOGI("输入内容=%s", cin);
	LOGI("输入帐号=%s", cAccount);
	LOGI("输入deviceid=%s", cDeviceId);
	char hello[7] = { ',', 'h', 'e', 'l', 'l', 'o', '\0' };
	strcat(cin, hello);
	LOGI("处理后内容=%s", cin);

	MD5_CTX context = { 0 };
	MD5Init(&context);
	MD5Update(&context, "123456", 6);
	unsigned char d[16] = { 0 };
	MD5Final(d, &context);
	int i;
	char buffer[80];
	char destination[25];
	for(i=0;i<16;i++)
	{
		sprintf(buffer, "%s%02x", buffer, d[i]);
		LOGI("%02x",d[i]);
	}
	//输出/////////////////
	return (*env)->NewStringUTF(env, buffer);
}

