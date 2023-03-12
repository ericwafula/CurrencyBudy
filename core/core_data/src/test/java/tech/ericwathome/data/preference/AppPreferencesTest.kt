package tech.ericwathome.data.preference

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppPreferencesTest {

    @Test
    fun `ensure default onBoarding status value is false`() = runBlocking {
        val context: Context = ApplicationProvider.getApplicationContext()

        val appPreferencesImpl = AppPreferencesImpl(context)

        appPreferencesImpl.getOnboardingStatus().test {
            val onBoardingStatus = awaitItem()
            assertThat(onBoardingStatus).isFalse()
        }
    }

}