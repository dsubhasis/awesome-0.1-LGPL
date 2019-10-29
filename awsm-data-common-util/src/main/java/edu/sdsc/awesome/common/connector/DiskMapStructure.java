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
