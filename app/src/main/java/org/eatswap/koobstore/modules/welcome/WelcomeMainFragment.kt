package org.eatswap.koobstore.modules.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.services.BookService
import kotlin.random.Random

class WelcomeMainFragment : Fragment() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	private var _bookService: BookService? = null
	private val bookService get() = _bookService!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val v = inflater.inflate(R.layout.fragment_welcome_main, container, false)

		_bookService = BookService(requireActivity().application as KoobApplication)

		v?.findViewById<Button>(R.id.login_button)?.setOnClickListener {
			findNavController().navigate(R.id.action_welcomeMain_to_welcomeLoginFragment)
		}

		v?.findViewById<Button>(R.id.register_button)?.setOnClickListener {
			findNavController().navigate(R.id.action_welcomeMain_to_welcomeRegisterFragment)
		}

		v.findViewById<Button>(R.id.fill_database_button)?.setOnClickListener {
			bookService.deleteAll()
			for (i in 0..50) {
				val book = Book(0, "Title $i", "Author $i", "ISBN $i", "Description $i", "https://img.bytepic.cn/blindbox1.jpg", Random.nextDouble() * 100)
				bookService.insert(book)
			}
			Toast.makeText(requireContext(), "Database filled", Toast.LENGTH_SHORT).show()
		}

		return v
	}

	companion object {

	}
}