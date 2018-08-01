import edu.sdsc.adil.AdilTest;
import edu.sdsc.adil.ParseException;
import edu.sdsc.adil.SimpleNode;


import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main( String[] args ){



        JsonObjectBuilder js = Json.createObjectBuilder();

        Map variableTable = new HashMap();
        String x = "IMPORT Dictionary text ;";
        Reader sr = new StringReader(x);
        AdilTest p = new AdilTest(sr);
        try {
            SimpleNode node = p.ADILStatement(js, variableTable);

            System.out.println(node.toString());

            node.dump(">");
        } catch (ParseException pe) {
            pe.printStackTrace();
        }


    }
}
