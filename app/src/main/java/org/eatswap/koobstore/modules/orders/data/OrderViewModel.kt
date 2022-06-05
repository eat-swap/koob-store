package org.eatswap.koobstore.modules.orders.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OrderViewModel(private val orderEntityDao: OrderEntityDao) : ViewModel() {

	private fun insert(orderEntity: OrderEntity) {
		viewModelScope.launch {
			orderEntityDao.insert(orderEntity)
		}
	}

	private fun update(orderEntity: OrderEntity) {
		viewModelScope.launch {
			orderEntityDao.update(orderEntity)
		}
	}

	// Delete
	fun delete(orderEntity: OrderEntity) {
		viewModelScope.launch {
			orderEntityDao.delete(orderEntity)
		}
	}

	// findById
	fun findById(id: Long): OrderEntity? {
		return orderEntityDao.findById(id.toString())
	}

	// findByUserId
	fun findByUserId(userId: String): List<OrderEntity> {
		return orderEntityDao.findByUserId(userId)
	}

}