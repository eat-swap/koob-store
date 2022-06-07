package org.eatswap.koobstore.modules.home.ui.notifications

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.internal.ViewUtils
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.databinding.FragmentNotificationsBinding
import org.eatswap.koobstore.modules.book.services.BookService
import org.eatswap.koobstore.modules.orders.data.Order
import org.eatswap.koobstore.modules.orders.data.OrderService
import org.eatswap.koobstore.modules.user.services.LoginService
import org.eatswap.koobstore.utils.ui.GridSpacingItemDecoration

class NotificationsFragment : Fragment() {

	private var _binding: FragmentNotificationsBinding? = null
	private val binding get() = _binding!!

	private lateinit var orderList: MutableList<Order>

	private lateinit var bookService: BookService

	private lateinit var orderService: OrderService

	@SuppressLint("RestrictedApi")
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentNotificationsBinding.inflate(inflater, container, false)
		orderService = OrderService(requireActivity().application as KoobApplication)
		bookService = BookService(requireActivity().application as KoobApplication)

		orderList = mutableListOf()
		val orderEntities = orderService.findByUserId(LoginService.loggedInUserId!!)
		for (orderEntity in orderEntities) {
			orderList.add(Order.of(orderEntity, (requireActivity().application as KoobApplication).database.bookDao()))
		}

		val root: View = binding.root

		val rv = binding.recyclerViewOrder
		rv.layoutManager = GridLayoutManager(context, 1)
		rv.addItemDecoration(
			GridSpacingItemDecoration(1, ViewUtils.dpToPx(requireContext(), 16).toInt(), true)
		)
		rv.adapter = OrderAdapter(
			orderList,
			bookService,
		)

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}