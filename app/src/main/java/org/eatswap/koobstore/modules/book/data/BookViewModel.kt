package org.eatswap.koobstore.modules.book.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BookViewModel(private val bookDao: BookDao) : ViewModel() {

	fun insert(book: Book) {
		viewModelScope.launch {
			bookDao.insert(book)
		}
	}

	// update
	fun update(book: Book) {
		viewModelScope.launch {
			bookDao.update(book)
		}
	}

	// delete
	fun delete(book: Book) {
		viewModelScope.launch {
			bookDao.delete(book)
		}
	}

	// Find all
	fun findAll() : List<Book> {
		return runBlocking {
			bookDao.findAll()
		}
	}

	// Find by id
	fun findById(id: Int) : Book? {
		var ret: Book? = null
		runBlocking {
			ret = bookDao.findById(id.toString())
		}
		return ret
	}

	// Delete all
	fun deleteAll() {
		viewModelScope.launch {
			bookDao.deleteAll()
		}
	}

}

class BookViewModelFactory(private val bookDao: BookDao) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
			return BookViewModel(bookDao) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
