package me.test.davidllorca.fantasycensus.ui.citizendetail;

import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;

/**
 * Contract of UI/Presenter.
 */
public interface CitizenDetailContract {

    interface View {

        void showCitizen(CitizenViewModel citizen);

    }

    interface Presenter {

        void loadCitizen();

    }
}
