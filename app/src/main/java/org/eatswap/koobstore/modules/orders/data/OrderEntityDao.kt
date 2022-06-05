package org.eatswap.koobstore.modules.orders.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OrderEntityDao {

	@Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
	suspend fun insert(orderEntity: OrderEntity)

	@Update
	suspend fun update(orderEntity: OrderEntity)

	@Delete
	suspend fun delete(orderEntity: OrderEntity)

	@Query("SELECT * FROM orders WHERE id = :id")
	fun findById(id: String): OrderEntity?

	@Query("SELECT * FROM orders WHERE user_id = :userId")
	fun findByUserId(userId: String): List<OrderEntity>

}