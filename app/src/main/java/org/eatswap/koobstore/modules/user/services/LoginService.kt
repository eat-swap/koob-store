package org.eatswap.koobstore.modules.user.services

import org.eatswap.koobstore.base.BusinessException
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.modules.user.data.UserViewModel
import org.eatswap.koobstore.modules.user.data.UserViewModelFactory
import org.mindrot.jbcrypt.BCrypt

class LoginService(private val context: KoobApplication) {
	private val userViewModel: UserViewModel = UserViewModelFactory(
		context.database.userDao()
	).create(UserViewModel::class.java)

	fun login(username: String, password: String): Boolean {
		val user = userViewModel.findByUsername(username) ?: throw BusinessException(
			"User not found"
		)
		if (!BCrypt.checkpw(password, user.password)) {
			throw BusinessException("Invalid password")
		}
		_loggedInUser = user.username
		_loggedInUserId = user.id
		return true
	}

	fun logout() {
		_loggedInUser = null
		_loggedInUserId = null
	}

	companion object {
		private var _loggedInUser: String? = null
		val loggedInUser get() = _loggedInUser

		private var _loggedInUserId: Int? = null
		val loggedInUserId get() = _loggedInUserId
	}
}