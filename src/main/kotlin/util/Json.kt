/*
 * Copyright © 2021 by Xenera Co., Ltd.
 *
 * Code written by Atilika Inc. Contact us at hello@atilika.com.
 */

package util

import kotlinx.serialization.SerializationException

fun <T> handleJsonErrors(block: () -> T): T {
    return try {
        block()
    } catch (e: SerializationException) {
        if (e.message?.endsWith("is required, but it was missing") == true) {
            throw MissingFieldError(e.message)
        }
        if (e.message?.startsWith("An unknown field for") == true) {
            throw UnknownFieldError(e.message)
        }
        throw JsonDataError()
    }
}
