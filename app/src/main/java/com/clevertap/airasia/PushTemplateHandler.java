package com.clevertap.airasia;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.interfaces.NotificationHandler;

@SuppressWarnings({"unused"})
public class PushTemplateHandler extends android.app.Application {

    private CleverTapAPI cleverTapDefaultInstance;
    @Override
    public void onCreate() {
        setCTInstance();
        CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());
        ActivityLifecycleCallback.register(this);
        super.onCreate();
    }
    public CleverTapAPI getCTInstance() {
        return cleverTapDefaultInstance;
    }

    public void setCTInstance() {
        this.cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(this);
    }
}
