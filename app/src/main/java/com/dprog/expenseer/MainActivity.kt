package com.dprog.expenseer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dprog.expenseer.navigation.AppNavigationHost
import com.dprog.expenseer.ui.theme.ExpenseerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseerTheme {
                AppNavigationHost()
            }
        }
    }
}
