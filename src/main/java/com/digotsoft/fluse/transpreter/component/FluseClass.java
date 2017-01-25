package com.digotsoft.fluse.transpreter.component;

import com.digotsoft.fluse.transpreter.Type;
import com.digotsoft.fluse.transpreter.processing.ProcessingDescription;

/**
 * @author Digot
 * @version 1.0
 */
public class FluseClass extends Component {

    private String name;
    private Type type;
    private FluseClass parent;
    private ProcessingDescription processingDescription;
    private AccessLevel accessLevel;

    public FluseClass(String name, Type type, ProcessingDescription processingDescription, AccessLevel accessLevel) {
        this.name = name;
        this.type = type;
        this.processingDescription = processingDescription;
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public ProcessingDescription getProcessingDescription() {
        return processingDescription;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setParent(FluseClass parent) {
        this.parent = parent;
    }
}
