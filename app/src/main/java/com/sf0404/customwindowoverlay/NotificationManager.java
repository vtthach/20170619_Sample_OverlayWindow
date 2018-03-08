package com.sf0404.customwindowoverlay;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager;

import com.cbsa.ui.widget.notification.OverlayWindowView;


public class NotificationManager {
    private final Context appContext;
    protected OverlayWindowView.Builder<NotificationData> builder;
    protected OverlayWindowView<NotificationData> notificationView;
    protected NotificationData notificationData = new NotificationData();
    Handler handler = new Handler();

    public NotificationManager(Context appContext) {
        this.appContext = appContext.getApplicationContext();
        this.builder = this.getDefaultBuilder(appContext);
    }

    protected OverlayWindowView.Builder<NotificationData> getDefaultBuilder(Context context) {
        return new OverlayWindowView.Builder<NotificationData>(context)
                .withData(this.notificationData)
                .animationStyle(R.style.AppNotificationAnim)
                .withViewHolder(new NotificationViewHolder(ToastType.TYPE_INFO));
    }

    public void showNotifyError(String msg) {
        showNotify(ToastType.TYPE_ERROR, msg);
    }

    public void showNotifyError(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_ERROR, errorMessage);
    }

    public void showNotifyInfo(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_INFO, errorMessage);
    }

    public void showNotify(ToastType type, String msg) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(() -> {
            notificationData.msg = msg;
            if (notificationView != null) {
                notificationView.dismiss();
            }
            notificationView = builder
                    .withData(notificationData)
                    .windowWidth(WindowManager.LayoutParams.MATCH_PARENT)
                    .animationStyle(R.style.AppNotificationAnim)
                    .withMarginTop(200)
                    .windowType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG)
                    .withViewHolder(new NotificationViewHolder(type))
                    .show();
        }, 200);
    }

    public void hideNotify() {
        if (notificationView != null) {
            notificationView.dismiss();
        }
    }

    class NotificationData {
        String msg;
    }
}
