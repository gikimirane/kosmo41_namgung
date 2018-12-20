package com.study.android.callmetellme;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


public class SaveSharedPreference extends AppCompatActivity {
    private static final String TAG = "lecture";
    static final String mystatus = "status";
    static final String myid = "id";
    static final String mypw = "pw";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setMyId(Context ctx, String id){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(myid,id);
        editor.commit();
    }

    public static String getMyId(Context ctx){
        return getSharedPreferences(ctx).getString(myid, "");
    }

    public static void setMypw(Context ctx,String pw){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(mypw,pw);
        editor.commit();
    }

    public static String getMypw(Context ctx){
        return getSharedPreferences(ctx).getString(mypw, "");
    }
    // 상태 저장
    public static void setStatus(Context ctx, boolean sw) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(mystatus, sw);
        editor.commit();
    }
    // 저장된 정보 가져오기
    public static Boolean getStatus(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(mystatus, false);
    }
    // 로그아웃
    public static void clearStatus(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(mystatus);
        editor.commit();
    }
    public static void clearLogin(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(myid);
        editor.remove(mypw);
        editor.commit();
    }
}
