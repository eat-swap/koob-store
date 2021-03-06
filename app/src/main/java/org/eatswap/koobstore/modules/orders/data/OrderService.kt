package org.eatswap.koobstore.modules.orders.data

import org.eatswap.koobstore.KoobApplication

class OrderService(private val context: KoobApplication) {

	private val orderViewModel: OrderViewModel = OrderViewModelFactory(
		context.database.orderEntityDao()
	).create(OrderViewModel::class.java)

	fun findByUserId(userId: Int): List<OrderEntity> {
		return orderViewModel.findByUserId(userId)
	}

	fun findById(id: Int): OrderEntity? {
		return orderViewModel.findById(id)
	}

	fun insert(order: Order) {
		orderViewModel.insert(order.toEntity())
	}

}