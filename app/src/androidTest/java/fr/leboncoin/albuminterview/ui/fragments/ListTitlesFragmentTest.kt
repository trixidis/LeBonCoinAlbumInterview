package fr.leboncoin.albuminterview.ui.fragments

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.leboncoin.albuminterview.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ListTitlesFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


//    @get:Rule(order = 1)
//    var activityRule: ActivityScenarioRule<HiltTestActivity> =
//        ActivityScenarioRule(HiltTestActivity::class.java)

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun progressIsVisibleOnStart() {
        launchFragmentInHiltContainer<ListTitlesFragment> {
            Assert.assertEquals(View.VISIBLE,
                this.view?.findViewById<ProgressBar>(
                    R.id.progressBarTitles
                )?.visibility
            )
        }
    }

    @Test
    fun recyclerViewNotVisibleWhenNoData() {
        launchFragmentInHiltContainer<ListTitlesFragment> {
            Assert.assertEquals(
                View.GONE,this.view?.findViewById<RecyclerView>(
                R.id.recyclerViewTitles
            )?.visibility)
        }
    }



}