package fr.lernejo.tester.internal;

import fr.lernejo.tester.SomeLernejoTests;

public class TestClassDescriptionLernejoTests
{
    public static void main(String[] args) {
        TestClassDescription testClass = new TestClassDescription(SomeLernejoTests.class);
        System.out.println(testClass.listTestMethods());
    }
}
