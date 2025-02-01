package by.freiding.keepit.domain.usecase

sealed class UseCaseResult<out T> {
    data object Loading : UseCaseResult<Nothing>()
    data class Success<T>(val data: T) : UseCaseResult<T>()
    data class Failure(val error: Throwable) : UseCaseResult<Nothing>()

    companion object {
        fun loading(): Loading {
            return Loading
        }

        fun <T> success(result: T): Success<T> {
            return Success(result)
        }

        fun failure(error: Throwable): Failure {
            return Failure(error)
        }
    }
}