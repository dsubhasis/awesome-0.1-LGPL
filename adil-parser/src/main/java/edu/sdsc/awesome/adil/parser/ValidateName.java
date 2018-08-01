package edu.sdsc.awesome.adil.parser;

//

import java.util.List;

public class ValidateName {


    public static boolean CheckVAR(List name)
    {

        boolean flag = false;
        for(int i = 0; i< name.size();i++) {
            System.out.println("Validating Name : " + name + "  OK");
            flag = true;


        }
        return flag;
    }

    public static boolean CheckDB(String dbName,  List name)
    {

        boolean flag = false;
        for(int i = 0; i< name.size();i++) {
            System.out.println("Validating Name : " + name.get(i) + " FROM Schema NAME :  "+dbName +"...... FOUND OK");
            flag = true;


        }
        return flag;
    }
    public static boolean CheckTable(String name)
    {
        return true;
    }
}
