package com.mvvm.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.mvvm.R
import com.mvvm.ui.book.details.BookDetailsFragment
import com.mvvm.ui.common.widget.BookSearchResultsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookSearchFragment : Fragment() {
    private var viewModel: BookSearchViewModel? = null
    private var adapter: BookSearchResultsAdapter? = null

    private var keywordEditText: TextInputEditText? = null
    private  var authorEditText:TextInputEditText? = null
    private var searchButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BookSearchResultsAdapter()
        viewModel = ViewModelProvider(this).get(BookSearchViewModel::class.java)
        viewModel!!.init()
        viewModel!!.getVolumesResponseLiveData()!!.observe(this
        ) { volumesResponse ->
            if (volumesResponse != null) {
                volumesResponse.items?.let {
                    adapter!!.setResults(it)

                }
            }
        }
        adapter!!.setItemClickListener {
            val viewHolder = it!!.tag as RecyclerView.ViewHolder
            Toast.makeText(context, ""+viewHolder.adapterPosition, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_bookSearchFragment_to_bookDetailsFragment2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_book_search, container, false)
        val recyclerView: RecyclerView =
            view.findViewById(R.id.fragment_booksearch_searchResultsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
//        recyclerView.onIte
        keywordEditText = view.findViewById(R.id.fragment_booksearch_keyword)
        authorEditText = view.findViewById(R.id.fragment_booksearch_author)
        searchButton = view.findViewById(R.id.fragment_booksearch_search) as Button
        searchButton!!.setOnClickListener(View.OnClickListener { performSearch() })
        return view
    }

    fun performSearch() {
        val keyword = keywordEditText!!.editableText.toString()
        val author: String = authorEditText?.getEditableText().toString()
        viewModel!!.searchVolumes(keyword, author)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}