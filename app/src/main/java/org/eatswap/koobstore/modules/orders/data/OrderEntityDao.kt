package org.eatswap.koobstore.modules.orders.data

import androidx.room.*

@Dao
interface OrderEntityDao {

	@Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
	suspend fun insert(orderEntity: OrderEntity)

	@Update
	suspend fun update(orderEntity: OrderEntity)

	@Delete
	suspend fun delete(orderEntity: OrderEntity)

	@Query("SELECT * FROM orders WHERE id = :id")
	suspend fun findById(id: String): OrderEntity?

	@Query("SELECT * FROM orders WHERE user_id = :userId")
	suspend fun findByUserId(userId: String): List<OrderEntity>

}