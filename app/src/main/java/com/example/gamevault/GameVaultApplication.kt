package com.example.gamevault

import android.app.Application
import com.example.gamevault.di.AppContainer
import com.example.gamevault.di.DefaultAppContainer

class GameVaultApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
