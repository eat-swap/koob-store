package org.eatswap.koobstore.modules.user.services

import org.eatswap.koobstore.modules.user.entity.User

class RegisterServices {
	companion object {
		fun registerUser(username: String, password: String): Boolean {
			User(username, password).save()
			return true
		}
	}
}