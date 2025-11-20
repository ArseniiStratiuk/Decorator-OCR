package apps.lab10;

import java.sql.*;

public class SQLiteInjector {
    private static final String DB_URL = "jdbc:sqlite:data.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = """
                CREATE TABLE IF NOT EXISTS documents (
                    path TEXT PRIMARY KEY,
                    parsed_text TEXT
                )
            """;
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String get(String path) {
        String sql = "SELECT parsed_text FROM documents WHERE path = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, path);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("parsed_text");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void put(String path, String text) {
        String sql = "INSERT OR REPLACE INTO documents(path, parsed_text) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, path);
            pstmt.setString(2, text);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
