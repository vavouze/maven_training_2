package fr.lernejo.tester;

import fr.lernejo.tester.api.TestMethod;
import fr.lernejo.tester.internal.TestClassDescription;
import fr.lernejo.tester.internal.TestClassDiscoverer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (String arg: args) {
            TestClassDiscoverer current = new TestClassDiscoverer(arg);
            List<TestClassDescription> TestClasses = current.listTestClasses();
            int launchedTests = 0,failedTests = 0;
            long globalStartTime = System.currentTimeMillis();
            for (TestClassDescription CurrentClass:TestClasses)
            {
                Object testInstance = CurrentClass.getClassInstance().getConstructor().newInstance();
                List<Method> testMethods = CurrentClass.listTestMethods();

                for (Method meth:testMethods)
                {
                    long startTime = System.currentTimeMillis();
                    String status;
                    launchedTests++;
                    try
                    {
                        meth.invoke(testInstance);
                        status = "OK";
                    }
                    catch (Exception e)
                    {
                        status = "KO";
                        failedTests++;
                    }
                    long stopTime = System.currentTimeMillis();
                    System.out.println(CurrentClass.getClassInstance().getName()+"#"+meth.getName()+" "+status+" "+(stopTime - startTime)+"ms");
                }
            }
            long globalStopTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("nblaunchedTest:"+launchedTests+" nbFailedTests:"+failedTests+" GlobalTimer:"+(globalStopTime - globalStartTime)+"ms");
        }
    }
}
