package hu.ait.bookrecorder3

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import hu.ait.bookrecorder3.data.AppDatabase
import hu.ait.bookrecorder3.data.BookList
import hu.ait.bookrecorder3.databinding.ActivityBooklistBinding
import hu.ait.bookrecorder3.databinding.SavedlistRowBinding


class BookListActivity: AppCompatActivity() {
    lateinit var binding: ActivityBooklistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooklistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllBooks()

        binding.btnBack.setOnClickListener {
            val intentResult = Intent()
            setResult(RESULT_CANCELED, intentResult)
            finish()
        }
    }


    fun getAllBooks() {
        Thread {
            val books = AppDatabase.getInstance(this).bookListDAO().getAllBooks()
            runOnUiThread{
                books.forEach {
                    val summaryBinding = SavedlistRowBinding.inflate(
                        layoutInflater
                    )
                    var bookl = BookList(it.bookID, it.Title, it.Author)
                    summaryBinding.tvTitle.text = it.Title
                    summaryBinding.tvAuthor.text = it.Author
                    summaryBinding.ivItemLogo.setImageResource(
                        R.drawable.books)
                    summaryBinding.btnDelete.setOnClickListener {
                        deleteBooks(bookl)
                        binding.layoutContent.removeView(summaryBinding.root)

                    }
                        binding.layoutContent.addView(summaryBinding.root)
                }
            }
        }.start()
    }

    private fun deleteBooks(bookList: BookList) {
        Log.i("QQQQQ", "deleteBooks: ${bookList.bookID}$, ${bookList.Title}$")
        Thread {
            AppDatabase.getInstance(this).bookListDAO().deleteBook(bookList)
        }.start()
    }
}
