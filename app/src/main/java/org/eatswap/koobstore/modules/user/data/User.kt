package org.eatswap.koobstore.modules.user.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [
	Index(value = ["id", "username"], unique = true)
], tableName = "users")
data class User (
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,

	@ColumnInfo(name = "username")
	val username: String,

	@ColumnInfo(name = "password")
	val password: String,
)