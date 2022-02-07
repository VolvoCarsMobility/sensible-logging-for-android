package co.m.common.utils.logging.persitance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LogEntity::class], version = 1, exportSchema = false)
@TypeConverters(LogDatabaseTypeConverters::class)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao

    companion object {
        private const val FILE_NAME = "log-database.db"
        fun init(context: Context): LogDao {
            return Room.databaseBuilder(context, LogDatabase::class.java, FILE_NAME)
                .build().logDao()
        }
    }
}
