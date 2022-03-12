package com.wolking.fortnite

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.wolking.fortnite.presentation.ui.NickActivity
import com.wolking.fortnite.utils.CountingIdlingResourceSingleton
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import java.lang.Exception


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NickNameInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<NickActivity> =
        ActivityScenarioRule(NickActivity::class.java)


    @Before
    @Throws(Exception::class)
    fun setUp() {
//        Intents.init()
        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
//        Intents.release()
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun whenActivityIsLaunched_shouldDisplayInititalState() {
        onView(withId(R.id.et_nick)).check(matches(isDisplayed()))
        onView(withId(R.id.bt_search)).check(matches(isDisplayed()))
    }

    @Test
    fun whenInsertNickNameValid_sendNextActivity() {
        onView(withId(R.id.et_nick)).perform(replaceText("wolking_")).perform(closeSoftKeyboard())
        onView(withId(R.id.bt_search)).perform(click())
        // Use CountingIdlingResource in NickActivity
        onView(withId(R.id.bt_edit)).check(matches(isDisplayed()))
    }

//    @Test
//    fun whenInsertNickNameValid_sendNextActivity_simulated() {
//        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
//        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())
//        intended(hasComponent(MainActivity::class.java.name))
//        ActivityScenario.launch<MainActivity>(intent)
//        intending(toPackage(MainActivity::class.java.name)).respondWith(result)
//    }

}