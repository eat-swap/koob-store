package org.eatswap.koobstore.modules.user.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
	abstract fun userDao(): UserDao

	companion object {
		@Volatile
		private var INSTANCE: UserDatabase? = null

		const val DB_NAME = "user_db"

		fun getDatabase(context: android.content.Context): UserDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					UserDatabase::class.java,
					DB_NAME
				).fallbackToDestructiveMigration().build()
				INSTANCE = instance
				return instance
			}
		}
	}
}
