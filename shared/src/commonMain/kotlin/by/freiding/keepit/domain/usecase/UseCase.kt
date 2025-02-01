package by.freiding.keepit.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class UseCase<P: Any, R: Any?> {

    abstract fun invoke(params: P): Flow<R>
}