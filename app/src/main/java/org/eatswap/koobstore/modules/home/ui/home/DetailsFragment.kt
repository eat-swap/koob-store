package org.eatswap.koobstore.modules.home.ui.home

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.FragmentDetailsBinding
import org.eatswap.koobstore.modules.book.services.BookService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
	// TODO: Rename and change types of parameters
	private var param1: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			param1 = it.getString(ARG_PARAM1)
		}
	}

	private var _binding: FragmentDetailsBinding? = null
	private val binding get() = _binding!!

	private var _bookService: BookService? = null
	private val bookService get() = _bookService!!

	@SuppressLint("SetTextI18n")
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentDetailsBinding.inflate(inflater, container, false)
		_bookService = BookService(activity?.application as KoobApplication)

		val v = binding.root

		return v
	}

	companion object {
		/**
		 * Use this factory method to create a new instance of
		 * this fragment using the provided parameters.
		 *
		 * @param param1 Parameter 1.
		 * @return A new instance of fragment DetailsFragment.
		 */
		// TODO: Rename and change types and number of parameters
		@JvmStatic
		fun newInstance(param1: String) =
			DetailsFragment().apply {
				arguments = Bundle().apply {
					putString(ARG_PARAM1, param1)
				}
			}
	}
}