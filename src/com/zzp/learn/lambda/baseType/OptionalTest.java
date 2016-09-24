package com.zzp.learn.lambda.baseType;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Desc
 * Created by zzp
 * on 2016/8/12.21:28
 */
public class OptionalTest {

    @Test
    public void testOf() {
        Optional<String> optional = Optional.of("a");
        Assert.assertEquals("a", optional.get());
    }

    @Test
    public void testHasVals() {
        Optional emptyOptional = Optional.empty();
        Optional nullOptional = Optional.ofNullable(null);
        Optional<String> optional = Optional.of("a");

        nullOptional.get().equals("a");
        assertFalse(nullOptional.isPresent());
        assertFalse(emptyOptional.isPresent());
        assertTrue(optional.isPresent());
    }

    @Test
    public void testOrElse() {
        Optional emptyOptional = Optional.empty();

        assertEquals("b", emptyOptional.orElse("b"));
        assertEquals("c", emptyOptional.orElseGet(() -> "c"));

    }

    @Test
    public void orElseOrElseGet() {
        String a = "a", b = "b";
        Optional<String> name = Optional.of("Sanaulla");

        System.out.println(name.orElse(a + b));
        System.out.println(name.orElseGet(() -> a + b));
    }
}
