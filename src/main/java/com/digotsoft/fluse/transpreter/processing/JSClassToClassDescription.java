package com.digotsoft.fluse.transpreter.processing;

/**
 * @author Digot
 * @version 1.0
 */
public class JSClassToClassDescription extends ProcessingDescription {

    private String jsClassName;

    public JSClassToClassDescription( String jsClassName ) {
        super( ProcessingMethod.JS_CLASS_TO_CLASS );
        this.jsClassName = jsClassName;
    }

    public String getJsClassName() {
        return jsClassName;
    }
}
