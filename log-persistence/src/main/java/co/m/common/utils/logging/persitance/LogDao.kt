package co.m.common.utils.logging.persitance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class LogDao {
    @Query("SELECT * FROM logEntity")
    abstract fun getAll(): Observable<List<LogEntity>>

    fun getAllLines(): Observable<List<PersistedLogLine>> {
        return getAll().map { lines -> lines.map { PersistedLogLine(it.toLine(), it.meta.toMeta()) } }
    }

    @Insert
    abstract fun insert(entities: List<LogEntity>): Completable

    @Query("DELETE FROM logEntity WHERE id NOT IN (SELECT id from logEntity ORDER BY timestamp DESC LIMIT :count)")
    abstract fun rollover(count: Int): Completable

    @Query("DELETE FROM logEntity")
    abstract fun clear(): Completable

    @Query("SELECT COUNT(*) FROM logEntity")
    abstract fun rowCount(): Observable<Int>
}
