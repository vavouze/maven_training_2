package fr.lernejo.tester.internal;


import fr.lernejo.tester.api.TestMethod;

import java.util.Arrays;
import java.util.List;

public class TestClassDiscovererLernejoTests
{
    @TestMethod
    public static void main(String[] args) throws Exception {
        List<TestClassDescription> testClass =  new TestClassDiscoverer("fr.lernejo.tester").listTestClasses();
        boolean Tester = true;
        String[] classNames = {"fr.lernejo.tester.internal.TestClassDescriptionLernejoTests", "fr.lernejo.tester.SomeLernejoTests", "fr.lernejo.tester.internal.TestClassDiscovererLernejoTests"};
        for (TestClassDescription current:testClass)
        {
            if (!Arrays.asList(classNames).contains(current.getClassInstance().getName()))
            {
                Tester = false;
            }
        }
        if (testClass.size() != 3 || !Tester)
        {
            throw new java.lang.Exception();
        }
    }
}
