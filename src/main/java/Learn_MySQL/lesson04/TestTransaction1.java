package Learn_MySQL.lesson04;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction1 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            // 关闭数据库的自动提交，自动会开启事务
            conn.setAutoCommit(false);  // 关闭同时自动开启事务

            String sql = "UPDATE account SET money = money - 100 WHERE name = 'A'";
            st = conn.prepareStatement(sql);
            st.executeUpdate();

            String sql2 = "UPDATE account SET money = money + 100 WHERE name = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            // 业务完毕，提交事务
            conn.commit();
            System.out.println("事务提交成功");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();  // 如果提交失败，则回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }
}
