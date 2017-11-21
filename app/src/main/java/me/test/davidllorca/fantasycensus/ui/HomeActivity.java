package me.test.davidllorca.fantasycensus.ui;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.test.davidllorca.fantasycensus.R;
import me.test.davidllorca.fantasycensus.ui.citizendetail.CitizenDetailActivity;
import me.test.davidllorca.fantasycensus.ui.citizenlist.CitizenListFragment;
import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;
import me.test.davidllorca.fantasycensus.utils.EspressoIdlingResource;

public class HomeActivity extends AppCompatActivity implements CitizenListFragment
        .OnCitizenListFragmentListener {

    /* VIEWS */
    @BindView(R.id.toolbar_home)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onCitizenClicked(CitizenViewModel citizen) {
        startActivity(CitizenDetailActivity.getIntentByCitizen(this, citizen));
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
