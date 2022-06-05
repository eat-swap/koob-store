package org.eatswap.koobstore.modules.user.services

import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.modules.user.data.UserViewModel
import org.eatswap.koobstore.modules.user.data.UserViewModelFactory

class RegisterServices(private val context: KoobApplication) {
	private val userViewModel: UserViewModel = UserViewModelFactory(
		context.database.userDao()
	).create(UserViewModel::class.java)

	fun registerUser(username: String, password: String): Boolean {
		userViewModel.addNewUser(username, password)
		return true
	}
}