package uz.triples.aapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(onNotice, IntentFilter("Msg"));

        btn1.setOnClickListener {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }

        btn.setOnClickListener {
            val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val mBuilder =
                NotificationCompat.Builder(this@MainActivity, default_notification_channel_id)
            mBuilder.setContentTitle("My Notification")
            mBuilder.setContentText("Notification Listener Service Example")
            mBuilder.setTicker("Notification Listener Service Example")
            mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
            mBuilder.setAutoCancel(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationChannel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "NOTIFICATION_CHANNEL_NAME",
                    importance
                )
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
                mNotificationManager.createNotificationChannel(notificationChannel)
            }
            mNotificationManager.notify(System.currentTimeMillis().toInt(), mBuilder.build())

        }
    }

    private val onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val pack = intent.getStringExtra("package")
            val title = intent.getStringExtra("title")
            val text = intent.getStringExtra("text")
            text1.text = text1.text.toString() + Html.fromHtml("<br>$pack<br><b>$title : </b>$text").toString()
        }
    }
}