package org.eatswap.koobstore.modules.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.eatswap.koobstore.KoobApplication
import org.eatswap.koobstore.R
import org.eatswap.koobstore.databinding.FragmentHomeBinding
import org.eatswap.koobstore.modules.user.services.LoginService

class HomeFragment : Fragment() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	private var _loginService: LoginService? = null
	private val loginService get() = _loginService!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		_loginService = LoginService(activity?.application as KoobApplication)

		val v = binding.root
		val nc = requireActivity().findNavController(R.id.navigation_home)

		v.findViewById<BottomNavigationView>(R.id.bottom_nav).setOnItemSelectedListener {
			NavigationUI.onNavDestinationSelected(it, nc)
		}

		return v
	}
}