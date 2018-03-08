package com.sf0404.customwindowoverlay;

import android.view.View;
import android.widget.TextView;

import com.cbsa.ui.widget.notification.OverlayWindowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

class NotificationViewHolder implements OverlayWindowView.OverlayViewHolder<NotificationManager.NotificationData> {

    private final ToastType type;

    @BindView(R.id.tvMessage)
    TextView tvMessage;

    private Unbinder unBinder;

    View rootView;

    public NotificationViewHolder(ToastType type) {
        this.type = type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_notify_holder_info;
    }

    @Override
    public void initView(View view) {
        rootView = view;
        unBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void updateData(NotificationManager.NotificationData o) {
        tvMessage.setText(o.msg);
    }

    @Override
    public void setCallback(OverlayWindowView.NotificationCallback notificationCallback) {
        // Stub method
    }

    @Override
    public void setNotificationView(OverlayWindowView overlayWindowView) {
        // Stub method
    }

    // TODO add callback when onDismiss occur to clean data - for ex:unBinder
}
