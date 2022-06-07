package org.eatswap.koobstore.modules.home.ui.dashboard

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.internal.ViewUtils
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.FragmentDashboardBinding
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.services.BookService
import org.eatswap.koobstore.modules.cart.Cart
import org.eatswap.koobstore.modules.cart.CartService
import org.eatswap.koobstore.modules.orders.data.Order
import org.eatswap.koobstore.modules.orders.data.OrderService
import org.eatswap.koobstore.modules.user.services.LoginService
import org.eatswap.koobstore.utils.ui.GridSpacingItemDecoration
import java.time.LocalDateTime
import java.time.ZoneOffset

class DashboardFragment : Fragment() {

	private var _cartList: List<Cart>? = null

	private var _binding: FragmentDashboardBinding? = null
	private val binding get() = _binding!!

	private var _bookService: BookService? = null
	private val bookService get() = _bookService!!

	private var _cartService: CartService? = null
	private val cartService get() = _cartService!!

	private lateinit var orderService: OrderService

	@SuppressLint("RestrictedApi")
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
		orderService = OrderService(requireActivity().application as KoobApplication)

		val root: View = binding.root

		_cartList = cartService.findAllByUserId(LoginService.loggedInUserId!!.toString())
		var total: Double = 0.0
		for (cart in _cartList!!) {
			val book = bookService.findById(cart.bookId)!!
			total += book.price * cart.quantity
		}
		binding.cartTotalPrice.text = String.format("$%.2f", total)


		val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_cart)

		recyclerView.layoutManager = GridLayoutManager(context, 1)

		recyclerView.addItemDecoration(
			GridSpacingItemDecoration(1, ViewUtils.dpToPx(requireContext(), 10).toInt(), true)
		)

		Log.d(TAG, "onCreateView: " + _cartList?.size)

		recyclerView.adapter = CartAdapter(
			_cartList!!,
			bookService,
			cartService,
			binding.cartTotalPrice,
		)

		binding.buttonCheckout.setOnClickListener {
			MaterialAlertDialogBuilder(requireContext())
				.setTitle("Checkout")
				.setMessage("Are you sure you want to checkout? Total price: ${binding.cartTotalPrice.text}")
				.setPositiveButton("OK") { dialog, _ ->
					val list = mutableListOf<Pair<Book, Int>>()
					var totalAmount: Double = 0.0
					for (cart in _cartList!!) {
						val book = bookService.findById(cart.bookId)!!
						list.add(Pair(book, cart.quantity))
						totalAmount += book.price * cart.quantity
					}

					val order = Order(0, LoginService.loggedInUserId!!, mutableListOf(), LocalDateTime.now().toEpochSecond(
						ZoneOffset.UTC), totalAmount)
					orderService.insert(order)

					cartService.removeAllByUserId(LoginService.loggedInUserId!!.toString())

					dialog.dismiss()
				}
				.setNegativeButton("Cancel") { dialog, _ ->
					dialog.dismiss()
				}

		}

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}