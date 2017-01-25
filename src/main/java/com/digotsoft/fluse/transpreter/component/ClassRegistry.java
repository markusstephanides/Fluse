package com.digotsoft.fluse.transpreter.component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Markus
 */
public class ClassRegistry {

    private static Set<FluseClass> classes;
    private static Set<String> registeredClassNames;

    static {
        ClassRegistry.classes = new LinkedHashSet<>();
        ClassRegistry.registeredClassNames = new LinkedHashSet<>();
    }

    public static FluseClass findClass(String className){
        if(ClassRegistry.registeredClassNames.contains(className)){
            for (FluseClass aClass : ClassRegistry.classes) {
                if(aClass.getName().equals(className)) return aClass;
            }
        }

        return new FutureClass(className);
    }

    public static ClassRegistrationResult register(FluseClass fluseClass){
        String className = fluseClass.getName();

        if(ClassRegistry.registeredClassNames.contains(className)){
            // A class with the same name has already been registered
            return ClassRegistrationResult.FAILED_NAME;
        }

        ClassRegistry.registeredClassNames.add(className);
        ClassRegistry.classes.add(fluseClass);

        return ClassRegistrationResult.SUCCESS;
    }

    private enum ClassRegistrationResult {
        SUCCESS, FAILED_NAME
    }

}
