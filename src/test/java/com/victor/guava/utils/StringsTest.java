package com.victor.guava.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StringsTest {

    @Test
    public void testStringsMethod() {
        assertThat(Strings.commonPrefix("Helx", "Hello"), equalTo("H"));

        assertThat(Strings.commonSuffix("hello", "echo"), equalTo("o"));

        assertThat(Strings.isNullOrEmpty(null), equalTo(true));

        assertThat(Strings.nullToEmpty(null), equalTo(""));

        assertThat(Strings.padEnd("Hello", 3, 'H'), equalTo("hello"));
        assertThat(Strings.padEnd("Hello", 7, 'H'), equalTo("helloHH"));
        assertThat(Strings.padStart("Hello", 7, 'H'), equalTo("HHhello"));

        assertThat(Strings.repeat("AX", 3), equalTo("AXAXAX"));

    }

    @Test
    public void testCharset() {
        Charset charset = Charset.forName("UTF-8");
        assertThat(Charsets.UTF_8, equalTo(charset));
    }

    @Test
    public void testCharMatcher() {
        assertThat(CharMatcher.breakingWhitespace().countIn("Alex is good 00  "), equalTo(5));
        assertThat(CharMatcher.javaDigit().matches('A'), equalTo(false));

        assertThat(CharMatcher.is('A').countIn("Alex is good"), equalTo(1));

        assertThat(CharMatcher.breakingWhitespace()
                .collapseFrom("Alex  is good 00  ", '*'), equalTo("Alex*is*good*00*"));

        assertThat(CharMatcher.javaDigit().or(CharMatcher.breakingWhitespace()).removeFrom("hello 1234 word "), equalTo("helloword"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.breakingWhitespace()).retainFrom("hello 1234 word "), equalTo(" 1234  "));





    }
}
