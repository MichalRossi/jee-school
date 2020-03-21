package pl.coderslab.jeeschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pl.coderslab.jeeschool.util.DbUtil;

public class DbService {

    private static Connection createConn() throws SQLException {
        //String connUrl = "jdbc:mysql://localhost:3306/"+dbName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        return DbUtil.getConnection();//DriverManager.getConnection(connUrl, dbUser, dbPass);
    }

    public static Integer insertIntoDatabase(String query, List<String> params) throws SQLException {
        try (Connection conn = createConn()) {

            String[] generatedColumns = {"id"};
            PreparedStatement pst = conn.prepareStatement(query, generatedColumns);
            if (params != null) {
                int i = 1;
                for (String p : params) {
                    pst.setString(i++, p);
                }
            }

            pst.executeUpdate();

            ResultSet res = pst.getGeneratedKeys();

            if (res.next())
                return res.getInt(1);
            else
                return null;
        } catch (SQLException e) {
            throw e;
        }

    }

    public static List<String[]> getData(String query, List<String> params) throws SQLException {

        try (Connection conn = createConn()) {

            PreparedStatement st = getPreparedStatement(query, params, conn);
            ResultSet rs = st.executeQuery();

            ResultSetMetaData columns = rs.getMetaData();

            List<String[]> result = new ArrayList<>();

            while (rs.next()) {
                String[] row = new String[columns.getColumnCount()];
                for (int j = 1; j <= columns.getColumnCount(); j++) {
                    row[j - 1] = rs.getString(columns.getColumnName(j));
                }
                result.add(row);
            }

            return result;
        } catch (SQLException e) {
            throw e;
        }

    }

    private static PreparedStatement getPreparedStatement(String query, List<String> params, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);
        if (params != null) {
            int i = 1;
            for (String p : params) {
                st.setString(i++, p);
            }
        }
        return st;
    }

    public static int executeUpdate(String query, List<String> params)
            throws SQLException {
        try (Connection conn = createConn()) {
            PreparedStatement st = getPreparedStatement(query, params, conn);
            return st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
