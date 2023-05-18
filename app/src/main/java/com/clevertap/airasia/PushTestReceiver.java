package com.clevertap.airasia;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.clevertap.android.sdk.CleverTapAPI;

import org.json.JSONException;

import java.util.List;

public class PushTestReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid,
                       String userId, String channelId, String requestId) {
        CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(context);
        if (cleverTapAPI != null) {
            cleverTapAPI.pushBaiduRegistrationId(channelId,true);
        }
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String message,
                          String customContentString, int i, int i1) {

        try {
            Bundle extras = com.clevertap.android.sdk.Utils.stringToBundle(message);
            CleverTapAPI.createNotification(context,extras);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

        Log.d("onNotificationArrived -- : ",s+"  "+s1+"   "+s2);
    }
}
