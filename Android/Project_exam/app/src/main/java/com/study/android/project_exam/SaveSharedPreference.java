package com.study.android.project_exam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;



public class SaveSharedPreference extends AppCompatActivity {
    private static final String TAG = "lecture";
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_PW = "userpw";
    static final String PREF_USER_POINT="userpoint";
    static final String PREF_USER_COUNT="usecount";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    // 계정 정보 저장
    public static void setUserName(Context ctx, String userName, String userPw,int userpoint,int usecount) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.putString(PREF_USER_PW,userPw);
        editor.putInt(PREF_USER_POINT,userpoint);
        editor.putInt(PREF_USER_COUNT,usecount);
        editor.commit();
    }
    // 저장된 정보 가져오기
    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserPw(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_PW, "");
    }

    public static int getUserPoint(Context ctx){
        return getSharedPreferences(ctx).getInt(PREF_USER_POINT,0);
    }

    public static int getUseCount(Context ctx){
        return getSharedPreferences(ctx).getInt(PREF_USER_COUNT, 0);
    }

    // 로그아웃
    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
