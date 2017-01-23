package com.digotsoft.fluse.transpreter.function;

import com.digotsoft.fluse.transpreter.Type;
import com.digotsoft.fluse.transpreter.clazz.FluseClass;

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

}
