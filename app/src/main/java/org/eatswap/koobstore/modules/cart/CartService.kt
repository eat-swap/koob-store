package org.eatswap.koobstore.modules.cart

import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.modules.user.services.LoginService

class CartService(private val context: KoobApplication) {

	private val cartViewModel: CartViewModel = CartViewModelFactory(
		context.database.cartDao()
	).create(CartViewModel::class.java)

	fun addItem(bookId: Int) {
		val userId = LoginService.loggedInUserId!!
		val cartItem = cartViewModel.findByUserIdAndBookId(userId.toString(), bookId.toString())

		if (cartItem != null) {
			++cartItem.quantity
			cartViewModel.update(cartItem)
		} else {
			cartViewModel.insert(Cart(0, userId, bookId, 1))
		}
	}

}