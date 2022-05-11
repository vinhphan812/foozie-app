package vn.edu.huflit.foozie_app.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import vn.edu.huflit.foozie_app.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MESSAGE_SERVICE";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        NotificationChannel channel = new NotificationChannel(String.valueOf(R.string.default_notification_channel_id), "abc", NotificationManager.IMPORTANCE_HIGH);

        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        RemoteMessage.Notification notify = message.getNotification();

        Log.d("RECEIVED_MESSAGE", message.getMessageId());
        NotificationManagerCompat notifyManager = NotificationManagerCompat.from(this);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, String.valueOf(R.string.default_notification_channel_id))
                .setContentTitle(notify.getTitle())
                .setContentText(notify.getBody())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notify.getBody()))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setFullScreenIntent(null, true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notifyManager.notify(1, builder.build());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}
