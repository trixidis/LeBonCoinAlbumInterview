package fr.leboncoin.albuminterview.ui.fragments

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.leboncoin.albuminterview.MainActivity
import fr.leboncoin.albuminterview.R
import fr.leboncoin.presentation.HiltTestRunner
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
class ListAlbumsFragmentTest : HiltTestRunner() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_name_matches_data_source() {
        // Inject the dependencies to the test (if there is any @Inject field in the test)
        hiltRule.inject()
        Espresso.onView(ViewMatchers.withId(R.id.empty_view))
            .check(
                ViewAssertions.matches(
                    Matchers.allOf(
                        ViewMatchers.isDisplayed(),
                        ViewMatchers.withText(R.string.no_data_available)
                    )
                )
            )
    }



}