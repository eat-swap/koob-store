package org.eatswap.koobstore.modules.home.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.FragmentHomeBinding
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.home.BookAdapter
import org.eatswap.koobstore.utils.ui.GridSpacingItemDecoration
import kotlin.random.Random

class HomeFragment : Fragment() {

	private var bookList: MutableList<Book>? = null

	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	@SuppressLint("RestrictedApi")
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val homeViewModel =
			ViewModelProvider(this).get(HomeViewModel::class.java)

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val v: View = binding.root

		bookList = mutableListOf()

		for (i in 0..10) {
			bookList?.add(Book(i, "Title $i", "Author $i", "ISBN $i", "Description $i", "https://img.bytepic.cn/blindbox1.jpg", Random.nextDouble() * 100))
		}

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