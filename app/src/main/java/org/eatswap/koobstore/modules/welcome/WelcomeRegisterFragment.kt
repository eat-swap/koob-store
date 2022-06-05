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

			Toast.makeText(context, "$username:$passwordHashed", Toast.LENGTH_SHORT).show()
			val ok = registerService.registerUser(username, passwordHashed)

			if (ok) {
				Toast.makeText(context, "Registered", Toast.LENGTH_SHORT).show()
			} else {
				Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
			}
		}

		return v
	}

}