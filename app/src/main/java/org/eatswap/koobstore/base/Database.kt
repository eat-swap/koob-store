package org.eatswap.koobstore.base

import androidx.room.Room
import androidx.room.RoomDatabase
import org.eatswap.koobstore.modules.user.data.User
import org.eatswap.koobstore.modules.user.data.UserDao

@androidx.room.Database(entities = [
	User::class,
	// TODO: add other entities
], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
	abstract fun userDao(): UserDao

	companion object {
		@Volatile
		private var INSTANCE: Database? = null

		const val DB_NAME = "koob_db"

		fun getDatabase(context: android.content.Context): Database {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					Database::class.java,
					DB_NAME
				).fallbackToDestructiveMigration().build()
				INSTANCE = instance
				return instance
			}
		}
	}
}
