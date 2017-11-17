package me.test.davidllorca.fantasycensus.ui.citizendetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.test.davidllorca.fantasycensus.Injection;
import me.test.davidllorca.fantasycensus.R;
import me.test.davidllorca.fantasycensus.data.model.Citizen;

public class CitizenDetailActivity extends AppCompatActivity implements CitizenDetailContract
        .View {

    public static final String CITIZEN_KEY = "citizen";

    /* VIEWS */
    @BindView(R.id.toolbar_layout_citizen_detail)
    CollapsingToolbarLayout mAppBarLayout;
    @BindView(R.id.toolbar_citizen_detail)
    Toolbar mToolbar;
    @BindView(R.id.img_citizen_detail_app_bar)
    ImageView mThumbnailImageView;
    @BindView(R.id.tv_citizen_detail_age)
    TextView mAgeTextView;
    @BindView(R.id.tv_citizen_detail_height)
    TextView mHeightTextView;
    @BindView(R.id.tv_citizen_detail_weight)
    TextView mWeightTextView;
    @BindView(R.id.tv_citizen_detail_hair_color)
    TextView mHairColorTextView;
    @BindView(R.id.lv_citizen_detail_professions)
    ListView mProfessionsListView;
    @BindView(R.id.lv_citizen_detail_friends)
    ListView mFriendsListView;

    /**
     * Implementation of {@link CitizenDetailContract.Presenter}
     */
    private CitizenDetailContract.Presenter mPresenter;


    /**
     * Get Intent to {@link CitizenDetailActivity} with {@link Citizen} in its arguments.
     *
     * @param context
     * @param citizen Citizen to show in detail.
     * @return Intent
     */
    public static Intent getIntentByCitizen(Context context, Citizen citizen) {
        Bundle extras = new Bundle();
        extras.putParcelable(CitizenDetailActivity.CITIZEN_KEY, citizen);
        Intent intent = new Intent(context, CitizenDetailActivity.class);
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_detail);
        ButterKnife.bind(this);

        // Init toolbar
        setSupportActionBar(mToolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Init presenter
        mPresenter = new CitizenDetailPresenter(this, Injection.provideCitizenRepository());

        // Get item to show.
        Citizen targetCitizen = getIntent().getExtras().getParcelable(CitizenDetailActivity
                .CITIZEN_KEY);
        if (targetCitizen != null) {
            showCitizen(targetCitizen);
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCitizen(Citizen citizen) {
        Picasso.with(this)
                .load(citizen.getThumbnail())
                .into(mThumbnailImageView);
        mAppBarLayout.setTitle(citizen.getName());
        mAgeTextView.setText(String.valueOf(citizen.getAge()));
        mHeightTextView.setText(String.valueOf(citizen.getHeight()));
        mWeightTextView.setText(String.valueOf(citizen.getWeight()));
        mHairColorTextView.setText(citizen.getHairColor());
        mProfessionsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, citizen.getProfessions()));
        mFriendsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                citizen.getFriends()));
    }

}
