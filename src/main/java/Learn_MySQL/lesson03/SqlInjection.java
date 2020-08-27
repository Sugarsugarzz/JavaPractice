package Learn_MySQL.lesson03;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.*;

// 使用PreparedStatment防止SQL注入
public class SqlInjection {
    public static void main(String[] args) {

        login("zhangsan", "123456");
        login(" ' or '1=1", " ' or '1=1");
    }

    // 登录业务
    public static void login(String username, String password) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            // PreparedStatement 防止 SQL 注入的本质，把传递进来的参数当做字符
            // 假设其中存在转义字符没救直接忽略， 比如 ' 会被直接转义
            String sql = "SELECT * FROM users WHERE name= ? AND password= ?";  // Mybatis
            st = conn.prepareStatement(sql);

            st.setString(1, "sugar");
            st.setString(2, "123123");

            rs = st.executeQuery();
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
