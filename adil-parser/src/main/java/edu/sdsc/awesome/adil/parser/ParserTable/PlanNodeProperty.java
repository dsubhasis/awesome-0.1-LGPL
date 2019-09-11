/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 */

package edu.sdsc.awesome.adil.parser.ParserTable;


import java.util.HashMap;
import java.util.Map;

public class PlanNodeProperty {

    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public Map getNodeProperty() {
        return nodeProperty;
    }
    public void setNodeProperty(Map nodeProperty) {
        this.nodeProperty = nodeProperty;
    }
    public PlanNodeProperty() {

        nodeProperty = new HashMap();

    }
    private String operation;
    private Map   nodeProperty ;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean inputNode=false;
    private boolean outputNode=false;

    public boolean isInputNode() {
        return inputNode;
    }

    public void setInputNode(boolean inputNode) {
        this.inputNode = inputNode;
    }

    public boolean isOutputNode() {
        return outputNode;
    }

    public void setOutputNode(boolean outputNode) {
        this.outputNode = outputNode;
    }
}
