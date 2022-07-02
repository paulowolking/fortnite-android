package com.wolking.fortnite

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wolking.fortnite.presentation.MainActivity

import org.junit.runner.RunWith

import org.junit.Rule
import org.junit.Test

class NavigationInstrumentedTest {

    private var navController: TestNavHostController =
        TestNavHostController(ApplicationProvider.getApplicationContext())

    init {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
    }

    @get:Rule
    var mainActivity: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun whenActivityIsLaunched_checkFirstFragment() {
        onView(withId(R.id.bt_edit)).check(matches(isDisplayed()))
    }

    @Test
    fun checkNavigation() {
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.tab)).check(matches(isDisplayed()))

        onView(withId(R.id.navigation_dashboard)).perform(click())
        onView(withId(R.id.rv_shop)).check(matches(isDisplayed()))

        onView(withId(R.id.navigation_notifications)).perform(click())
        onView(withId(R.id.rv_news)).check(matches(isDisplayed()))

        onView(withId(R.id.navigation_friends)).perform(click())
        onView(withId(R.id.compose_view)).check(matches(isDisplayed()))
    }

}