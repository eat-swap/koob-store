package org.eatswap.koobstore.modules.orders.data

import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.data.BookDao

data class Order(
	val id: Int,
	val userId: Int,
	val items: List<Pair<Book, Int>>,
	val createdAt: Long, // Timestamp
) {
	companion object {
		fun of(orderEntity: OrderEntity, bookDao: BookDao) : Order {
			val intArr = orderEntity.itemIdCount.split(',')

			val items = mutableListOf<Pair<Book, Int>>()

			for (i in intArr.indices step 2) {
				bookDao.findById(intArr[i])?.let {
					items.add(Pair(it, intArr[i + 1].toInt()))
				}
			}

			return Order(
				orderEntity.id,
				orderEntity.userId,
				items,
				orderEntity.createdAt
			)
		}
	}
}