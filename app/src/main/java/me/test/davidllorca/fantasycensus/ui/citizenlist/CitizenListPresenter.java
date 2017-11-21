package me.test.davidllorca.fantasycensus.ui.citizenlist;

import java.util.List;

import me.test.davidllorca.fantasycensus.domain.GetCitizensInteractor;
import me.test.davidllorca.fantasycensus.domain.GetCitizensInteractorImpl;
import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;
import me.test.davidllorca.fantasycensus.utils.EspressoIdlingResource;
import me.test.davidllorca.fantasycensus.utils.Injection;

/**
 * Presenter's implementation of {@link CitizenListFragment}.
 */
public class CitizenListPresenter implements CitizenListContract.Presenter, GetCitizensInteractor
        .Callback {

    private static final String LOG_TAG = CitizenListPresenter.class.getSimpleName();

    private CitizenListContract.View mView;

    private GetCitizensInteractor mGetCitizenInteractor;

    public CitizenListPresenter(CitizenListContract.View view) {
        this.mView = view;
        this.mGetCitizenInteractor = new GetCitizensInteractorImpl(Injection
                .provideCitizenRepository(), this);
    }

    @Override
    public void loadCitizens() {
        EspressoIdlingResource.increment();
        mView.setLoading(true);
        mGetCitizenInteractor.execute();
    }

    @Override
    public void onSuccess(List<CitizenViewModel> citizens) {
        decrementIdlingResource();
        mView.showCitizens(citizens);
        mView.setLoading(false);
    }

    @Override
    public void onError(String error) {
        decrementIdlingResource();
        mView.showError(error);
        mView.setLoading(false);
    }

    private void decrementIdlingResource() {
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
    }

}
