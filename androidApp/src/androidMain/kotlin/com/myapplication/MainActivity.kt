package com.myapplication

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import login.navigation.components.DefaultRootComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create the root component before starting Compose
        val root = DefaultRootComponent(componentContext = defaultComponentContext())
        setContent {
            MainView(root)
        }
    }
}