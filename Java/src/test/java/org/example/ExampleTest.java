package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleTest {

    @BeforeEach
    void setUp() {
        // You may or may not need to initialize something before each test.
    }

    @AfterEach
    void tearDown() {
        // You may or may not need to deinitialize something after each test.
    }

    @Test
    void succeeds() {
        assertThat(42).isLessThan(1337);
        assertThat(new ArrayList<String>()).isEmpty();
    }

    @Test
    void fails() {
        assertThat("A").isEqualTo("B");
        assertThat(new Object()).isNull();
    }
}
