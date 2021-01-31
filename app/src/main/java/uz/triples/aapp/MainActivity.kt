package uz.triples.aapp

import android.content.*
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.triples.aapp.database.AppDatabase
import uz.triples.aapp.database.Notification
import uz.triples.aapp.database.NotificationsDao
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"
    private val insertionDao: NotificationsDao by lazy {
        AppDatabase.getInstance(this)!!.notificationDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(onNotice, IntentFilter("Msg"));

        btn.setOnClickListener {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }
    }

    private fun notificationListenerEnabled(): Boolean {
        val cn = ComponentName(this, NLService::class.java)
        val flat: String = Settings.Secure.getString(
            this.contentResolver,
            "enabled_notification_listeners"
        )
        return flat.contains(cn.flattenToString())
    }

    override fun onResume() {
        if (notificationListenerEnabled())
            btn.text = "Disable permission"
        else
            btn.text = "Get permission"
        super.onResume()
    }

    private val onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val pack = intent.getStringExtra("package")!!
            val title = intent.getStringExtra("title")!!
            val text = intent.getStringExtra("text")!!
            val s = SimpleDateFormat("hh:mm:ss, MMMM dd").format(Date().time)

            CoroutineScope(Dispatchers.IO).launch {
                insertionDao.insertNotification(
                    Notification(0, pack, title, text, s)                                      //
                )
            }
        }
    }
}