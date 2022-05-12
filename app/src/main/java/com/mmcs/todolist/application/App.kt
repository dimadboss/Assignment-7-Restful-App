package com.mmcs.todolist.application

import android.app.Application
import com.mmcs.todolist.data.network.DishTypeRepository


class App : Application() {
    companion object {
        lateinit var dishTypeRepository: DishTypeRepository
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        dishTypeRepository = DishTypeRepository()
    }

}