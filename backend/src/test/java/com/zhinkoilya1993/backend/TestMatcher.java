package com.zhinkoilya1993.backend;

import org.assertj.core.api.RecursiveComparisonAssert;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static com.zhinkoilya1993.backend.util.TestUtil.readListFromJsonMvcResult;
import static org.assertj.core.api.Assertions.assertThat;

public class TestMatcher<T> {

    private final String[] fieldsToIgnore;
    private final boolean usingEquals;
    private final Class<T> clazz;

    private TestMatcher(boolean usingEquals, Class<T> clazz, String... fieldsToIgnore) {
        this.fieldsToIgnore = fieldsToIgnore;
        this.usingEquals = usingEquals;
        this.clazz = clazz;
    }

    public static <T> TestMatcher<T> fieldsComparorOf(Class<T> clazz) {
        return new TestMatcher<>(true, clazz);
    }

    public static <T> TestMatcher<T> fieldsComparorOf(Class<T> clazz, String... fieldsToIgnore) {
        return new TestMatcher<>(false, clazz, fieldsToIgnore);
    }

    public void assertMatch(T actual, T expected) {
        RecursiveComparisonAssert<?> recursiveComparison = assertThat(actual)
                .usingRecursiveComparison();
        if (!usingEquals) {
            recursiveComparison = recursiveComparison.ignoringFields(fieldsToIgnore);
        }
        recursiveComparison.isEqualTo(expected);
    }

    @SafeVarargs
    public final void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        RecursiveComparisonAssert<?> recursiveComparison = assertThat(actual)
                .usingRecursiveComparison();
        if (!usingEquals) {
            recursiveComparison = recursiveComparison.ignoringFields(fieldsToIgnore);
        }
        recursiveComparison.isEqualTo(expected);
    }

    public ResultMatcher contentJson(T expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, clazz), expected);
    }

    public ResultMatcher contentJson(T... expected) {
        return contentJson(List.of(expected));
    }

    public ResultMatcher contentJson(Iterable<T> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, clazz), expected);
    }

}
