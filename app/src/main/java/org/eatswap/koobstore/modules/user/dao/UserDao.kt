package org.eatswap.koobstore.modules.user.dao

import androidx.room.*
import org.eatswap.koobstore.modules.user.entity.User

@Dao
interface UserDao {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(user: User)

	@Update
	suspend fun update(user: User)

	@Delete
	suspend fun delete(user: User)

	// Get User by ID
	@Query("SELECT * FROM user WHERE id = :id")
	suspend fun getUser(id: String): User?

}