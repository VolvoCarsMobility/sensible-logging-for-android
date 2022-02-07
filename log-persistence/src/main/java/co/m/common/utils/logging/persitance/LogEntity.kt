package co.m.common.utils.logging.persitance

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LogEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val timestamp: Long,
    val categories: List<String>,
    val preFormatted: Boolean,
    val level: Level,
    val message: String,
    @Embedded(prefix = "meta_")
    val meta: MetaEntity,
    val throwable: Throwable?,
    val parameters: Map<String, String>?
) {

    data class MetaEntity(
        val className: String,
        val simpleClassName: String,
        val functionName: String,
        val lineNumber: Int,
        val threadName: String,
        val fileName: String
    )

    enum class Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}
