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
import org.eatswap.koobstore.base.BusinessException
import org.eatswap.koobstore.databinding.FragmentWelcomeRegisterBinding
import org.eatswap.koobstore.modules.user.services.RegisterService
import org.mindrot.jbcrypt.BCrypt

class WelcomeRegisterFragment : Fragment() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	private var _binding: FragmentWelcomeRegisterBinding? = null
	private val binding get() = _binding!!

	private var _registerService: RegisterService? = null
	private val registerService get() = _registerService!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentWelcomeRegisterBinding.inflate(inflater, container, false)
		_registerService = RegisterService(activity?.application as KoobApplication)

		val v = binding.root

		v.findViewById<Button>(R.id.buttonRegister).setOnClickListener {
			val username = binding.outlinedTextFieldUsername.editText?.text.toString()
			val password = binding.outlinedTextFieldPassword.editText?.text.toString()
			val passwordHashed = BCrypt.hashpw(password, BCrypt.gensalt())

			try {
				registerService.registerUser(username, passwordHashed)
			} catch (e: BusinessException) {
				Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			Toast.makeText(context, "Registered successfully, please login", Toast.LENGTH_SHORT).show()
			findNavController().navigate(R.id.action_welcomeRegisterFragment_to_welcomeLoginFragment)
		}

		return v
	}

}