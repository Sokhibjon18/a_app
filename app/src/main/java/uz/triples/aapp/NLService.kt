package uz.triples.aapp

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import java.util.*


class NLService : NotificationListenerService() {
    private val TAG = "NLService"

    override fun onCreate() {
        super.onCreate()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {

        val pack = gettingAppName(sbn.packageName)
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

    private fun gettingAppName(pack: String): String{
        var packCopy = pack
        while (packCopy.indexOf(".") >= 0){
            packCopy = packCopy.substring(packCopy.indexOf(".")+1)
        }
        packCopy = packCopy.substring(0, 1).toUpperCase(Locale.ROOT) + packCopy.substring(1);
        return packCopy
    }
}