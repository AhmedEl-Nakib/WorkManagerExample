package com.example.workmanagerexample

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.example.workmanagerexample.Constants.Companion.STATE_INPUT
import com.example.workmanagerexample.Constants.Companion.STATE_OUTPUT


class MyWorker(private val context: Context, private val workerParameters: WorkerParameters) : Worker(context,workerParameters) {


    override fun doWork(): Result {
        val data = inputData.getString(STATE_INPUT)

        displayNotification("Hey I am your work", data!!);
//        val data1: Data = Data.Builder()
//            .putString(STATE_OUTPUT, "Task Finished Successfully")
//            .build()
//


//        setForegroundAsync(createForegroundInfo(""))

        return Result.success()
    }


    // Creates an instance of ForegroundInfo which can be used to update the
    // ongoing notification.

//    private fun createForegroundInfo(progress: String): ForegroundInfo {
//        val id = applicationContext.getString(R.string.notification_channel_id)
//        val title = applicationContext.getString(R.string.notification_title)
//        val cancel = applicationContext.getString(R.string.cancel_download)
//        // This PendingIntent can be used to cancel the worker
//        val intent = WorkManager.getInstance(applicationContext)
//            .createCancelPendingIntent(getId())
//
//        // Create a Notification channel if necessary
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createChannel()
//        }
//
//        val notification = NotificationCompat.Builder(applicationContext, id)
//            .setContentTitle(title)
//            .setTicker(title)
//            .setContentText(progress)
//            .setSmallIcon(R.drawable.ic_work_notification)
//            .setOngoing(true)
//            // Add the cancel action to the notification which can
//            // be used to cancel the worker
//            .addAction(android.R.drawable.ic_delete, cancel, intent)
//            .build()
//
//        return ForegroundInfo(notification)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createChannel() {
//        // Create a Notification channel
//    }
//

    private fun displayNotification(task: String, desc: String) {
        val manager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "simplifiedcoding",
                "simplifiedcoding",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        val builder =
            NotificationCompat.Builder(applicationContext, "simplifiedcoding")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.drawable.ic_dialog_info)
        manager.notify(1, builder.build())
    }
}