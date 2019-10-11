package com.MohamedTaha.Imagine.Quran.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPerefrenceHelper {
    private static final String SHARED_PREFRENCES_NAME = "save_path_images";
    private static final String SHARED_PREFRENCES_NAME_FOR_SERVICE_BOUND = "save_service_bound";
    private static final String SHARED_PREFRENCES_NAME_FOR_FirstTime = "save_first_time";

    private static SharedPreferences getSharedPrefrences(Context context){
        return context.getSharedPreferences(SHARED_PREFRENCES_NAME,Context.MODE_PRIVATE);
    }
    private static SharedPreferences getSharedPrefrencesForServiceBound(Context context){
        return context.getSharedPreferences(SHARED_PREFRENCES_NAME_FOR_SERVICE_BOUND,Context.MODE_PRIVATE);
    }
    private static SharedPreferences getSharedPrefrencesForFirstTime(Context context){
        return context.getSharedPreferences(SHARED_PREFRENCES_NAME_FOR_FirstTime,Context.MODE_PRIVATE);
    }
    public static void putInt(Context context,String key, int value){
        getSharedPrefrences(context).edit().putInt(key,value).apply();
    }
    public static void putBoolean(Context context,String key, boolean value){
        getSharedPrefrences(context).edit().putBoolean(key,value).apply();
    }
    public static int getInt(Context context , String key, int defaultValue){
         return getSharedPrefrences(context).getInt(key,defaultValue);
    }
    public static boolean getBoolean(Context context,String key, boolean defaultValue){
       return getSharedPrefrences(context).getBoolean(key,defaultValue);
    }
    public static void removeData(Context context){
        getSharedPrefrences(context).edit().clear().commit();
    }
    public static void putFirstTime(Context context,String key, boolean value){
        getSharedPrefrencesForFirstTime(context).edit().putBoolean(key,value).apply();
    }
    public static boolean getFirstTime(Context context , String key, boolean defaultValue){
        return getSharedPrefrencesForFirstTime(context).getBoolean(key,defaultValue);
    }

    public static void putServiceBound(Context context,String key, boolean value){
        getSharedPrefrencesForServiceBound(context).edit().putBoolean(key,value).apply();
    }
    public static boolean getServiceBound(Context context , String key, boolean defaultValue){
        return getSharedPrefrencesForServiceBound(context).getBoolean(key,defaultValue);
    }
}
