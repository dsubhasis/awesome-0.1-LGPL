package edu.sdsc.awesome.neo4j.awsmneo4jclient.postgresSQL;

public class query {


    public static String  viewQuery(String datasource ){
        String query = "(SELECT  p.viewname AS view, p.viewowner AS owner, p.definition AS defn, false AS indexed, false AS populated, 'view' AS type from pg_views p WHERE p.schemaname = '"+datasource+"') UNION ALL (SELECT q.matviewname AS view, q.matviewowner  AS owner, q.definition AS defn, q.hasindexes AS indexed , q.ispopulated AS populated, 'metview' AS type from pg_matviews q   WHERE q.schemaname = '"+datasource+"');";
        return query;
    }
}
