package me.test.davidllorca.fantasycensus.ui.citizendetail;

import me.test.davidllorca.fantasycensus.data.model.Citizen;

/**
 * Contract of UI/Presenter.
 */
public interface CitizenDetailContract {

    interface View {

        void showCitizen(Citizen citizen);

    }

    interface Presenter {

        void loadCitizen();

    }
}
