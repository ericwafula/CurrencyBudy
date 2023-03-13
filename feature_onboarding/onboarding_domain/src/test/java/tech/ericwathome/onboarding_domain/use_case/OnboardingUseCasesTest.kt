package tech.ericwathome.onboarding_domain.use_case

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import tech.ericwathome.data.preference.AppPreferences

@RunWith(MockitoJUnitRunner::class)
class OnboardingUseCasesTest {

    @Mock
    private lateinit var appPreferences: AppPreferences

    @Test
    fun `ensure the getOnBoardingStatus() returns the correct values, returns true`() = runBlocking {
        var getOnboardingStatus = mock(GetOnboardingStatus::class.java)
        Mockito.`when`(getOnboardingStatus.invoke())
            .thenReturn(flowOf(true))
        getOnboardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isTrue()
            expectNoEvents()
        }
    }

    @Test
    fun `ensure the getOnBoardingStatus() returns the correct values, returns false`() = runBlocking {
        var getOnBoardingStatus = mock(GetOnboardingStatus::class.java)
        Mockito.`when`(getOnBoardingStatus.invoke())
            .thenReturn(flowOf(false))
        getOnBoardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isFalse()
            expectNoEvents()
        }
    }

    @Test
    fun `ensure getOnBoardingStatus() returns the correct value, returns true when appPreferences has a true value`() = runBlocking{
        var getOnBoardingStatus = GetOnboardingStatus(appPreferences)
        Mockito.`when`(appPreferences.getOnboardingStatus())
            .thenReturn(flowOf(true))
        appPreferences.getOnboardingStatus()
        getOnBoardingStatus().test {
        val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isTrue()
            expectNoEvents()
        }
    }

    @Test
    fun `ensure getOnBoardingStatus() returns the correct value, returns false when appPreferences has a false value`() = runBlocking{
        var getOnBoardingStatus = GetOnboardingStatus(appPreferences)
        Mockito.`when`(appPreferences.getOnboardingStatus())
            .thenReturn(flowOf(false))
        appPreferences.getOnboardingStatus()
        getOnBoardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isFalse()
            expectNoEvents()
        }
    }



}