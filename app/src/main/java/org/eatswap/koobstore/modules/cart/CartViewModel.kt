package org.eatswap.koobstore.modules.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CartViewModel(private val cartDao: CartDao) : ViewModel() {

	private fun insert(item: Cart) {
		viewModelScope.launch {
			cartDao.insert(item)
		}
	}

	private fun delete(item: Cart) {
		viewModelScope.launch {
			cartDao.delete(item)
		}
	}

	private fun update(item: Cart) {
		viewModelScope.launch {
			cartDao.update(item)
		}
	}

	// findByUserId
	private fun findByUserId(userId: String) : List<Cart> {
		return cartDao.findByUserId(userId)
	}

	// deleteByUserId
	private fun deleteByUserId(userId: String) {
		viewModelScope.launch {
			cartDao.deleteByUserId(userId)
		}
	}

}

class CartViewModelFactory(private val cartDao: CartDao) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
			return CartViewModel(cartDao) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
