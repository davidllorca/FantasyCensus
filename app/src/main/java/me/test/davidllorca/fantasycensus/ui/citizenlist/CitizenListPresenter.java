package me.test.davidllorca.fantasycensus.ui.citizenlist;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.fantasycensus.data.CitizensRepository;
import me.test.davidllorca.fantasycensus.utils.EspressoIdlingResource;

/**
 * Presenter's implementation of {@link CitizenListFragment}.
 */
public class CitizenListPresenter implements CitizenListContract.Presenter {

    private static final String LOG_TAG = CitizenListPresenter.class.getSimpleName();

    private CitizenListContract.View mView;

    private CitizensRepository mRepository;

    public CitizenListPresenter(CitizenListContract.View view, CitizensRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadCitizens() {
        EspressoIdlingResource.increment();
        mView.setLoading(true);
        mRepository.getCitizens()
                .doFinally(() -> {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(citizens -> {
                            mView.showCitizens(citizens);
                            mView.setLoading(false);
                        }
                        , throwable -> Log.e(LOG_TAG, throwable.getMessage()));
    }
}
