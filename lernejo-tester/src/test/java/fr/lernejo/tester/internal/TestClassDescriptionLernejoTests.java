package fr.lernejo.tester.internal;

import fr.lernejo.tester.SomeLernejoTests;
import fr.lernejo.tester.api.TestMethod;

import java.lang.reflect.Method;
import java.util.List;

public class TestClassDescriptionLernejoTests
{
    @TestMethod
    public static void main(String[] args) throws Exception {
        TestClassDescription testClass = new TestClassDescription(SomeLernejoTests.class);
        List<Method> testMethods = testClass.listTestMethods();
        if (testMethods.size() != 2 || !testMethods.contains(SomeLernejoTests.class.getMethod("ok")) || !testMethods.contains(SomeLernejoTests.class.getMethod("ko")))
        {
            throw new java.lang.Exception();
        }
    }
}
