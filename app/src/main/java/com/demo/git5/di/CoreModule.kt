package com.demo.git5.di

import com.demo.git5.api.APIWorker
import com.demo.git5.api.IGithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideNetworkService(): IGithubService {
        return APIWorker.retrofitClient().create(IGithubService::class.java)
    }
}