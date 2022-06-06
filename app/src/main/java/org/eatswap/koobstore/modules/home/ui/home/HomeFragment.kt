package org.eatswap.koobstore.modules.home.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.FragmentHomeBinding
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.services.BookService
import org.eatswap.koobstore.modules.home.BookAdapter
import org.eatswap.koobstore.utils.ui.GridSpacingItemDecoration

class HomeFragment : Fragment() {

	private var bookList: List<Book>? = null

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	private var _bookService: BookService? = null
	private val bookService get() = _bookService!!

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

		val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_view_browse)!!
		recyclerView.layoutManager = GridLayoutManager(context, 2)
		recyclerView.addItemDecoration(
			GridSpacingItemDecoration(2, ViewUtils.dpToPx(requireContext(), 10).toInt(), true)
		)
		recyclerView.adapter = BookAdapter(bookList!!)

		return v
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}