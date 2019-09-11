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
import java.util.List;
import java.util.Map;
public class VariableTable {
    private Map<String, VariableTableEntry > variables;
    public VariableTable() {
       variables = new HashMap<>();
    }
    public VariableTableEntry insertName(String name){
        VariableTableEntry vte = new VariableTableEntry();
        vte.setName(name);
        variables.put(name, vte);
        return vte;
    }
    public void updateType(String name, Integer type){
       variables.get(name).setType(type);
       if(type == DataTypeEnum.DataSource.ordinal()){
           variables.get(name).setType(type);
           variables.get(name).setStoragePointer(true);
       }
    }
    public void addVariable(VariableTableEntry vte){
        variables.put(vte.getName(), vte);
    }
    public void addDimention(String name, Integer dimention){
        variables.get(name).setDimension(dimention);
    }
    public void setGroup(List<String> groups){
        for(String group : groups){
            variables.get(group).setOrdered(true);
            variables.get(group).setOrderList(groups);
        }
    }
    public void setOrder(List<String> groups) {
        for (String group : groups) {
            variables.get(group).setOrdered(true);
            variables.get(group).setOrderList(groups);
        }
    }

    public VariableTableEntry getVariableProperties(String name){
        return variables.get(name);

    }

    public void variableStaticResolve(){

    }

}
