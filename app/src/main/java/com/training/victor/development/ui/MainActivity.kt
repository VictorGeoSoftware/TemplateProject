package com.training.victor.development.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.presenter.ProfilesPresenter
import com.training.victor.development.utils.getDpFromValue
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProfilesPresenter.ProfilesView {

    @Inject lateinit var profilesPresenter: ProfilesPresenter
    private val mProfilesList = ArrayList<ProfileItem>()
    private lateinit var profilesAdapter: ProfilesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)

        val myGridLayoutManager = GridLayoutManager(this, 2)
        lstProfiles.layoutManager = myGridLayoutManager
        lstProfiles.addItemDecoration(SpaceDecorator(getDpFromValue(10)))
        profilesAdapter = ProfilesAdapter(mProfilesList)
        lstProfiles.adapter = profilesAdapter

        profilesPresenter.view = this
        profilesPresenter.getProfilesList()
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).releasePresenterComponent()
    }



    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER VIEW ---------------------------------------------
    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onProfilesListReceived(profilesList: ArrayList<ProfileItem>) {
        mProfilesList.clear()
        mProfilesList.addAll(profilesList)
        profilesAdapter.notifyDataSetChanged()
    }

    override fun onProfilesListError() {
        mProfilesList.clear()
        profilesAdapter.notifyDataSetChanged()
    }
}
