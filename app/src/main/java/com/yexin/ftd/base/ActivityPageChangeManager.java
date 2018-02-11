package com.yexin.ftd.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Stack;

/**
 * Created by yexin on 2018/2/7.
 */

public class ActivityPageChangeManager<T> {
    private  Stack<T> activityStack;


    private static class ActivityPageChangeManagerInstance{
        private static ActivityPageChangeManager<Activity> instance= new ActivityPageChangeManager<Activity>();
    }

    public static ActivityPageChangeManager getInstance(){
        return ActivityPageChangeManagerInstance.instance;
    }

    private ActivityPageChangeManager(){

    }
    /**
     * add Activity to Stack
     */
    public void addActivity(T activity) {
        if (activityStack == null) {
            activityStack = new Stack<T>();
        }
        activityStack.add(activity);
    }

    /**
     *  remove Activity from Stack
     */
    public void removeActivity(T activity) {
        if (activityStack == null) {
            activityStack = new Stack<T>();
        }
        activityStack.remove(activity);
    }

    /**
     * get current activity from Stack
     */
    public T currentActivity() {
        T activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        T activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(T activity) {
        if (activity != null  && activity instanceof Activity) {
            activityStack.remove(activity);
            ((Activity)activity).finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls) {
        for (T activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity() {
        for (T activity : activityStack) {
            if (null != activity && activity instanceof Activity) {
                ((Activity)activity).finish();
            }
        }
        activityStack.clear();
        //杀死该应用进程
//		android.os.Process.killProcess(android.os.Process.myPid());

    }

    public static void unbindReferences(View view) {
        try {
            if (view != null) {
                view.destroyDrawingCache();
                unbindViewReferences(view);
                if (view instanceof ViewGroup){
                    unbindViewGroupReferences((ViewGroup) view);
                }
            }
        } catch (Throwable e) {

        }
    }

    private static void unbindViewGroupReferences(ViewGroup viewGroup) {
        int nrOfChildren = viewGroup.getChildCount();
        for (int i = 0; i < nrOfChildren; i++) {
            View view = viewGroup.getChildAt(i);
            unbindViewReferences(view);
            if (view instanceof ViewGroup)
                unbindViewGroupReferences((ViewGroup) view);
        }
        try {
            viewGroup.removeAllViews();
        } catch (Throwable mayHappen) {
            // AdapterViews, ListViews and potentially other ViewGroups don't
            // support the removeAllViews operation
        }
    }

    @SuppressWarnings("deprecation")
    private static void unbindViewReferences(View view) {
        // set all listeners to null (not every view and not every API level
        // supports the methods)
        try {
            view.setOnClickListener(null);
            view.setOnCreateContextMenuListener(null);
            view.setOnFocusChangeListener(null);
            view.setOnKeyListener(null);
            view.setOnLongClickListener(null);
            view.setOnClickListener(null);
        } catch (Throwable mayHappen) {
            //todo
        }

        // set background to null
        Drawable d = view.getBackground();
        if (d != null){
            d.setCallback(null);
        }

        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            d = imageView.getDrawable();
            if (d != null){
                d.setCallback(null);
            }
            imageView.setImageDrawable(null);
            imageView.setBackgroundDrawable(null);
        }

        // destroy WebView
        if (view instanceof WebView) {
            WebView webview = (WebView) view;
            webview.stopLoading();
            webview.clearFormData();
            webview.clearDisappearingChildren();
            webview.setWebChromeClient(null);
            webview.setWebViewClient(null);
            webview.destroyDrawingCache();
            webview.destroy();
            webview = null;
        }

        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            try {
                listView.removeAllViewsInLayout();
            } catch (Throwable mayHappen) {
            }
            ((ListView) view).destroyDrawingCache();
        }
    }

    /**
     * exit System
     * @param context
     */
    public void exit(Context context) {
        exit(context, true);
    }

    /**
     * exit System
     * @param context
     * @param isClearCache
     */
    @SuppressWarnings("deprecation")
    public void exit(Context context, boolean isClearCache) {
        try {
            finishAllActivity();
			/*if(context != null){
				ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
				activityMgr.restartPackage(context.getPackageName());
			}*/
			/*if(isClearCache){
				LruCacheManager.getInstance().evictAll();
				CacheManager.clearAll();
			}*/
//			System.exit(0);
//			android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


