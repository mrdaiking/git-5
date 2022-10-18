package com.demo.git5.di

import com.demo.git5.data.source.remote.IUserDataRemote
import com.demo.git5.data.source.remote.impl.UserDataRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun provideUserRemote(userDataRemoteImpl: UserDataRemoteImpl): IUserDataRemote

}