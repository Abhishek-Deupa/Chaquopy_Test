package com.example.chaquopytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        //initiating python instance and integrating python module
        val py = Python.getInstance()
        val module = py.getModule("main")

        //using variables
        val val1 = module["val1"]?.toString()
        val val2 = module["val2"]?.toInt()

        //using function
        val val3 = module["stringReturner"]?.call()

        setContent {
            AppMain(val1.toString(), val2.toString(), val3.toString())
        }
    }
}

@Composable
fun AppMain(val1: String, val2: String, val3: String) {
    Box(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(val1)
            Text(text = val2)
            Text(text = val3)
        }
    }
}