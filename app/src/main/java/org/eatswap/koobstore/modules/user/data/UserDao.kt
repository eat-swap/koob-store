package org.eatswap.koobstore.modules.user.data

import androidx.room.*

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
	suspend fun findById(id: String): User?

	// Get User by Username
	@Query("SELECT * FROM user WHERE username = :username")
	suspend fun findByUsername(username: String): User?

}