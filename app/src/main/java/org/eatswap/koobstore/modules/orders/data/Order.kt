package org.eatswap.koobstore.modules.orders.data

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.runBlocking
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.data.BookDao

data class Order(
	val id: Int,
	val userId: Int,
	val items: MutableList<Pair<Book, Int>>,
	val createdAt: Long, // Timestamp
	val totalAmount: Double,
) {
	companion object {
		fun of(orderEntity: OrderEntity, bookDao: BookDao) : Order {
			val intArr = orderEntity.itemIdCount.split(',')

			val items = mutableListOf<Pair<Book, Int>>()

			runBlocking {
				for (i in intArr.indices step 2) {
					bookDao.findById(intArr[i])?.let {
						items.add(Pair(it, intArr[i + 1].toInt()))
					}
				}
			}

			return Order(
				orderEntity.id,
				orderEntity.userId,
				items,
				orderEntity.createdAt,
				orderEntity.totalAmount,
			)
		}
	}

	fun toEntity() : OrderEntity {
		val itemIdCount = StringBuilder()
		for (item in items) {
			itemIdCount.append(item.first.id)
			itemIdCount.append(',')
			itemIdCount.append(item.second)
			itemIdCount.append(',')
		}
		Log.d(TAG, "toEntity: itemIdCount.toString() = [$itemIdCount]")

		return OrderEntity(
			id,
			userId,
			itemIdCount.toString().substring(0 .. itemIdCount.length - 2),
			createdAt,
			totalAmount,
		)
	}

}