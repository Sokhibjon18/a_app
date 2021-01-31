package uz.triples.aapp.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationsDao {
    @Insert
    fun insertNotification(notification: Notification)

    @Query("SELECT * FROM notifications")
    fun getAllNotification(): Cursor
}