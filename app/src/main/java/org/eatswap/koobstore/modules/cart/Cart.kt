package org.eatswap.koobstore.modules.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [
	Index(value = ["id"], unique = true)
])
data class Cart(

	@PrimaryKey(autoGenerate = true)
	val Id: Int = 0,

	@ColumnInfo(name = "user_id")
	val userId: Int,

	@ColumnInfo(name = "book_id")
	val bookId: Int,

	@ColumnInfo(name = "quantity")
	val quantity: Int

)