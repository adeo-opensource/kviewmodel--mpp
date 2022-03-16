package com.adeo.kviewmodel.example

import com.adeo.kviewmodel.example.compose.test.TestScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestScreen()
        }
    }
}
