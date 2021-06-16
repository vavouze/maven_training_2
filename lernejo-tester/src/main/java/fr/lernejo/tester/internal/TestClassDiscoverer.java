package fr.lernejo.tester.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.lernejo.tester.api.TestMethod;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;


public class TestClassDiscoverer
{
    private final String packageName;
    public TestClassDiscoverer(String packageName)
    {
        this.packageName = packageName;
    }

    public List<TestClassDescription> listTestClasses()
    {
        Reflections reflections = new Reflections(this.packageName, new SubTypesScanner(false));
        Set<Class<?>> allTypes = reflections.getSubTypesOf(Object.class);
        List<TestClassDescription> descriptionList = new ArrayList<>();
        for (Class<?> type: allTypes)
        {
            TestClassDescription current = new TestClassDescription(type);
            List<Method> to_return = new ArrayList<Method>();
            for (Method courant : current.getClassInstance().getDeclaredMethods())
            {
                if (Modifier.isPublic(courant.getModifiers()) && courant.getReturnType().equals(Void.TYPE) && courant.isAnnotationPresent(TestMethod.class))
                {
                    to_return.add(courant);
                }
            }
            if (to_return.size() > 0)
            {
                descriptionList.add(current);
            }
        }
        return descriptionList;
    }
}
