package com.marakana.android.yamba;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by DapurMasak07 on 11/11/14.
 */
public class RefreshService extends IntentService {
    static final String TAG = "RefreshService";

    public RefreshService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Log.d(TAG, "onStarted");
    }
//    @Override
//    public IBinder onBind(Intent intent){
//        return null;
//    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreated");
    }
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId){
//        super.onStartCommand(intent, flags, startId);
//        Log.d(TAG, "onStarted");
//        return START_STICKY;
//    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroyed");
    }
}
