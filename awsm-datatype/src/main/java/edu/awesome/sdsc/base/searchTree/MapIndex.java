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
