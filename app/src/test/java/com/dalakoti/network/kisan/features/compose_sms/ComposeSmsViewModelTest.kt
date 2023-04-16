package com.dalakoti.network.kisan.features.compose_sms

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.os.Looper
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.core.data.rep_impl.TwilioRepositoryImpl
import com.dalakoti.network.core.domain.usecases.SendSmsUseCase
import com.dalakoti.network.kisan.core.repository.FakeTwilioService
import com.dalakoti.network.kisan.utils.FakeDbDao
import com.dalakoti.network.kisan.utils.MainCoroutineRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.*

@ExperimentalCoroutinesApi
class ComposeSmsViewModelTest {
    private lateinit var viewModel: ComposeSmsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mockkStatic(Looper::class)

        val looper = mockk<Looper> {
            every { thread } returns Thread.currentThread()
        }

        every { Looper.getMainLooper() } returns looper

        // inject dependency manually
        viewModel = ComposeSmsViewModel(
            sendSmsUseCase = SendSmsUseCase(
                twilioRepository = TwilioRepositoryImpl(
                    service = FakeTwilioService()
                ),
                jsonConvertor = Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            ),
            dao = FakeDbDao(),
        )
    }

    @Test
    fun `successfully send sms`() = runTest {
        Assert.assertEquals(
            false,
            viewModel.uiState.value.isLoading
        )
        Assert.assertEquals(
            0,
            FakeDbDao.counter,
        )

        viewModel.sendSms(
            Contact(
                id = 4,
                name = "Kisan Network",
                designation = "Startup",
                address = "India",
                avatar = "https://i.imgur.com/uTSXYZa.png",
                phoneNumber = "+919810153260"
            ),
            to = "+919810153260",
            body = "I am testing it",
        )

        // assert that assertion was done
        Assert.assertEquals(
            1,
            FakeDbDao.counter,
        )

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

}