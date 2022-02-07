package co.m.common.utils.logging.printer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import co.m.common.utils.logging.Line
import co.m.common.utils.logging.Meta
import co.m.common.utils.logging.filter.CategoryFilter

@RequiresApi(Build.VERSION_CODES.N)
class NotificationPrinter constructor(
    private val context: Context,
    override val filter: CategoryFilter,
    @DrawableRes private val smallIconRes: Int
) : Printer() {

    companion object {
        private const val channelId = "channel_id_log_output"
        fun createNotificationChannel(
            context: Context,
            channelName: String = "Log output",
            channelDescription: String = "Output from the application logging"
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_LOW

                val channel = NotificationChannel(channelId, channelName, importance)
                    .apply {
                        description = channelDescription
                    }

                context.notificationManager().createNotificationChannel(channel)
            }
        }

        private fun Context.notificationManager(): NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val notificationManager = context.notificationManager()

    override fun printFiltered(line: Line, meta: Meta) {
        val category = extractCategory(line)
        val id = category.asNotificationId()
        val style = getOrCreateStyle(category, id)
        val notification: Notification = createNotification(line, meta, style)
        notificationManager.notify(id, notification)
    }

    private fun createNotification(
        line: Line,
        meta: Meta,
        style: NotificationCompat.MessagingStyle
    ): Notification {
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(smallIconRes)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setOnlyAlertOnce(true)
        style.addMessage(
            NotificationCompat.MessagingStyle.Message(
                line.message,
                line.timestamp,
                meta.simpleClassName.substringBefore("$").asPerson()
            )
        )
        builder.setStyle(style)
        return builder.build()
    }

    private fun String.asNotificationId() = hashCode()

    private fun extractCategory(line: Line) =
        line.categories.intersect(filter.categories).first()

    private fun findActiveNotification(notificationId: Int): Notification? {
        return notificationManager
            .activeNotifications.find { it.id == notificationId }?.notification
    }

    private fun getOrCreateStyle(category: String, notificationId: Int): NotificationCompat.MessagingStyle {
        return findActiveNotification(notificationId)?.let {
            NotificationCompat.MessagingStyle.extractMessagingStyleFromNotification(
                it
            )
        } ?: NotificationCompat.MessagingStyle(category.asPerson())
            .also {
                it.conversationTitle = category
            }
    }

    private fun String.asPerson() = Person.Builder()
        .setName(this)
        .setBot(true)
        .build()
}
