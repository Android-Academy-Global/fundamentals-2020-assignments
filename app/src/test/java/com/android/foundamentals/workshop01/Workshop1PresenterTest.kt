package com.android.foundamentals.workshop01

import com.android.fundamentals.domain.login.LoginInteractor
import com.android.fundamentals.workshop01.Workshop1Presenter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class Workshop1PresenterTest {

    private lateinit var testDispatcher: TestCoroutineDispatcher

    @Before
    fun setUp() {
        testDispatcher = TestCoroutineDispatcher()
    }

    @After
    fun cleanUp() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `assert login UserNameError`() = runBlockingTest {
        // Prepare all components for testing
        val (interactor, presenter, view) = createComponents()

        // Call presenter method to attach view
        // TODO 09: Uncomment this code.
        //presenter.attachView(view)

        // Checking the view
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        // Call presenter method login
        // TODO 10: Uncomment this code and run the test.
        //presenter.login(userName = "", password = "")

        // Checking the view
        view.assert(
            expectedLoading = true,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        testDispatcher.advanceTimeBy(LoginInteractor.DELAY_MILLIS)
        // Checking the view after delay
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 1,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )
    }

    @Test
    fun `assert login UserNameError few times`() = runBlockingTest {
        val (interactor, presenter, view) = createComponents()

        // TODO 11: Call presenter attach view.

        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        repeat(10) { index ->
            // TODO 12: Call presenter login with empty name and run test.

            view.assert(
                expectedLoading = true,
                expectedUserNameErrorShownTimes = index,
                expectedPasswordErrorShownTimes = 0,
                expectedSuccessShownTimes = 0
            )

            testDispatcher.advanceTimeBy(LoginInteractor.DELAY_MILLIS)
            view.assert(
                expectedLoading = false,
                expectedUserNameErrorShownTimes = (index + 1),
                expectedPasswordErrorShownTimes = 0,
                expectedSuccessShownTimes = 0
            )
        }
    }

    @Test
    fun `assert login PasswordError`() = runBlockingTest {
        val (interactor, presenter, view) = createComponents()

        // TODO 13: Call presenter attach view.

        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        // TODO 14: Call presenter login with valid name and empty password, and run test.

        view.assert(
            expectedLoading = true,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        testDispatcher.advanceTimeBy(LoginInteractor.DELAY_MILLIS)
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 1,
            expectedSuccessShownTimes = 0
        )
    }

    @Test
    fun `assert login Success`() = runBlockingTest {
        val (interactor, presenter, view) = createComponents()

        // TODO 15: Call presenter attach view.

        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        // TODO 16: Call presenter logic with valid data and run test.

        view.assert(
            expectedLoading = true,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        testDispatcher.advanceTimeBy(LoginInteractor.DELAY_MILLIS)
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 1
        )
    }

    private fun createComponents(): Triple<LoginInteractor, Workshop1Presenter, Workshop1ViewMock> {
        val interactor = LoginInteractor(dispatcher = testDispatcher)
        val presenter = Workshop1Presenter(interactor = interactor, mainDispatcher = testDispatcher)
        val view = Workshop1ViewMock()
        return Triple(interactor, presenter, view)
    }
}

private fun Workshop1ViewMock.assert(
    expectedLoading: Boolean,
    expectedUserNameErrorShownTimes: Int,
    expectedPasswordErrorShownTimes: Int,
    expectedSuccessShownTimes: Int
) {
    Assert.assertEquals(this.isLoading, expectedLoading)
    Assert.assertEquals(this.userNameErrorShownTimes, expectedUserNameErrorShownTimes)
    Assert.assertEquals(this.passwordErrorShownTimes, expectedPasswordErrorShownTimes)
    Assert.assertEquals(this.successShownTimes, expectedSuccessShownTimes)
}