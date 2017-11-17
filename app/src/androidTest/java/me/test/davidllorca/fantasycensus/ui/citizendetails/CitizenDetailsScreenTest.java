package me.test.davidllorca.fantasycensus.ui.citizendetails;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.test.davidllorca.fantasycensus.R;
import me.test.davidllorca.fantasycensus.data.model.Citizen;
import me.test.davidllorca.fantasycensus.ui.citizendetail.CitizenDetailActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.anything;

/**
 * Test fot Citizen list screen.
 */
@RunWith(AndroidJUnit4.class)
public class CitizenDetailsScreenTest {

    private static final Citizen CITIZEN = new Citizen(0,
            "Tobus Quickwhistle", "http://www" +
            ".publicdomainpictures.net/pictures/10000/nahled/thinking-monkey-11282237747K8xB.jpg",
            306,
            39.065952,
            107.75835,
            "Pick",
            new ArrayList<>(Arrays.asList("Metalworker", "Woodcarver")),
            new ArrayList<>(Arrays.asList("Cogwitz Chillwidget", "Tinadette Chillbuster")));
    @Rule
    public ActivityTestRule<CitizenDetailActivity> mCitizenDetailActivityTestRule = new
            ActivityTestRule<>(CitizenDetailActivity.class, true, false);
    List<String> friends = new ArrayList<>(Arrays.asList("Cogwitz " +
            "Chillwidget", "Tinadette Chillbuster"));

    @Test
    public void loadCitizen() {
        startActivityWithWithStubbedCitizen(CITIZEN);
        CollapsingToolbarLayout toolbarLayout = mCitizenDetailActivityTestRule.getActivity()
                .findViewById(R.id.toolbar_layout_citizen_detail);

        assertEquals("Title:", CITIZEN.getName(), toolbarLayout.getTitle());
        onView(withId(R.id.tv_citizen_detail_age))
                .check(matches(withText(String.valueOf(CITIZEN.getAge()))));
        onView(withId(R.id.tv_citizen_detail_height))
                .check(matches(withText(String.valueOf(CITIZEN.getHeight()))));
        onView(withId(R.id.tv_citizen_detail_weight))
                .check(matches(withText(String.valueOf(CITIZEN.getWeight()))));
        onView(withId(R.id.tv_citizen_detail_hair_color))
                .check(matches(withText(CITIZEN.getHairColor())));
        for (int i = 0; i < CITIZEN.getProfessions().size(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lv_citizen_detail_professions))
                    .atPosition(i)
                    .check(matches(withText(CITIZEN.getProfessions().get(i))));
        }
        for (int i = 0; i < CITIZEN.getProfessions().size(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lv_citizen_detail_friends))
                    .atPosition(i)
                    .check(matches(withText(CITIZEN.getFriends().get(i))));
        }
    }


    private void startActivityWithWithStubbedCitizen(Citizen citizen) {
        Intent startIntent = new Intent();
        startIntent.putExtra(CitizenDetailActivity.CITIZEN_KEY,
                citizen);
        mCitizenDetailActivityTestRule.launchActivity(startIntent);
    }

}
