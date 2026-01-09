package com.tetsu

import android.app.Application
import com.tetsu.data.AppContainer
import com.tetsu.data.AppDataContainer

class TetsuHouseholdApp : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
