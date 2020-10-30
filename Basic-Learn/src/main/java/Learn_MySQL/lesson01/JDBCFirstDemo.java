package Learn_MySQL.lesson01;

import java.sql.*;

public class JDBCFirstDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1、加载驱动
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");  // 固定写法，加载驱动

        // 2、用户信息和URL
        // MySQL版本高于JDBC时，useSSL=true会报错，改成false即可。
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "123456";

        // 3、连接成功，数据库对象  Connection 代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 4、执行SQL的对象  Statement  执行sql的对象
        Statement statement = connection.createStatement();

        // 5、执行SQL的对象 去 执行SQL，可能存在结果，查看返回结果
        String sql = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);  // 返回的结果集，封装了全部的查询出来的结果

        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("password=" + resultSet.getObject("password"));
        }

        // 6、释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
