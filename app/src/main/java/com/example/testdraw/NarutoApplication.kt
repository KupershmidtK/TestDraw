package com.example.testdraw

import android.app.Application
import com.example.testdraw.data.AppContainer
import com.example.testdraw.data.DefaultAppContainer

class NarutoApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}