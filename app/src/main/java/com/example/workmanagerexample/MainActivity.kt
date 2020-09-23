package com.example.workmanagerexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.workmanagerexample.Constants.Companion.STATE_INPUT
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: Data = Data.Builder()
            .putString(STATE_INPUT, "Hey I am sending the work data")
            .build()

        val request = OneTimeWorkRequest.Builder(MyWorker::class.java).setInputData(data).build()

        btnClickedId.setOnClickListener {
//            Handler().postDelayed(Runnable {
//            },2000)
            WorkManager.getInstance(this).enqueue(request)

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