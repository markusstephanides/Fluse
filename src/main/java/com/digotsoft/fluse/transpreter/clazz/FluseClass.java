package com.digotsoft.fluse.transpreter.clazz;

import com.digotsoft.fluse.transpreter.Type;
import com.digotsoft.fluse.transpreter.processing.ProcessingDescription;

/**
 * @author Digot
 * @version 1.0
 */
public class FluseClass {

    private String name;
    private Type type;
    private ProcessingDescription processingDescription;

    public FluseClass( String name, ProcessingDescription processingDescription, Type type ) {
        this.name = name;
        this.processingDescription = processingDescription;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ProcessingDescription getProcessingDescription() {
        return processingDescription;
    }

    public Type getType() {
        return type;
    }
}
