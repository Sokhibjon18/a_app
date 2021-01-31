package uz.triples.aapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notification::class],version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun notificationDao(): NotificationsDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
            if (instance == null){
                instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, "Notifications.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }

    }

}