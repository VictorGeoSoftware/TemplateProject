package com.training.victor.development.data

import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.network.ProfilesRepository
import io.reactivex.Observable

class DataManager(private val profilesRepository: ProfilesRepository) {

    fun getProfilesList(): Observable<ArrayList<ProfileItem>> {
        return profilesRepository.getProfilesList().flatMap {
            Observable.just(it)
        }
    }


}