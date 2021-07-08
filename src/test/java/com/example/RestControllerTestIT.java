package com.example;

import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.Test;

public class RestControllerTestIT {
    @Test
    public void testHelloIT() {
        Assert.assertTrue(new RestController().hello().equals("Hello World"));
    }
}
