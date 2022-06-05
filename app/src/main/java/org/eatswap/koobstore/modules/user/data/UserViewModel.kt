package org.eatswap.koobstore.modules.user.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(private val userDao: UserDao) : ViewModel() {
	private fun insert(user: User) {
		viewModelScope.launch {
			userDao.insert(user)
		}
	}

	private fun update(user: User) {
		viewModelScope.launch {
			userDao.update(user)
		}
	}

	private fun delete(user: User) {
		viewModelScope.launch {
			userDao.delete(user)
		}
	}

	fun addNewUser(username: String, password: String) : Boolean {
		// Search for existing user
		var existingUser: User? = null
		runBlocking {
			existingUser = userDao.findByUsername(username)
		}
		if (existingUser != null) {
			// User already exists
			return false
		}

		val user = User(0, username, password)
		insert(user)
		return true
	}
}

/**
 * Template code, can copy and paste into any project
 */
class UserViewModelFactory(private val dataSource: UserDao) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
			return UserViewModel(dataSource) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
