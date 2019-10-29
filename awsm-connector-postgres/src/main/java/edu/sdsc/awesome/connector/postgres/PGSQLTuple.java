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

package edu.sdsc.awesome.connector.postgres;

import java.util.List;

public class PGSQLTuple {

    private String column_name;
    private String data_type;
    private Integer charecter_maximum_length;
    private String date_type_precession;
    private String udt_name;
    private String is_nullable;


    public String getColumn_name() {
        return column_name;
    }

    public String getData_type() {
        return data_type;
    }

    public Integer getCharecter_maximum_length() {
        return charecter_maximum_length;
    }

    public String getDate_type_precession() {
        return date_type_precession;
    }

    public String getUdt_name() {
        return udt_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public void setCharecter_maximum_length(Integer charecter_maximum_length) {
        this.charecter_maximum_length = charecter_maximum_length;
    }

    public void setDate_type_precession(String date_type_precession) {
        this.date_type_precession = date_type_precession;
    }

    public void setUdt_name(String udt_name) {
        this.udt_name = udt_name;
    }

    public void Setall(String column_name,  String udt_name,  String is_nullable, Integer character_maximum_length, String data_type, String date_type_precession){
        this.column_name=column_name;
        this.data_type = data_type;
        this.date_type_precession = date_type_precession;
        this.udt_name = udt_name;
        this.is_nullable = is_nullable;

        this.charecter_maximum_length = character_maximum_length;









        //column_name, data_type, udt_name,  is_nullable, character_maximum_length, data_type


    }


}
