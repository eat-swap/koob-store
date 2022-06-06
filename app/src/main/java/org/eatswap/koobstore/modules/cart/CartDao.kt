package org.eatswap.koobstore.modules.cart

import androidx.room.*

@Dao
interface CartDao {

	@Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
	suspend fun insert(cart: Cart)

	@Update
	suspend fun update(cart: Cart)

	@Delete
	suspend fun delete(cart: Cart)

	// findByUserId
	@Query("SELECT * FROM cart WHERE user_id = :userId")
	fun findByUserId(userId: String): List<Cart>

	// findByUserIdAndBookId
	@Query("SELECT * FROM cart WHERE user_id = :userId AND book_id = :bookId")
	fun findByUserIdAndBookId(userId: String, bookId: String): Cart?

	// deleteByUserId
	@Query("DELETE FROM cart WHERE user_id = :userId")
	fun deleteByUserId(userId: String)

}