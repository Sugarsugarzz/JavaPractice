package Learn_MySQL.lesson02;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// SQL注入问题
public class SqlInjection {
    public static void main(String[] args) {

        login("zhangsan", "123456");
        login(" ' or '1=1", " ' or '1=1");  // 查询出所有用户信息
    }

    // 登录业务
    public static void login(String username, String password) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();  // 获取数据库连接
            st = conn.createStatement();  // 获取SQL的执行对象
            // SELECT * FROM users WHERE name='username' AND password='password'
            // SELECT * FROM users WHERE name='' or 1=1' AND password='' or 1=1'
            String sql = "SELECT * FROM users WHERE name='" + username + "' AND password='" + password + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
