package org.eatswap.koobstore.modules.home.ui.notifications

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.ActivityOrderDetailsBinding
import org.eatswap.koobstore.modules.book.data.BookDao
import org.eatswap.koobstore.modules.orders.data.Order
import org.eatswap.koobstore.modules.orders.data.OrderService

class OrderDetailsActivity : AppCompatActivity() {

	private lateinit var binding: ActivityOrderDetailsBinding
	private lateinit var orderService: OrderService

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
		orderService = OrderService(this.application as KoobApplication)

		val v = binding.root
		val i = this.intent!!
		val oe = orderService.findById(i.getIntExtra("order_id", 0))!!
		val o = Order.of(oe, (this.application as KoobApplication).database.bookDao())

		val imgUri = Uri.parse(o.items[0].first.imageUrl)!!
		Glide.with(this).load(imgUri).into(binding.orderImageDetails)

		binding.textOrderBasicInfo.text = "Order ID: ${o.id} | Total ${String.format("$%.2f", o.totalAmount)}"

		binding.textOrderDetails.text = StringBuilder().apply {
			o.items.forEach {
				append("${it.first.title} x ${it.second} \n")
			}
		}

		setContentView(v)
	}
}