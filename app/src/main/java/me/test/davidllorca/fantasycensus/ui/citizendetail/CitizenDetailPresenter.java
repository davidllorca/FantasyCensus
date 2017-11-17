package me.test.davidllorca.fantasycensus.ui.citizendetail;

import me.test.davidllorca.fantasycensus.data.CitizensRepository;

/**
 * Presenter's implementation of {@link CitizenDetailActivity}.
 */
public class CitizenDetailPresenter implements CitizenDetailContract.Presenter {

    private CitizenDetailContract.View mView;

    private CitizensRepository mRepository;

    public CitizenDetailPresenter(CitizenDetailContract.View view, CitizensRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadCitizen() {

    }
}
