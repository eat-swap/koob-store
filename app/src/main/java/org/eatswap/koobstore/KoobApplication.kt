package org.eatswap.koobstore

import android.app.Application
import org.eatswap.koobstore.base.Database

class KoobApplication : Application() {
	val database: Database by lazy { Database.getDatabase(this) }

	override fun onCreate() {
		super.onCreate()
		init()
	}

	private fun init() {
		// May be useful in the future
	}
}