package uz.triples.aapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val program: String,
    val title: String,
    val message: String,
    val iconDirection: String,
    val s: String
)