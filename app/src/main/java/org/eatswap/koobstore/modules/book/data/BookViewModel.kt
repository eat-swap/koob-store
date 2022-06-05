package org.eatswap.koobstore.modules.book.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel(private val bookDao: BookDao) : ViewModel() {

	private fun insert(book: Book) {
		viewModelScope.launch {
			bookDao.insert(book)
		}
	}

	// update
	private fun update(book: Book) {
		viewModelScope.launch {
			bookDao.update(book)
		}
	}

	// delete
	private fun delete(book: Book) {
		viewModelScope.launch {
			bookDao.delete(book)
		}
	}

	// Find all
	fun findAll() : List<Book> = bookDao.findAll()

	// Find by id
	fun findById(id: Long) : Book? = bookDao.findById(id.toString())

}