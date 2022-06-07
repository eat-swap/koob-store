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
	suspend fun findAll(): List<Book>

	@Query("SELECT * FROM books WHERE id = :id")
	suspend fun findById(id: String): Book?

	// Delete all books
	@Query("DELETE FROM books")
	suspend fun deleteAll()

	// Find By Category
	@Query("SELECT * FROM books WHERE category = :category")
	suspend fun findByCategory(category: String): List<Book>
}