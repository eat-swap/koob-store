package org.eatswap.koobstore.modules.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import org.eatswap.koobstore.R

class WelcomeMainFragment : Fragment() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val v = inflater.inflate(R.layout.fragment_welcome_main, container, false)

		v?.findViewById<Button>(R.id.login_button)?.setOnClickListener {
			findNavController().navigate(R.id.action_welcomeMain_to_welcomeLoginFragment)
		}

		v?.findViewById<Button>(R.id.register_button)?.setOnClickListener {
			findNavController().navigate(R.id.action_welcomeMain_to_welcomeRegisterFragment)
		}

		return v
	}

	companion object {

	}
}