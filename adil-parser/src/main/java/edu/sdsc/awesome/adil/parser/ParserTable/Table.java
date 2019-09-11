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

public class Table {
    private Map<String, AttributeElement> attributeTable;
    private Map<String, DecisionElement> decisionTable;

    public Table() {
        attributeTable = new HashMap<String, AttributeElement>();
        decisionTable = new HashMap<String, DecisionElement>();
    }

    public void addAttribute(String name, String attributeType, boolean store)
    {
        AttributeElement at = new AttributeElement(name, attributeType, store);
        attributeTable.put(name,at);
    }

    public boolean checkAttribute(String name){
        return attributeTable.containsKey(name);
    }

    public boolean delAttribute(String name){
        boolean flag =false;
        if(attributeTable.containsKey(name))
        {
            attributeTable.remove(name);
            flag = true;
        }
        return flag;
    }

    public DecisionElement getDecisionElement(String name){

        return decisionTable.get(name);

    }



}
