package org.eatswap.koobstore.base

import androidx.room.Room
import androidx.room.RoomDatabase
import org.eatswap.koobstore.modules.book.data.Book
import org.eatswap.koobstore.modules.book.data.BookDao
import org.eatswap.koobstore.modules.cart.Cart
import org.eatswap.koobstore.modules.cart.CartDao
import org.eatswap.koobstore.modules.orders.data.OrderEntity
import org.eatswap.koobstore.modules.orders.data.OrderEntityDao
import org.eatswap.koobstore.modules.user.data.User
import org.eatswap.koobstore.modules.user.data.UserDao

@androidx.room.Database(entities = [
	User::class,
	Book::class,
	Cart::class,
	OrderEntity::class
	// TODO: add other entities
], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
	abstract fun userDao(): UserDao

	abstract fun bookDao(): BookDao

	abstract fun cartDao(): CartDao

	abstract fun orderEntityDao(): OrderEntityDao

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
