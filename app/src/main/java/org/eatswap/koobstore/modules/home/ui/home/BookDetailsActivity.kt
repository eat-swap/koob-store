package org.eatswap.koobstore.modules.home.ui.home

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.databinding.ActivityBookDetailsBinding
import org.eatswap.koobstore.modules.book.services.BookService
import org.eatswap.koobstore.modules.cart.CartService

class BookDetailsActivity : AppCompatActivity() {

	private var _binding: ActivityBookDetailsBinding? = null
	private val binding get() = _binding!!

	private var _bookService: BookService? = null
	private val bookService get() = _bookService!!

	private var _cartService: CartService? = null
	private val cartService get() = _cartService!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityBookDetailsBinding.inflate(layoutInflater)
		_bookService = BookService(this.application as KoobApplication)
		_cartService = CartService(this.application as KoobApplication)

		val v = binding.root
		val intent = this.intent!!
		val book = bookService.findById(intent.getIntExtra("book_id", 1))!!

		val imageView = binding.imageDetails // v.findViewById<ImageView>(R.id.image_details)
		Glide.with(this).load(Uri.parse(book.imageUrl)).into(imageView)

		binding.textPrice.text = String.format("$%.2f", book.price)

		binding.textBookBasicInfo.text = "${book.title}\nBy ${book.author}\nISBN ${book.isbn}"

		binding.textDetails.text = book.description

		binding.buttonAddCart.setOnClickListener {
			Toast.makeText(this, "Added to cart: ${book.title} | ${String.format("$%.2f", book.price)}", Toast.LENGTH_SHORT).show()
			cartService.addItem(book.id)
		}

		setContentView(v)
	}
}