package org.eatswap.koobstore.modules.orders.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [
	Index(value = ["id"], unique = true)
], tableName = "orders")
data class OrderEntity(

	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,

	@ColumnInfo(name = "user_id")
	val userId: Int,

	@ColumnInfo(name = "item_id_count")
	val itemIdCount: String,

	@ColumnInfo(name = "created_at")
	val createdAt: Long, // Timestamp

)