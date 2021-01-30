package uz.triples.aapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationsDao {
    @Insert
    fun insertNotification(notification: Notification)

    @Query("SELECT * FROM Notification")
    fun getAllNotification(): List<Notification>
}