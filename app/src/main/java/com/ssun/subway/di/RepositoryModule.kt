package com.ssun.subway.di

import com.ssun.subway.data.repository.MainRepositoryImpl
import com.ssun.subway.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

}
