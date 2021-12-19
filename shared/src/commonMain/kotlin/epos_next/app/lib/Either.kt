package epos_next.app.lib

public sealed class Either<out A, out B> {

    public inline fun <C> fold(ifLeft: (A) -> C, ifRight: (B) -> C): C = when (this) {
        is Either.Right -> ifRight(value)
        is Either.Left -> ifLeft(value)
    }

    internal abstract val isRight: Boolean
    internal abstract val isLeft: Boolean

    public data class Left<out A> constructor(val value: A) : Either<A, Nothing>() {
        override val isLeft = true
        override val isRight = false

        override fun toString(): String = "Either.Left($value)"

        public companion object {
            @PublishedApi
            internal val leftUnit: Either<Unit, Nothing> =
                Left(Unit)
        }
    }

    public data class Right<out B> constructor(val value: B) : Either<Nothing, B>() {
        override val isLeft = false
        override val isRight = true

        override fun toString(): String = "Either.Right($value)"

        public companion object {
            @PublishedApi
            internal val unit: Either<Nothing, Unit> = Right(Unit)
        }
    }
}