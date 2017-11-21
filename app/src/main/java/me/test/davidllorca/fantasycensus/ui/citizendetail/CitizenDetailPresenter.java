package me.test.davidllorca.fantasycensus.ui.citizendetail;


/**
 * Presenter's implementation of {@link CitizenDetailActivity}.
 */
public class CitizenDetailPresenter implements CitizenDetailContract.Presenter {

    private CitizenDetailContract.View mView;

    public CitizenDetailPresenter(CitizenDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadCitizen() {
        // TODO Future features.
    }
}
