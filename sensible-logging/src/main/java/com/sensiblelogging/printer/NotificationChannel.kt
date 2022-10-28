/*
 * Copyright 2022 Volvo Car Mobility AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sensiblelogging.printer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.sensiblelogging.Line
import com.sensiblelogging.Meta
import com.sensiblelogging.filter.CategoryFilter

@RequiresApi(Build.VERSION_CODES.N)
class NotificationChannel constructor(
    private val context: Context,
    override val filter: CategoryFilter,
    override val default: Boolean = false,
    @DrawableRes private val smallIconRes: Int
) : Channel() {

    companion object {
        const val id = "NotificationChannel"
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

    override val id: String = Companion.id

    private val notificationManager = context.notificationManager()

    override fun print(line: Line, meta: Meta) {
        val id = line.category.asNotificationId()
        val style = getOrCreateStyle(line.category, id)
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
