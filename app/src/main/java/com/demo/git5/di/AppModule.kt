package com.demo.git5.di

import com.demo.git5.domain.usecase.UserUseCase
import com.demo.git5.usecase.impl.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideUserUseCase(userUseCaseImpl: UserUseCaseImpl) : UserUseCase

}