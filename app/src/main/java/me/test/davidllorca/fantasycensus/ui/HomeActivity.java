package me.test.davidllorca.fantasycensus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.test.davidllorca.fantasycensus.R;
import me.test.davidllorca.fantasycensus.data.model.Citizen;
import me.test.davidllorca.fantasycensus.ui.citizendetail.CitizenDetailActivity;
import me.test.davidllorca.fantasycensus.ui.citizenlist.CitizenListFragment;

public class HomeActivity extends AppCompatActivity implements CitizenListFragment
        .OnCitizenListFragmentListener {

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
    public void onCitizenClicked(Citizen citizen) {
        startActivity(new Intent(this, CitizenDetailActivity.class));
    }

}
