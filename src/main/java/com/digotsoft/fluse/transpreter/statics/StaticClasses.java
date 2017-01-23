package com.digotsoft.fluse.transpreter.statics;

import com.digotsoft.fluse.transpreter.Type;
import com.digotsoft.fluse.transpreter.clazz.FluseClass;
import com.digotsoft.fluse.transpreter.processing.JSClassToClassDescription;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Digot
 * @version 1.0
 */
public class StaticClasses {

    public static Set<FluseClass> classes = new LinkedHashSet<>(
            Arrays.asList(
                    new FluseClass( "Console", new JSClassToClassDescription( "console" ), Type.CLIENT )
            )
    );

}
