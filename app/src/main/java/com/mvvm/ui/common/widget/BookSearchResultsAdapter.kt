package com.mvvm.ui.common.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.R
import com.mvvm.data.model.api1.Volume

class BookSearchResultsAdapter:
    RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder>() {

    private var results: List<Volume> = ArrayList<Volume>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchResultHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BookSearchResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookSearchResultHolder, position: Int) {
        val volume: Volume = results[position]
        holder.titleTextView.text = volume.volumeInfo?.title
        holder.publishedDateTextView.text = volume.volumeInfo?.publishedDate
        if (volume.volumeInfo?.imageLinks != null) {
            val imageUrl: String? = volume.volumeInfo!!.imageLinks?.smallThumbnail
                ?.replace("http://", "https://")
            Glide.with(holder.itemView)
                .load(imageUrl)
                .into(holder.smallThumbnailImageView)
        }
        if (volume.volumeInfo?.authors != null) {
////            val u = Util()
////            val authors: String = u.StringJoin(volume.getVolumeInfo().getAuthors(), ", ")
//            holder.authorsTextView.text = authors
        }
    }

    private var onItemClickListener: View.OnClickListener? = null

    fun setItemClickListener(clickListener: View.OnClickListener?) {
        onItemClickListener = clickListener
    }


    override fun getItemCount(): Int {
        return results.size
    }

    fun setResults(results: List<Volume>) {
        this.results = results
        notifyDataSetChanged()
    }

    inner class BookSearchResultHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView
        val authorsTextView: TextView
        val publishedDateTextView: TextView
        val smallThumbnailImageView: ImageView

        init {
            titleTextView = itemView.findViewById(R.id.book_item_title)
            authorsTextView = itemView.findViewById(R.id.book_item_authors)
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate)
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail)
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener)
        }
    }
}