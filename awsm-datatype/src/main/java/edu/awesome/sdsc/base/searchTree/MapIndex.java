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

package edu.awesome.sdsc.base.searchTree;

import org.mapdb.*;
import org.mapdb.serializer.SerializerCompressionWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MapIndex {
    Logger logger = Logger.
            getLogger(MapIndex.class.getName());
    DB db;
    private String fileName;
    public MapIndex(Map prop) {

        if(prop.containsKey("fileName")) {

            String fileName = (String) prop.get("fileName");
            Integer concurrencyStyle = (Integer) prop.get("concurrenceScale");
            Integer initialSize = (Integer) prop.get("initialSize");
            db = DBMaker.fileDB(fileName)
                    .closeOnJvmShutdown()
                    .concurrencyScale(concurrencyStyle)
                    .allocateStartSize(initialSize)
                    .make();
        }
        if(!prop.containsKey("fileName")) {
            Integer concurrencyStyle = (Integer) prop.get("concurrenceScale");
            Integer initialSize = (Integer) prop.get("initialSize");
            db = DBMaker.memoryDB()
                    .closeOnJvmShutdown()
                    .concurrencyScale(concurrencyStyle)
                    .allocateStartSize(initialSize)
                    .make();
        }

    }
    public BTreeMap Btree(String lname){
        BTreeMap<Long, String> lmap = null;
        try {
             lmap = db.treeMap(lname)
                   .valueSerializer(new SerializerCompressionWrapper(Serializer.STRING))
                   .valuesOutsideNodesEnable()
                   .createOrOpen();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "MAP Creation I/O Issus See Details "+e.getMessage());
        }
        return lmap;
    }
    public BTreeMap BTreeInsertAll(BTreeMap lmap, Map col){
        lmap.putAll(col);
        return lmap;
    }
    public HTreeMap HTreeMap(String name){

        HTreeMap<String, Long> lHmap = null;
        try {
            lHmap = db.hashMap(name).valueSerializer(Serializer.LONG).keySerializer(Serializer.STRING).createOrOpen();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hash Creation I/O Issus See Details "+e.getMessage());
        }
        return lHmap;
    }

    public HTreeMap HTreeMap(String name, Long ttl, Long maxMem){

        HTreeMap<String, Long> lHmap = null;
        try {
            lHmap = db.hashMap(name).valueSerializer(Serializer.LONG).keySerializer(Serializer.STRING)
                    .expireAfterCreate(ttl).expireMaxSize(maxMem).createOrOpen();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hash Creation I/O Issus See Details "+e.getMessage());
        }
        return lHmap;
    }


    public HTreeMap HTreeMap(String name, Long maxMem){
        HTreeMap<String, Long> lHmap = null;
        try {
            lHmap = db.hashMap(name).valueSerializer(Serializer.LONG).keySerializer(Serializer.STRING)
                    .expireMaxSize(maxMem).createOrOpen();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hash Creation I/O Issus See Details "+e.getMessage());
        }
        return lHmap;
    }

    public static void main( String[] args ){
        Map n = new HashMap();
        n.put("fileName","test");
        n.put("concurrenceScale", 10);
        n.put("initialSize", 1000);
        MapIndex bp = new MapIndex(n);
        BTreeMap lmap = bp.Btree("text");
        lmap.put(1L, "text");
        String op = (String) lmap.get(1L);
    }


}
