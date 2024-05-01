package com.devartdgo.test2selccapp.di

import com.devartdgo.test2selccapp.network.FileSystemCacheProvider
import com.devartdgo.test2selccapp.repository.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Singleton
    @Provides
    fun providesRepository(cacheProvider: FileSystemCacheProvider) = StudentRepository(cacheProvider)

    @Singleton
    @Provides
    fun providesApi() = FileSystemCacheProvider()
}