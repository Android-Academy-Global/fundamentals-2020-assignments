package com.android.foundamentals.workshop01.solution

import com.android.fundamentals.domain.login.LoginInteractor
import com.android.fundamentals.workshop01.solution.Workshop1SolutionPresenter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class Workshop1SolutionPresenterTest {

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
        val (interactor, presenter, view) = createComponents()

        presenter.attachView(view)
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        presenter.login(userName = "", password = "")
        view.assert(
            expectedLoading = true,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        testDispatcher.advanceTimeBy(LoginInteractor.DELAY_MILLIS)
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

        presenter.attachView(view)
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        repeat(10) { index ->
            presenter.login(userName = "", password = "")
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

        presenter.attachView(view)
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        presenter.login(userName = "amazing user name", password = "")
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

        presenter.attachView(view)
        view.assert(
            expectedLoading = false,
            expectedUserNameErrorShownTimes = 0,
            expectedPasswordErrorShownTimes = 0,
            expectedSuccessShownTimes = 0
        )

        presenter.login(userName = "amazing user name", password = "amazing password")
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

    private fun createComponents(): Triple<LoginInteractor, Workshop1SolutionPresenter, Workshop1SolutionViewMock> {
        val interactor = LoginInteractor(dispatcher = testDispatcher)
        val presenter =
            Workshop1SolutionPresenter(interactor = interactor, mainDispatcher = testDispatcher)
        val view = Workshop1SolutionViewMock()
        return Triple(interactor, presenter, view)
    }
}

private fun Workshop1SolutionViewMock.assert(
    expectedLoading: Boolean,
    expectedUserNameErrorShownTimes: Int,
    expectedPasswordErrorShownTimes: Int,
    expectedSuccessShownTimes: Int
) {
    assertEquals(this.isLoading, expectedLoading)
    assertEquals(this.userNameErrorShownTimes, expectedUserNameErrorShownTimes)
    assertEquals(this.passwordErrorShownTimes, expectedPasswordErrorShownTimes)
    assertEquals(this.successShownTimes, expectedSuccessShownTimes)
}