package awsm.example;

import edu.sdsc.awesome.connector.postgres.JDBCConnection;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SQLtoCypher {

    public static String pgurl = "jdbc:postgresql://10.128.5.135:5432";
    public static String pgpassword = "Sdsc2018#";
    public static String pguser = "postgres";

    public void SqlExec() throws SQLException, FileNotFoundException {


        JDBCConnection jd = new JDBCConnection(pgurl, pguser, pgpassword);
        String query = "SELECT t.created_at as t1_datetime, id as startnodeid, in_reply_to_user_id_str as replyid, retweeted_id, userid from twitterstatus t1 LIMIT 10;";

        Map rs = jd.pgSQLQuery(query);

        JsonObject gmap = graphMap();






    }


    public JsonObject graphMap() throws FileNotFoundException {

        String json = "{}";

        InputStream fis = new FileInputStream("awsm/example/config.json");

        JsonReader reader = Json.createReader(fis);

        JsonObject config = reader.readObject();

        reader.close();

        return config;
    }


    public static void main(String[] args) throws FileNotFoundException, SQLException {

        SQLtoCypher stq = new SQLtoCypher();
        stq.SqlExec();

    }


}
