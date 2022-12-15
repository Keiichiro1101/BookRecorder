package hu.ait.bookrecorder3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookListDAO {
    @Query("SELECT * FROM books")
    fun getAllBooks() : List<BookList>

    @Insert
    fun insertBook(book:BookList)

    @Delete
    fun deleteBook(book: BookList)
}