package com.example.workmanagerexample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanagerexample.Constants.Companion.STATE_INPUT
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: Data = Data.Builder()
            .putString(STATE_INPUT, "Hey I am sending the work data")
            .build()

        val constraints = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.CONNECTED)
        }.build()

//        setRequiresCharging(true)
//        setRequiresStorageNotLow(true)

        val request = OneTimeWorkRequest.Builder(MyWorker::class.java).setInitialDelay(2, TimeUnit.SECONDS).setInputData(data).setConstraints(constraints).build()


        btnClickedId.setOnClickListener {
//            Handler().postDelayed(Runnable {
                WorkManager.getInstance(this).enqueue(request)
//            },2000)

        }


        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id).observe(this, Observer {
            if(it != null) {
//                if(it.state.isFinished){
//                    val data = it.outputData.getString(STATE_INPUT)
//                    txtViewId.append("$data \n")
//                }
                val status = it.state.name
                txtViewId.append("$status \n")
            }
        })
    }
}