package com.reactlibrary;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;

import com.facebook.react.BuildConfig;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class SetAppIconModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private String []iconColour = {"RED", "GREEN",  "BLUE"};

    public SetAppIconModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "SetAppIcon";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void changeIcon(String iconName, Callback callback) {
        int action;
        boolean status = false;
        final Activity activity = getCurrentActivity();
        PackageManager packageManager = activity.getApplicationContext().getPackageManager();
        for (String value : iconColour) {
            if (value.equals(iconName)) {
                action = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
                status = true;
            } else
                action = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;

            packageManager.setComponentEnabledSetting(
                    new ComponentName(BuildConfig.APPLICATION_ID, value),
                    action, PackageManager.DONT_KILL_APP);
            callback.invoke(null, status);
        }
    }
}
