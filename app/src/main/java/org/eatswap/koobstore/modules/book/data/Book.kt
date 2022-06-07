package org.eatswap.koobstore.modules.book.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [
	Index(value = ["id"], unique = true)
], tableName = "books")
data class Book(
	@PrimaryKey(autoGenerate = true)
	val id: Int,

	@ColumnInfo(name = "title")
	val title: String,

	@ColumnInfo(name = "author")
	val author: String,

	@ColumnInfo(name = "isbn")
	val isbn: String,

	@ColumnInfo(name = "description")
	val description: String,

	@ColumnInfo(name = "image_url")
	val imageUrl: String,

	@ColumnInfo(name = "price")
	val price: Double,

	@ColumnInfo(name = "category")
	val category: String,

	// No more useless columns here
)