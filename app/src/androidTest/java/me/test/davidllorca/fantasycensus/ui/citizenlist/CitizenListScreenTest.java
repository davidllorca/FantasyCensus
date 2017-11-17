package me.test.davidllorca.fantasycensus.ui.citizenlist;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.test.davidllorca.fantasycensus.R;
import me.test.davidllorca.fantasycensus.ui.HomeActivity;
import me.test.davidllorca.fantasycensus.ui.citizendetail.CitizenDetailActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

/**
 * Test fot Citizen list screen.
 */
@RunWith(AndroidJUnit4.class)
public class CitizenListScreenTest {

    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityTestRule = new
            ActivityTestRule<HomeActivity>(HomeActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                }
            };

    @Rule
    public IntentsTestRule<HomeActivity> intentsTestRule =
            new IntentsTestRule<>(HomeActivity.class);


    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests
     * significantly
     * more reliable.
     */
    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(
                mHomeActivityTestRule.getActivity().getCountingIdlingResource());
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(
                mHomeActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void showAllCitizens() {
        onView(ViewMatchers.withId(R.id.rv_fragment_citizen_list)).check(matches(isDisplayed()));

        RecyclerView rv = mHomeActivityTestRule.getActivity().findViewById(R.id
                .rv_fragment_citizen_list);
        assertTrue("Has elements:", rv.getAdapter().getItemCount() > 0);
    }

    @Test
    public void intentToDetailActivityIsLaunched() {
        Intents.init();

        onView(withId(R.id.rv_fragment_citizen_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(CitizenDetailActivity.class.getName()));

        Intents.release();
    }


}
