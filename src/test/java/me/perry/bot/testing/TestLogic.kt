package me.perry.bot.testing

import me.perry.bot.JUnittest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

/**
 * @author perry.
 * @since 2/9/2022.
 */
class TestLogic {

    /*
    Might start to use JUnit testing if this project gets big, and if I actually maintain it, so I can make sure everything works fine easily.
    Because it is just more reliable and time-saving to use automated testing than manually testing.
    */
    @Test
    @Disabled // So it doesn't run when we try to build, and it says "There were failing tests. See the report at: *directory*".
    fun test() {
        // Fails because it expects it to be true, but it's false.
        assertTrue(JUnittest().falses())
        // Fails because it expects it to be false, but it's true.
        assertFalse(JUnittest().trues())
        // Fails because it expects it to be null, but it's not null.
        assertNull(JUnittest().strings())
        // Fails because it expects it to not be null, but it's null.
        assertNotNull(JUnittest().nulls())
    }
}