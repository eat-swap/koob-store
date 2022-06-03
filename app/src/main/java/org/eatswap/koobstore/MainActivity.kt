package org.eatswap.koobstore

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import org.eatswap.koobstore.base.BaseActivity

class MainActivity : BaseActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
	}
}