package me.test.davidllorca.fantasycensus.data;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.test.davidllorca.fantasycensus.data.remote.CitizensRemoteDataSource;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of remote.
 */
public class CitizensRepositoryTest {

    private CitizensRepository mCitizensRepository;

    @Mock
    private CitizensRemoteDataSource mCitizensRemoteDataSource;


    @Before
    public void setupTasksRepository() {
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mCitizensRepository = CitizensRepository.getInstance(
                mCitizensRemoteDataSource);
    }

    @After
    public void destroyRepositoryInstance() {
        CitizensRepository.destroyInstance();
    }

    @Test
    public void getCitizens() {
        // Operation to test
        mCitizensRepository.getCitizens();

        verify(mCitizensRemoteDataSource).getCitizens();
    }
}
