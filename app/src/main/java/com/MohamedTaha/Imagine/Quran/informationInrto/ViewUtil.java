package com.MohamedTaha.Imagine.Quran.informationInrto;

import android.os.Build;
import android.view.View;
import android.view.ViewManager;
import android.view.ViewTreeObserver;

import androidx.core.view.ViewCompat;

/**
 * Created by ManasatPC on 05/01/18.
 */

public class ViewUtil
{
    ViewUtil() {
    }

    /** Returns whether or not the view has been laid out **/
    private static boolean isLaidOut(View view) {
        return ViewCompat.isLaidOut(view) && view.getWidth() > 0 && view.getHeight() > 0;
    }

    /** Executes the given {@link Runnable} when the view is laid out **/
    static void onLaidOut(final View view, final Runnable runnable) {
        if (isLaidOut(view)) {
            runnable.run();
            return;
        }

        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final ViewTreeObserver trueObserver;

                if (observer.isAlive()) {
                    trueObserver = observer;
                } else {
                    trueObserver = view.getViewTreeObserver();
                }

                removeOnGlobalLayoutListener(trueObserver, this);

                runnable.run();
            }
        });
    }

    @SuppressWarnings("deprecation")
    static void removeOnGlobalLayoutListener(ViewTreeObserver observer,
                                             ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT >= 16) {
            observer.removeOnGlobalLayoutListener(listener);
        } else {
            observer.removeGlobalOnLayoutListener(listener);
        }
    }

    static void removeView(ViewManager parent, View child) {
        if (parent == null || child == null) {
            return;
        }

        try {
            parent.removeView(child);
        } catch (Exception ignored) {
            // This catch exists for modified versions of Android that have a buggy ViewGroup
            // implementation. See b.android.com/77639, #121 and #49
        }
    }
}
