package com.jun.common.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityLifecycleTracker {
    private static AtomicBoolean tracking = new AtomicBoolean(false);
    private static WeakReference<Activity> currActivity;
    private static int activityReferences = 0;
    private static AtomicInteger foregroundActivityCount = new AtomicInteger(0);

    public static void startTracking(Application application) {
        if (!tracking.compareAndSet(false, true)) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                ActivityLifecycleTracker.onActivityCreated(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                ActivityLifecycleTracker.activityReferences++;
                ActivityLifecycleTracker.onActivityStarted(activity);
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                ActivityLifecycleTracker.onActivityResumed(activity);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                ActivityLifecycleTracker.onActivityPaused(activity);
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                ActivityLifecycleTracker.onActivityStopped(activity);
                ActivityLifecycleTracker.activityReferences--;
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                ActivityLifecycleTracker.onActivitySaveInstanceState(activity);
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                ActivityLifecycleTracker.onActivityDestroyed(activity);
            }
        });
    }

    public static boolean isInBackground() {
        return 0 == activityReferences;
    }

    public static Activity getCurrentActivity() {
        return currActivity != null ? currActivity.get() : null;
    }

    private static void onActivityCreated(Activity activity) {
    }

    private static void onActivityStarted(Activity activity) {
    }

    private static void onActivityResumed(Activity activity) {
        currActivity = new WeakReference<>(activity);
        foregroundActivityCount.incrementAndGet();
    }

    private static void onActivityPaused(Activity activity) {
        int count = foregroundActivityCount.decrementAndGet();
        if (count < 0) {
            foregroundActivityCount.set(0);
        }
    }

    private static void onActivityStopped(Activity activity) {
    }

    private static void onActivitySaveInstanceState(Activity activity) {
    }

    private static void onActivityDestroyed(Activity activity) {
    }


}
