package org.eatswap.koobstore.modules.orders.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OrderViewModel(private val orderEntityDao: OrderEntityDao) : ViewModel() {

	fun insert(orderEntity: OrderEntity) {
		viewModelScope.launch {
			orderEntityDao.insert(orderEntity)
		}
	}

	fun update(orderEntity: OrderEntity) {
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

/**
 * Template code, can copy and paste into any project
 */
class OrderViewModelFactory(private val dataSource: OrderEntityDao) : ViewModelProvider.Factory {
	@Suppress("unchecked_cast")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
			return OrderViewModel(dataSource) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
