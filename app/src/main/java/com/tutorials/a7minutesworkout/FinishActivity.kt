package com.tutorials.a7minutesworkout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tutorials.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarFinishActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener{
            finish()
        }

        val dao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)

    }

    private fun addDateToDatabase(historyDao: HistoryDao) {

        val c = Calendar.getInstance()
        val dataTime = c.time
        Log.e("Data: ", ""+dataTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dataTime)
        Log.e("Formatted Data :", "" + date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e(
                "Data : ",
                "Added..."
            )
        }
    }
}