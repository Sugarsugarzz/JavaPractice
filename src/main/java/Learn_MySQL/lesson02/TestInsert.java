package Learn_MySQL.lesson02;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();  // 获取数据库连接
            st = conn.createStatement();  // 获取SQL的执行对象
            String sql = "INSERT INTO users(id, `name`, `password`, `email`, `birthday`)" +
                    "VALUES (4, 'sugar', '123456', '406857586@qq.com', '2020-01-01')";
            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
