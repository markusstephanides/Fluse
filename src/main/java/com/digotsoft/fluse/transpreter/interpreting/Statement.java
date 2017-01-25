package com.digotsoft.fluse.transpreter.interpreting;

import com.digotsoft.fluse.transpreter.component.Component;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Markus
 */
public class Statement {

    private Statement parent;
    private StatementType type;
    private Set<Statement> childs;
    private Component component;

    public Statement(Statement parent, StatementType type) {
        this.parent = parent;
        this.type = type;
        this.childs = new LinkedHashSet<>();
    }

    public boolean addChild(Statement child){
        return this.childs.add(child);
    }

    public Statement getParent() {
        return parent;
    }

    public StatementType getType() {
        return type;
    }

    public Set<Statement> getChilds() {
        return childs;
    }
}
