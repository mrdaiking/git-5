package com.demo.git5.di

import com.demo.git5.domain.repository.IUserRepository
import com.demo.git5.repository.impl.IUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepositoryImpl: IUserRepositoryImpl): IUserRepository

}