package tech.ericwathome.data.preference

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class AppPreferencesTest {
    private lateinit var appPreferencesImpl: AppPreferencesImpl

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appPreferencesImpl = AppPreferencesImpl(context)
    }

    @Test
    fun `ensure default onBoarding status value is false`() = runBlocking {
        appPreferencesImpl.getOnboardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isFalse()
        }
    }

    @Test
    fun `check if onBoarding status gets updated to true`() = runBlocking {
        appPreferencesImpl.updateOnboardingStatus(true)
        appPreferencesImpl.getOnboardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isTrue()
        }
    }

    @Test
    fun `check if the appPreferences interface's getOnBoarding status is invoked correctly, returns true`() = runBlocking {
        val appPreferences = mock(AppPreferences::class.java)
        Mockito.`when`(appPreferences.getOnboardingStatus())
            .thenReturn(flowOf(true))
        appPreferences.getOnboardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isTrue()

            // ensure there are no more events
            expectNoEvents()
        }
    }

    @Test
    fun `check if the appPreferences interface's getOnBoarding status is invoked correctly, returns false`() = runBlocking {
        val appPreferences = mock(AppPreferences::class.java)
        Mockito.`when`(appPreferences.getOnboardingStatus())
            .thenReturn(flowOf(false))
        appPreferences.getOnboardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isFalse()

            // ensure there are no more events
            expectNoEvents()
        }
    }

}