/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import org.junit.jupiter.api.Test;
import probability.Chance;

import static org.junit.jupiter.api.Assertions.*;

// Ensures Chance operates correctly
class ChanceTest {

    @Test void equality() {
        assertEquals(new Chance(0.75), new Chance(0.75));
    }
}
