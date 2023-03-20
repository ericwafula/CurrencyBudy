package tech.ericwathome.data.preference

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import kotlin.properties.Delegates

@RunWith(AndroidJUnit4::class)
class AppPreferencesTest {
    private lateinit var appPreferencesImpl: AppPreferencesImpl
    private lateinit var context: Context
    private var initialOnBoardingStatus by Delegates.notNull<Boolean>()


    @Before
    fun setup() = runBlocking {
        context = ApplicationProvider.getApplicationContext()
        appPreferencesImpl = AppPreferencesImpl(context)
        /**
         * setup the initial onBoardingStatus value based on the initial preference value
         */
        initialOnBoardingStatus = appPreferencesImpl.getOnboardingStatus().first()
    }

    @After
    fun tearDown() = runBlocking {
        /**
         * resets the onBoarding status to default after each test
         */
        appPreferencesImpl.updateOnboardingStatus(initialOnBoardingStatus)
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
    fun `check if the appPreferences interface's getOnBoarding status is invoked correctly, returns true`() =
        runBlocking {
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
    fun `check if the appPreferences interface's getOnBoarding status is invoked correctly, returns false`() =
        runBlocking {
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