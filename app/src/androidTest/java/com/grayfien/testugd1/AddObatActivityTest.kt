package com.grayfien.testugd1


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddObatActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AddObatActivity::class.java)

    @Test
    fun addObatActivityTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.btn_add_obat), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
        onView(isRoot()).perform(waitFor(5000))

        val textInputEditText = onView(
            allOf(
                withId(R.id.txt_id_obat),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("3"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btn_add_obat), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
        onView(isRoot()).perform(waitFor(5000))

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.txt_nama_obat),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("TestingObat"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.btn_add_obat), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
        onView(isRoot()).perform(waitFor(5000))

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.txt_jenis_obat),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("testing"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.btn_add_obat), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())
        onView(isRoot()).perform(waitFor(5000))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.txt_deskripsi_obat),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("testingObat"), closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.btn_add_obat), withText("Simpan Data"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())
        onView(isRoot()).perform(waitFor(5000))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun waitFor(delay : Long): ViewAction? {
        return object: ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for" + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}
