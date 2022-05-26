package fr.leboncoin.albuminterview.ui.fragments

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import fr.leboncoin.albuminterview.HiltTestActivity
import fr.leboncoin.albuminterview.MainActivity
import fr.leboncoin.albuminterview.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ListAlbumsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<HiltTestActivity> =
        ActivityScenarioRule(HiltTestActivity::class.java)

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun testEmptyViewIsOk() {
        launchFragmentInHiltContainer<ListAlbumsFragment> {
            assert(
                this.view?.findViewById<TextView>(
                    R.id.empty_view
                )?.text.toString() == getString(R.string.no_data_available)

            )
        }
    }

    @Test
    fun recyclerViewNotVisibleWhenNoData() {
        launchFragmentInHiltContainer<ListAlbumsFragment> {
            Assert.assertEquals(View.GONE,this.view?.findViewById<RecyclerView>(
                R.id.recyclerView
            )?.visibility)
        }
    }

    @Test
    fun recyclerViewVisibleWhenData() {
        launchFragmentInHiltContainer<ListAlbumsFragment> {
            InstrumentationRegistry.getInstrumentation().waitForIdle {
                Assert.assertEquals(View.VISIBLE,this.view?.findViewById<RecyclerView>(
                    R.id.recyclerView
                )?.visibility)
            }

        }
    }



}