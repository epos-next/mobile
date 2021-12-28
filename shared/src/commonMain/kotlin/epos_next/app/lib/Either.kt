package epos_next.app.lib

 sealed class Either<out A, out B> {

     inline fun <C> fold(ifLeft: (A) -> C, ifRight: (B) -> C): C = when (this) {
         is Right -> ifRight(value)
         is Left -> ifLeft(value)
     }

     internal abstract val isRight: Boolean
     internal abstract val isLeft: Boolean

     data class Left<out A> constructor(val value: A) : Either<A, Nothing>() {
         override val isLeft = true
         override val isRight = false

         override fun toString(): String = "Either.Left($value)"

         companion object {
             @PublishedApi
             internal val leftUnit: Either<Unit, Nothing> =
                 Left(Unit)
         }
     }

     data class Right<out B> constructor(val value: B) : Either<Nothing, B>() {
         override val isLeft = false
         override val isRight = true

         override fun toString(): String = "Either.Right($value)"

         companion object {
             @PublishedApi
             internal val unit: Either<Nothing, Unit> = Right(Unit)
         }
     }
 }