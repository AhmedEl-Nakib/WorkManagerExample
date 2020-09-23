package com.example.workmanagerexample

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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


        return Result.success()
    }


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