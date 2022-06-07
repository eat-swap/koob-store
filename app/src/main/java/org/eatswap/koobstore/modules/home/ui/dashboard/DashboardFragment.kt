package org.eatswap.koobstore.modules.home.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.databinding.FragmentDashboardBinding
import org.eatswap.koobstore.modules.book.services.BookService
import org.eatswap.koobstore.modules.cart.Cart
import org.eatswap.koobstore.modules.cart.CartService
import org.eatswap.koobstore.modules.user.services.LoginService

class DashboardFragment : Fragment() {

	private var _cartList: List<Cart>? = null

	private var _binding: FragmentDashboardBinding? = null
	private val binding get() = _binding!!

	private var _bookService: BookService? = null
	private val bookService get() = _bookService!!

	private var _cartService: CartService? = null
	private val cartService get() = _cartService!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val dashboardViewModel =
			ViewModelProvider(this).get(DashboardViewModel::class.java)

		_binding = FragmentDashboardBinding.inflate(inflater, container, false)
		_bookService = BookService(requireActivity().application as KoobApplication)
		_cartService = CartService(requireActivity().application as KoobApplication)


		val root: View = binding.root

		_cartList = cartService.findAllByUserId(LoginService.loggedInUserId!!.toString())

		val recyclerView = binding.recyclerViewCart

		recyclerView.layoutManager = LinearLayoutManager(context)

		recyclerView.adapter = CartAdapter(
			_cartList!!,
			bookService,
			cartService
		)

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}