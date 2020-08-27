package Learn_MySQL.lesson03;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.*;

public class TestInsert {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            // 区别
            // 使用 ? 占位符代替参数
            String sql = "INSERT INTO users(id, name, password, email, birthday) " +
                    "VALUES (?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql);  // 预编译sql，先写sql，然后不执行

            // 手动给参数赋值
            st.setInt(1, 4);
            st.setString(2, "sugar");
            st.setString(3, "123123");
            st.setString(4, "406857586@qq.com");
            st.setString(5, "2020-02-02");
            // 注意 sql.Date 和 util.Date 的区别，getTime()获得时间戳

            // 执行
            int i = st.executeUpdate();
            if (i > 0)
                System.out.println("插入成功");

            // 如果是查询，则 executeQuery();
            // 返回的依然是 ResultSet

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, null);
        }

    }
}
