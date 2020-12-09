package user.dao;

import user.domain.User;

import java.sql.*;

public class UserDao {

    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        PreparedStatement ps = c.prepareStatement(
            "CREATE TABLE users (\n" +
                "    id varchar(10) primary key,\n" +
                "    name varchar(20) not null,\n" +
                "    password varchar(10) not null\n" +
                ")");

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        PreparedStatement ps = c.prepareStatement(
            "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }


    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
