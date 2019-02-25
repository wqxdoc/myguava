package com.victor.guava.utils;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JoinerTest {

    private final List<String> stringList = Arrays.asList("Google", "Guava", "Java", "Scala", "Kafka");

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scala", null
    );

    private final Map<String, String> stringMap = of("Hello", "Guava", "Java", "Scala");

    @Test
    public void testJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        assertThat(result, equalTo("Google#Guava#Java#Scala#Kafka"));
    }

    @Test(expected = NullPointerException.class)
    public void testJoinOnNoneSkipNullJoin() {
        String result = Joiner.on("#").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala"));
    }

    @Test
    public void testJoinOnSkipNullJoin() {
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala"));
    }

    @Test
    public void testJoinOnDefaultNullJoin() {
        String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala#DEFAULT"));
    }

    @Test
    public void testJoinOnBuilderJoin() {
        StringBuilder sb = new StringBuilder();
        StringBuilder stringBuilder = Joiner.on("#").appendTo(sb, stringList);

        assertThat(stringBuilder, sameInstance(sb));
        assertThat(stringBuilder.toString(), equalTo("Google#Guava#Java#Scala"));
    }

    @Test
    public void testJoinOnStreamJdkWithSkipNullValuesJoin() {
        String result = stringListWithNullValue.stream()
                .filter(item -> item != null && !item.isEmpty())
                .collect(Collectors.joining("#"));
        assertThat(result, equalTo("Google#Guava#Java#Scala"));
    }

    @Test
    public void testJoinOnStreamJdkWithDefaultNullValuesJoin() {
        String result = stringListWithNullValue.stream()
                .map(item -> item == null || item.isEmpty() ? "DEFAULT" : item)
                .collect(Collectors.joining("#"));
        assertThat(result, equalTo("Google#Guava#Java#Scala#DEFAULT"));
    }

    @Test
    public void testJoinWithMap() {
        String joinMap = Joiner.on("$").withKeyValueSeparator("=").join(stringMap);

        assertThat(joinMap, equalTo("Hello=Guava#Java=Scala"));
    }
}
