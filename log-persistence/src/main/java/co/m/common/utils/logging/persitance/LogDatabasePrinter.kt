package co.m.common.utils.logging.persitance

import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta
import co.m.common.utils.logging.filter.Filter
import co.m.common.utils.logging.printer.Printer
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class LogDatabasePrinter(private val logDao: LogDao, override val filter: Filter) : Printer() {

    companion object {
        private const val MAX_LINES = 5000
    }

    private val lineFeed: PublishSubject<LogEntity> = PublishSubject.create()
    private val linesBatch: Observable<List<LogEntity>> = lineFeed.buffer(3, TimeUnit.SECONDS, MAX_LINES)
    private var subscription: Disposable? = null

    fun create() {
        subscription = linesBatch
            .subscribeOn(Schedulers.io())
            .concatMapCompletable { appendBatch(it) }
            .subscribe()
    }

    fun destroy() {
        subscription?.dispose()
    }

    override fun printFiltered(line: Line, meta: Meta) {
        append(
            line.toEntity(meta)
        )
    }

    private fun append(line: LogEntity) {
        lineFeed.onNext(line)
    }

    private fun appendBatch(it: List<LogEntity>) =
        logDao.rollover(MAX_LINES).andThen(logDao.insert(it))
}
