package com.wolking.fortnite.utils

import androidx.test.platform.app.InstrumentationRegistry

class MockResponseFileReader(path: String) {
    val content: String

    init {
        val reader =
            InstrumentationRegistry.getInstrumentation().context.assets.open(path)
                .reader()
        content = reader.readText()
        reader.close()
    }
}