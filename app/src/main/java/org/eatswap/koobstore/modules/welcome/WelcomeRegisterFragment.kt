package org.eatswap.koobstore.modules.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.FragmentWelcomeRegisterBinding

class WelcomeRegisterFragment : Fragment() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	private var _binding: FragmentWelcomeRegisterBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentWelcomeRegisterBinding.inflate(inflater, container, false)

		val v = binding.root

		v.findViewById<Button>(R.id.buttonRegister).setOnClickListener {
			val str = binding.outlinedTextFieldUsername.editText?.text.toString()
			Toast.makeText(context, str.length.toString(), Toast.LENGTH_SHORT).show()
		}

		return v
	}

	companion object {
	}
}