package by.freiding.keepit.domain.model

sealed class RequestResultState<out T> {
    data object None: RequestResultState<Nothing>()
    data object Loading : RequestResultState<Nothing>()
    data class Success<T>(val data: T) : RequestResultState<T>()
    data class Failure(val error: Throwable) : RequestResultState<Nothing>()

    companion object {

        fun none(): None {
            return None
        }

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