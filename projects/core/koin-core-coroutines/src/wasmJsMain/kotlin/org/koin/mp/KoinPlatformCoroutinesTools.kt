package org.koin.mp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual object KoinPlatformCoroutinesTools {
    actual fun defaultCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Default

    // Wasm/JS is single-threaded and has no blocking primitive. The JS actual fakes this with
    // GlobalScope.promise(...).getCompleted(), which only works if the block never suspends;
    // this fork does not target wasm, so it fails loudly rather than returning a wrong result.
    actual fun <T> runBlocking(
        context: CoroutineContext,
        block: suspend CoroutineScope.() -> T
    ): T = throw UnsupportedOperationException(
        "KoinPlatformCoroutinesTools.runBlocking is not supported on wasmJs"
    )
}
