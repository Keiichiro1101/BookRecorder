package hu.ait.bookrecorder3

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hu.ait.bookrecorder3.data.AppDatabase
import hu.ait.bookrecorder3.data.BookList
import hu.ait.bookrecorder3.databinding.ActivityMainBinding
import hu.ait.bookrecorder3.databinding.BooklistRowBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bookAdapter: BookAdapter

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBookList.setOnClickListener {
            val intentDetails = Intent()
            intentDetails.setClass(
                this, BookListActivity::class.java
            )
             startForResult.launch(intentDetails)
        }

        setupRecyclerView()

        binding.btnSearch.setOnClickListener {
            if(binding.etTitle.text!!.isNotEmpty()){
            lifecycleScope.launchWhenCreated {
                binding.progressBar.isVisible = true
//          need to check that the input is not null
                val response = try{
                    RetroFitInstance.api.getBooks(binding.etTitle.text.toString())
                }catch(e: IOException) {
                    Log.e(TAG, "IOException, you might not have internet connection")
                    binding.progressBar.isVisible = false
                    return@launchWhenCreated
                }catch (e: HttpException) {
                    Log.e(TAG, "HttpException, unexpected response")
                    binding.progressBar.isVisible = false
                    return@launchWhenCreated
                }
                if(response.isSuccessful && response.body() != null) {
                    bookAdapter.books = response.body()!!.items
                } else {
                    Log.e(TAG, "Response not successful")
                }
                binding.progressBar.isVisible = false
                }
            }
            else{
                binding.etTitle.error = "This field cannot be empty"
            }
        }


    }
    private fun setupRecyclerView() = binding.rvBooks.apply {
        bookAdapter = BookAdapter(this@MainActivity)
        adapter = bookAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    fun insertBooks(title:String, author:String) {
        Thread {
            val newBook = BookList(
                null,
                title,
                author
            )
            AppDatabase.getInstance(this).bookListDAO().insertBook(newBook)
        }.start()
    }

}