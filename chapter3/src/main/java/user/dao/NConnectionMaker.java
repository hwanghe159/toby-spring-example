package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        // N 사의 DB connection 생성코드
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }
}
