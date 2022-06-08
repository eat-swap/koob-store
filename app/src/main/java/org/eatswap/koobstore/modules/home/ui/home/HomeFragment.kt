package org.eatswap.koobstore.modules.home.ui.home

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.FragmentHomeBinding
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.services.BookService
import org.eatswap.koobstore.utils.ui.GridSpacingItemDecoration

class HomeFragment : Fragment() {

	private var bookList: List<Book>? = null

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	private var _bookService: BookService? = null
	private val bookService get() = _bookService!!

	private lateinit var categoryArray: Array<String>

	private lateinit var allBooks: List<Book>

	@SuppressLint("RestrictedApi")
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_bookService = BookService(requireActivity().application as KoobApplication)

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val v: View = binding.root

		bookList = bookService.findAll()
		allBooks = bookService.findAll()

		val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_view_browse)!!
		recyclerView.layoutManager = GridLayoutManager(context, 2)
		recyclerView.addItemDecoration(
			GridSpacingItemDecoration(2, ViewUtils.dpToPx(requireContext(), 10).toInt(), true)
		)
		recyclerView.adapter =
			BookAdapter(bookList!!)

		val categories = mutableListOf("Clear Selections")
		bookService.findAll().forEach {
			if (it.category !in categories) {
				categories.add(it.category)
			}
		}
		categoryArray = categories.toTypedArray()


		(binding.categoryMenu.editText as? MaterialAutoCompleteTextView)
			?.setSimpleItems(categoryArray)
		(binding.categoryMenu.editText as? MaterialAutoCompleteTextView)
			?.addTextChangedListener {
				val str = it.toString()
				if (str == "Clear Selections" || str == "") {
					bookList = bookService.findAll()
					recyclerView.adapter = BookAdapter(bookList!!)
				} else {
					bookList = bookService.findByCategory(str)
					recyclerView.adapter = BookAdapter(bookList!!)
				}
				Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
			}

		binding.textInputLayoutSearch.editText?.setOnKeyListener { _, keyCode, event ->
			if (event.action != KeyEvent.ACTION_DOWN || keyCode != KeyEvent.KEYCODE_ENTER) {
				return@setOnKeyListener false
			}
			val str = binding.textInputLayoutSearch.editText?.text.toString()
			bookList = allBooks.fold(mutableListOf<Book>()) { acc, book ->
				if (book.title.contains(str, true)) {
					acc.add(book)
				}
				acc
			}
			recyclerView.adapter =
				BookAdapter(bookList!!)

			true
		}

		return v
	}

	override fun onResume() {
		super.onResume()
		// Log.d(TAG, "onResume: !!!!!!!!!")
		(binding.categoryMenu.editText as? MaterialAutoCompleteTextView)
			?.setSimpleItems(categoryArray)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}