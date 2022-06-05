package org.eatswap.koobstore.modules.book.data

import androidx.room.*

@Dao
interface BookDao {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(book: Book)

	@Update
	suspend fun update(book: Book)

	@Delete
	suspend fun delete(book: Book)

	// Get books
	@Query("SELECT * FROM books")
	fun findAll(): List<Book>

	@Query("SELECT * FROM books WHERE id = :id")
	fun findById(id: String): Book?
}