package me.test.davidllorca.fantasycensus.ui;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import me.test.davidllorca.fantasycensus.RxImmediateSchedulerRule;
import me.test.davidllorca.fantasycensus.data.CitizensRepository;
import me.test.davidllorca.fantasycensus.data.model.Citizen;
import me.test.davidllorca.fantasycensus.ui.citizenlist.CitizenListContract;
import me.test.davidllorca.fantasycensus.ui.citizenlist.CitizenListPresenter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link CitizenListPresenter}
 */
public class CitizenListPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private static List<Citizen> RESPONSE;

    @Mock
    Citizen citizen;

    @Mock
    private CitizensRepository mCitizensRepository;

    @Mock
    private CitizenListContract.View mCitizenListView;


    private CitizenListPresenter mCitizenListPresenter;

    @Before
    public void setupCitizenListPresenter() {
        MockitoAnnotations.initMocks(this);

        mCitizenListPresenter = new CitizenListPresenter(mCitizenListView, mCitizensRepository);

        RESPONSE = new ArrayList<>();
        RESPONSE.add(citizen);
    }

    @Test
    public void loadCitizensFromRepositoryAndLoadIntoView() {
        when(mCitizensRepository.getCitizens()).thenReturn(Single.just(RESPONSE));

        mCitizenListPresenter.loadCitizens();

        verify(mCitizenListView).setLoading(true);
        verify(mCitizenListView).setLoading(false);
        verify(mCitizenListView).showCitizens(RESPONSE);
    }

}
