package uz.triples.aapp

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log


class NLService : NotificationListenerService() {
    private val TAG = "NLService"

    override fun onCreate() {
        super.onCreate()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {

        val pack = sbn.packageName
        val extras = sbn.notification.extras
        val title = extras.getString("android.title").toString()
        val text = extras.getCharSequence("android.text").toString()

        Log.i("Package", pack)
        Log.i("Title", title)
        Log.i("Text", text)

        val msgrcv = Intent("Msg")
        msgrcv.putExtra("package", pack)
        msgrcv.putExtra("title", title)
        msgrcv.putExtra("text", text)

        sendBroadcast(msgrcv)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {

    }

    
}