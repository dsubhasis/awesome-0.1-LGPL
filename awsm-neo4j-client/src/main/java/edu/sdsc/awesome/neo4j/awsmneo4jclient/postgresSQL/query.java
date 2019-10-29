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

package edu.sdsc.awesome.neo4j.awsmneo4jclient.postgresSQL;

public class query {


    public static String  viewQuery(String datasource ){
        String query = "(SELECT  p.viewname AS view, p.viewowner AS owner, p.definition AS defn, false AS indexed, false AS populated, 'view' AS type from pg_views p WHERE p.schemaname = '"+datasource+"') UNION ALL (SELECT q.matviewname AS view, q.matviewowner  AS owner, q.definition AS defn, q.hasindexes AS indexed , q.ispopulated AS populated, 'metview' AS type from pg_matviews q   WHERE q.schemaname = '"+datasource+"');";
        return query;
    }
}
