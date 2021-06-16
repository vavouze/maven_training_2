package fr.lernejo.tester.internal;


import fr.lernejo.tester.api.TestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestClassDescription {

    private final Class<?> classInstance;
    public TestClassDescription(Class<?> mystere)
    {
        this.classInstance = mystere;
    }

    public Class<?> getClassInstance() {
        return classInstance;
    }

    public List<Method> listTestMethods()
    {
        List<Method> to_return = new ArrayList<Method>();
        for (Method current : classInstance.getDeclaredMethods())
        {
            if (Modifier.isPublic(current.getModifiers()) && current.getReturnType().equals(Void.TYPE) && current.getParameterTypes().length == 0 && current.isAnnotationPresent(TestMethod.class))
            {
                to_return.add(current);
            }
        }
        return to_return;
    }
}
