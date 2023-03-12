package tech.ericwathome.data.preference

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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

}