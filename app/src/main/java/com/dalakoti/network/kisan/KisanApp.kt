package com.dalakoti.network.kisan

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KisanApp: Application() {

    override fun onCreate() {
        super.onCreate()
//        if(BuildConfig.DEBUG){
//            Stetho.initializeWithDefaults(this)
//            Pluto.Installer(this)
//                .addPlugin(PlutoNetworkPlugin("network"))
//                .install()
//        }
    }

}