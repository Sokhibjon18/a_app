package uz.triples.aapp

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import uz.triples.aapp.database.AppDatabase
import uz.triples.aapp.database.NotificationsDao

class NotificationContentProvider : ContentProvider() {

    lateinit var notificationsDao: NotificationsDao

    private val CONTENT_URI = "content://uz.triples.aapp/notifications"

    companion object{
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)
        init {
            MATCHER.addURI("uz.triples.aapp", "notifications", 1)
        }
    }


    override fun onCreate(): Boolean {
        notificationsDao = AppDatabase.getInstance(context!!)!!.notificationDao()
        return false
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        when(MATCHER.match(p0)){
            1 -> {
                if (context != null)
                    return notificationsDao.getAllNotification()
            }
            else -> throw IllegalArgumentException("Unknown URI: $p0");
        }
        return null
    }

    override fun getType(p0: Uri): String? {
        return null
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }
}