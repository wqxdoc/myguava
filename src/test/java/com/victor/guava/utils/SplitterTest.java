package com.victor.guava.utils;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class SplitterTest {

    @Test
    public void testSplitOnSplit() {
        List<String> result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | guava||");

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo("hello"));
        assertThat(result.size(), equalTo("guava"));
    }

    @Test
    public void testSplitFixLength() {
        List<String> result = Splitter.fixedLength(3).splitToList("aaabbbcccddd");

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(4));
        assertThat(result.get(0), equalTo("aaa"));
        assertThat(result.get(3), equalTo("ddd"));
    }

    @Test
    public void testSplitOnLimit() {
        List<String> result = Splitter.on("|").limit(3).splitToList("hello|new|skill|guava");

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(2), equalTo("skill|guava"));
    }

    @Test
    public void testSplitToMap() {
        Map<String, String> resultMap = Splitter.on(Pattern.compile("\\|"))
                .trimResults()
                .omitEmptyStrings()
                .withKeyValueSeparator("=")
                .split("hello=first|two=guava");

        assertThat(resultMap.get("hello"), equalTo("first"));
    }
}
