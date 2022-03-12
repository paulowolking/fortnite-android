package com.wolking.fortnite

import android.app.Activity
import android.app.Instrumentation
import android.os.Handler
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wolking.fortnite.presentation.ui.MainActivity

import androidx.test.rule.ActivityTestRule
import com.wolking.fortnite.presentation.ui.NickActivity
import org.junit.After
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
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
    fun whenActicityIsLaunched_checkFirstFragment() {
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
    }

}