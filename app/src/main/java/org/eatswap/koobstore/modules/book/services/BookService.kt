package org.eatswap.koobstore.modules.book.services

import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.data.BookViewModel
import org.eatswap.koobstore.modules.book.data.BookViewModelFactory

class BookService(private val context: KoobApplication) {

	private val bookViewModel: BookViewModel = BookViewModelFactory(
		context.database.bookDao()
	).create(BookViewModel::class.java)

	fun findAll(): List<Book> {
		return bookViewModel.findAll()
	}

	fun findById(id: Int): Book? {
		return bookViewModel.findById(id)
	}

	fun insert(book: Book) {
		bookViewModel.insert(book)
	}

	fun deleteAll() {
		bookViewModel.deleteAll()
	}

}