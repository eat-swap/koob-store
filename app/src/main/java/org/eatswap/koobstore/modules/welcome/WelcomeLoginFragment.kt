package org.eatswap.koobstore.modules.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.base.BusinessException
import org.eatswap.koobstore.databinding.FragmentWelcomeLoginBinding
import org.eatswap.koobstore.modules.user.services.LoginService

class WelcomeLoginFragment : Fragment() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	private var _binding: FragmentWelcomeLoginBinding? = null
	private val binding get() = _binding!!

	private var _loginService: LoginService? = null
	private val loginService get() = _loginService!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentWelcomeLoginBinding.inflate(inflater, container, false)
		_loginService = LoginService(activity?.application as KoobApplication)

		val v = binding.root

		v.findViewById<Button>(R.id.login_button).setOnClickListener {
			val username = binding.outlinedTextFieldUsername.editText?.text.toString()
			val password = binding.outlinedTextFieldPassword.editText?.text.toString()

			try {
				loginService.loginUser(username, password)
			} catch (e: BusinessException) {
				Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
				return@setOnClickListener
			}

			Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
		}

		// Inflate the layout for this fragment
		return v
	}
}