package org.eatswap.koobstore.modules.orders.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
	fun findById(id: Int): OrderEntity? {
		return runBlocking {
			orderEntityDao.findById(id.toString())
		}
	}

	// findByUserId
	fun findByUserId(userId: Int): List<OrderEntity> {
		return runBlocking {
			orderEntityDao.findByUserId(userId.toString())
		}
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
