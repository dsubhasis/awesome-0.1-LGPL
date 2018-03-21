package edu.sdsc.awesome.common.connector;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;

public class DiskMapStructure {


    final Logger logger = LoggerFactory.getLogger(DiskMapStructure.class);

    public DiskMapStructure(String DBname) {
        this.DBname = DBname;
    }

    private String DBname;



    private ConcurrentMap InitmapDB(String DBname)
    {
        DB db = DBMaker.memoryDB().make();

        ConcurrentMap map = db.hashMap(DBname).createOrOpen();

        return map;
    }

    public ConcurrentMap InitTreeMapDB(String DBName)
    {

        String dbName = "_asm_"+getSaltString();
        DB db = DBMaker
                .fileDB(dbName)
                .fileMmapEnable()
                .make();

        ConcurrentMap map = db.treeMap(DBName).createOrOpen();

        return map;
    }
    public ConcurrentMap InitHashMapDB(String DBName)
    {

        String dbName = "_asm_"+getSaltString();
        DB db = DBMaker
                .fileDB(dbName)
                .fileMmapEnable()
                .make();

        ConcurrentMap map = db.treeMap(DBName).createOrOpen();

        return map;
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz"+System.nanoTime();
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }



}
