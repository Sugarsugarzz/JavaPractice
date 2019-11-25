package JsonTest;
import org.json.JSONObject;

public class TestJson {

    public static void main(String[] args) {

        JSONObject jo = new JSONObject();
        try {
            jo.put("name", "张三");
            jo.put("age", 12.2);
            System.out.println(jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
