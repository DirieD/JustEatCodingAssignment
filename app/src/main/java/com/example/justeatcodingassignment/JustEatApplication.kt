package com.example.justeatcodingassignment

import android.app.Application
import com.example.justeatcodingassignment.data.AppContainer
import com.example.justeatcodingassignment.data.DefaultAppContainer

class JustEatApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}