package com.digotsoft.fluse.transpreter.component;

import com.digotsoft.fluse.transpreter.Type;

/**
 * @author Markus
 */
public class FutureClass extends FluseClass {

    private String classToBeLoaded;

    public FutureClass(String classToBeLoaded) {
        super(classToBeLoaded, Type.SERVER, null, AccessLevel.PUBLIC);
        this.classToBeLoaded = classToBeLoaded;
    }

    public String getClassToBeLoaded() {
        return classToBeLoaded;
    }
}
