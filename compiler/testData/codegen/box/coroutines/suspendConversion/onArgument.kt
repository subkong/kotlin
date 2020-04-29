// !LANGUAGE: +SuspendConversion
// WITH_RUNTIME
// WITH_COROUTINES
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JVM, NATIVE

import helpers.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

suspend fun runS(fn: suspend (String) -> String) = fn("O")

val lambda: (String) -> String = { it + "K" }

fun box(): String {
    var test = "Failed"
    builder {
        test = runS(lambda)
    }
    return test
}