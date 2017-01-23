package com.digotsoft.fluse.transpreter.processing;

/**
 * @author Digot
 * @version 1.0
 */
public abstract class ProcessingDescription {

    protected ProcessingMethod processingMethod;

    public ProcessingDescription( ProcessingMethod processingMethod ) {
        this.processingMethod = processingMethod;
    }

    public ProcessingMethod getProcessingMethod() {
        return processingMethod;
    }
}
