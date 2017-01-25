package com.digotsoft.fluse.transpreter.component;

import com.digotsoft.fluse.transpreter.Type;

import java.util.Map;

/**
 * @author Digot
 * @version 1.0
 */
public class FluseFunction {

    private FluseClass parentClass;
    private Type type;
    private FluseClass returnType;
    private Map<String, FluseClass> parameters;
    private String name;
    private AccessLevel accessLevel;

}
