package hu.ait.bookrecorder3

import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import hu.ait.bookrecorder3.databinding.BooklistRowBinding
import hu.ait.bookrecorder3.googlebookapi.Item

class BookAdapter(private val context: Context):RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: BooklistRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var books: List<Item>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(BooklistRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.binding.apply {
            val book = books[position]
            tvTitle.text = book.volumeInfo.title
            tvAuthor.text = book.volumeInfo.authors[0]
            ivItemLogo.setImageResource(
                R.drawable.books
            )
            btnSave.setOnClickListener {
                (context as MainActivity).insertBooks(tvTitle.text.toString(), tvAuthor.text.toString())
            }
        }
    }
}