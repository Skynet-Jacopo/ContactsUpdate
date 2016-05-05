package edu.feicui.contactsupdate.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ☆刘群☆ on 2016/5/4.
 */
public abstract class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";

    /**
     * 用来保存所有存在的Activity
     */
    private static ArrayList<BaseActivity> onlineActivityList = new ArrayList<>();

    /**
     * 依次退出当前存在的所有Activity
     */
    public static void finishAll(){
        Iterator<BaseActivity> iterator=onlineActivityList.iterator();

        while (iterator.hasNext()){
            iterator.next().finish();
        }
    }

    /**
     *在类中创建Activity跳转的StartActivity方法
     * @param targetClass 以下为普通跳转
     */
    protected void startActivity(Class<?> targetClass){
        Intent intent=new Intent(this,targetClass);
        startActivity(intent);
    }

    //传递参数的跳转
    protected void startActivity(Class<?> targetClass,Bundle bundle){
        Intent intent=new Intent(this,targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //带动画的跳转
    protected void startActivity(Class<?> targetClass,int inAnimID,int OutAnimID){
        Intent intent=new Intent(this,targetClass);
        startActivity(intent);
        overridePendingTransition(inAnimID,OutAnimID);
    }

    //带动画的跳转,并传递参数
    protected void  startActivity(Class<?> targetClass,int inAnimID,int outAnimID,Bundle bundle){
        Intent intent=new Intent(this,targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(inAnimID,outAnimID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, getClass().getSimpleName() + "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getClass().getSimpleName() + "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, getClass().getSimpleName() + "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, getClass().getSimpleName() + "onStop()");
    }

    //在onDestroy()方法中清空Activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getClass().getSimpleName() + "onDestroy()");

        if (onlineActivityList.contains(this)){
            onlineActivityList.remove(this);
        }
    }
}
