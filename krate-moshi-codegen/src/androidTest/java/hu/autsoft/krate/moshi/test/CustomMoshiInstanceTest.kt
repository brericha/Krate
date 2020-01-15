package hu.autsoft.krate.moshi.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import hu.autsoft.krate.Krate
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.moshi.defaultMoshi
import hu.autsoft.krate.moshi.moshi
import hu.autsoft.krate.moshi.moshiInstance
import hu.autsoft.krate.moshi.util.targetContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CustomMoshiInstanceTest {

    private lateinit var krate: Krate

    @Before
    fun setup() {
        krate = object : SimpleKrate(targetContext) {}
    }

    @Test
    fun testDefaultMoshiInstanceInternal() {
        assertEquals(defaultMoshi, krate.moshiInstance)
    }

    @Test
    fun testCustomMoshiInstanceInternal() {
        val customMoshi = createCustomTestMoshi()

        krate.moshi = customMoshi

        assertEquals(customMoshi, krate.moshiInstance)
    }

    @Test
    fun testMultipleMoshiInstancesInternal() {
        val customMoshi = createCustomTestMoshi()
        krate.moshi = customMoshi
        assertEquals(customMoshi, krate.moshiInstance)

        val customMoshi2 = createCustomTestMoshi()
        krate.moshi = customMoshi2
        assertEquals(customMoshi2, krate.moshiInstance)

        krate.moshi = null
        assertEquals(defaultMoshi, krate.moshiInstance)
    }

    @Test
    fun testDefaultMoshiInstancePublic() {
        assertEquals(null, krate.moshi)
    }

    @Test
    fun testCustomMoshiInstancePublic() {
        val customMoshi = createCustomTestMoshi()

        krate.moshi = customMoshi

        assertEquals(customMoshi, krate.moshi)
    }

    @Test
    fun testMultipleMoshiInstancesPublic() {
        val customMoshi = createCustomTestMoshi()
        krate.moshi = customMoshi
        assertEquals(customMoshi, krate.moshi)

        val customMoshi2 = createCustomTestMoshi()
        krate.moshi = customMoshi2
        assertEquals(customMoshi2, krate.moshi)

        krate.moshi = null
        assertEquals(null, krate.moshi)
    }

    private fun createCustomTestMoshi(): Moshi {
        return Moshi.Builder()
                .build()
    }

}
