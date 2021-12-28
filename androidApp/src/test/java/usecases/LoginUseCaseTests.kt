package usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.lib.Either
import epos_next.app.models.SetAuthTokens
import epos_next.app.network.Api
import epos_next.app.network.ApiImpl
import epos_next.app.network.responces.auth.AuthenticateResponse
import epos_next.app.usecases.usecases.LoginUseCaseImpl
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

class LoginUseCaseTests: KoinTest {

    val api = mockk<ApiImpl>()
    val authDataStore = mockk<AuthDataStoreImpl>(relaxUnitFun = true)

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<Api> { api }
                single<AuthDataStore> { authDataStore }
            })
    }

    @ExperimentalTime
    @Test
    fun `should perform correct request`(): Unit = runBlocking {
        val email = "test-email@gmail.com"
        val password = "qwerty123"
        val id = 81
        val tokens = SetAuthTokens("access.token.123", "refresh.token.wow22")

        val response = AuthenticateResponse(
            success = true,
            tokens = tokens,
            id = id,
        )

        val loginUseCase = LoginUseCaseImpl()

        coEvery { api.authenticate(email, password) } returns Either.Right(response)

        loginUseCase.execute(email, password).fold(
            { },
            { assertEquals(it, id) }
        )

        coVerify(exactly = 1) { api.authenticate(email, password) }
        coVerify(exactly = 1) { authDataStore.setId(id) }
        coVerify(exactly = 1) { authDataStore.setTokens(tokens) }

        confirmVerified(api)
        confirmVerified(authDataStore)
    }

}