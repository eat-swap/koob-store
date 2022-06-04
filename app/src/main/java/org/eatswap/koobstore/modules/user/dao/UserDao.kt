package org.eatswap.koobstore.modules.user.dao

import android.database.Cursor
import android.database.sqlite.SQLiteStatement
import org.eatswap.koobstore.modules.user.entity.User
import org.greenrobot.greendao.AbstractDao
import org.greenrobot.greendao.AbstractDaoSession
import org.greenrobot.greendao.database.Database
import org.greenrobot.greendao.database.DatabaseStatement
import org.greenrobot.greendao.internal.DaoConfig
import java.util.Date

class UserDao : AbstractDao<User, Long> {

	/**
	 * Constructors that match its base class.
	 */
	constructor(config: DaoConfig?) : super(config)

	constructor(config: DaoConfig?, daoSession: AbstractDaoSession?) : super(config, daoSession)

	override fun readEntity(cursor: Cursor?, offset: Int): User {
		cursor ?: throw IllegalArgumentException("Cursor must not be null")
		return User(
			if (cursor.isNull(offset)) null else cursor.getLong(offset), // id
			Date(cursor.getLong(offset + 1)), // createdAt
			Date(cursor.getLong(offset + 2)), // updatedAt
			cursor.getString(offset + 3), // username
			cursor.getString(offset + 4), // password
		)
	}

	override fun readEntity(cursor: Cursor?, entity: User?, offset: Int) {
		cursor ?: throw IllegalArgumentException("Cursor must not be null")
		entity ?: throw IllegalArgumentException("Entity must not be null")
		entity.id = if (cursor.isNull(offset)) null else cursor.getLong(offset)
		entity.createdAt = Date(cursor.getLong(offset + 1))
		entity.updatedAt = Date(cursor.getLong(offset + 2))
		entity.username = cursor.getString(offset + 3)
		entity.password = cursor.getString(offset + 4)
	}

	override fun readKey(cursor: Cursor?, offset: Int): Long? {
		return if (cursor!!.isNull(offset)) null else cursor.getLong(offset)
	}

	override fun bindValues(stmt: DatabaseStatement?, entity: User?) {
		stmt!!.clearBindings()
		val id = entity!!.id
		if (id != null) {
			stmt.bindLong(1, id)
		}
		stmt.bindLong(2, entity.createdAt.time)
		stmt.bindLong(3, entity.updatedAt.time)
		stmt.bindString(4, entity.username)
		stmt.bindString(5, entity.password)
	}

	override fun bindValues(stmt: SQLiteStatement?, entity: User?) {
		stmt!!.clearBindings()
		val id = entity!!.id
		if (id != null) {
			stmt.bindLong(1, id)
		}
		stmt.bindLong(2, entity.createdAt.time)
		stmt.bindLong(3, entity.updatedAt.time)
		stmt.bindString(4, entity.username)
		stmt.bindString(5, entity.password)
	}

	override fun updateKeyAfterInsert(entity: User?, rowId: Long): Long {
		entity!!.id = rowId
		return rowId
	}

	override fun getKey(entity: User?): Long = entity!!.id!!

	override fun hasKey(entity: User?): Boolean = entity!!.id != null

	override fun isEntityUpdateable(): Boolean = true

	companion object {
		fun initDb(db: Database, ifExist: Boolean = true) {
			db.execSQL("""
				CREATE TABLE IF NOT EXISTS `User` (
					`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
					`createdAt` INTEGER NOT NULL,
					`updatedAt` INTEGER NOT NULL,
					`username` TEXT NOT NULL,
					`password` TEXT NOT NULL
				);
			""".trimIndent(), emptyArray())
		}
	}
}