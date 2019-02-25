package com.victor.guava.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        checkNotNull(null);
    }

    @Test
    public void testCheckNullMessage() {
        try {
            checkNotNullWithMessage(null);
        } catch (Exception e) {
            assertThat(e, is(NullPointerException.class));

            assertThat(e.getMessage(), equalTo("The list should not be null"));
        }
    }

    @Test
    public void testCheckArgument() {
        final String result = "A";
        try {
            Preconditions.checkArgument(result.equals("B"));
        } catch (Exception e) {
            assertThat(e, is(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCheckState() {
        try {
            Preconditions.checkState("A".equals("B"));
            fail("should not process here");
        } catch (Exception e) {
            assertThat(e, is(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCheckIndex() {
        List<String> result = ImmutableList.of();
        try {
            Preconditions.checkElementIndex(10, result.size());
        } catch (Exception e) {
            assertThat(e, is(IndexOutOfBoundsException.class));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testNullCheckByJdkObjects() {
        Objects.requireNonNull(null);
    }

    @Test(expected = AssertionError.class)
    public void testAssert() {
        List<String> result = null;
        assert result != null;
    }

    @Test
    public void testAssertWithMessage() {
        List<String> result = null;
        try {
            assert result != null : "The list should not be null.";
        } catch (Error e) {
            assertThat(e, is(AssertionError.class));
            assertThat(e.getMessage(), equalTo("The list should not be null."));
        }
    }

    private void checkNotNullWithMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null");
    }
}
