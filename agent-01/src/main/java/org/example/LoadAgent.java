package org.example;

import java.lang.instrument.Instrumentation;

public class LoadAgent {
    public static void agentmain(String args, Instrumentation inst) {
        System.err.println("agent startup , args is " + args);

        Class<?>[] classes = inst.getAllLoadedClasses();
        for (Class<?> cls : classes) {
            System.out.println(cls.getName());
        }
    }
}
