package hu.ait.bookrecorder3.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookList(
    @PrimaryKey(autoGenerate = true) var bookID: Long?,
    @ColumnInfo(name = "title") var Title: String,
    @ColumnInfo(name = "author")var Author: String)