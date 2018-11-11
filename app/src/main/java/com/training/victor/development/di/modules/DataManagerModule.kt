package com.training.victor.development.di.modules

import com.training.victor.development.data.DataManager
import com.training.victor.development.network.ProfilesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataManagerModule {
    @Provides
    @Singleton
    fun provideDataManager(profileRepository: ProfilesRepository): DataManager = DataManager(profileRepository)
}