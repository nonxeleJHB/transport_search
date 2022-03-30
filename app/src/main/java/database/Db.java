package database;

import model.TransportSearchModel;

import java.sql.*;
import java.util.ArrayList;

public class Db {

    public static void createNewDatabase() {
        String url = "jdbc:sqlite:C:/Users/nomsa/sqlite/TransportSearch/transport_search.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:C:/Users/nomsa/sqlite/TransportSearch/transport_search.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void createTaxiRankTable() {
        String url = "jdbc:sqlite:C:/Users/nomsa/sqlite/TransportSearch/transport_search.db";

        String sql = "CREATE TABLE IF NOT EXISTS taxiRank (\n"
                + "taxiRankID integer PRIMARY KEY,\n"
                + "taxiRankName text NOT NULL\n"
                + ");";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            // create a new table
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createRouteTable() {
        String url = "jdbc:sqlite:C:/Users/nomsa/sqlite/TransportSearch/transport_search.db";

        String sql = "CREATE TABLE IF NOT EXISTS route (\n"
                + "routeID integer PRIMARY KEY,\n"
                + "routeName text NOT NULL,\n"
                + "taxiRankID integer,\n"
                + "FOREIGN KEY(taxiRankID) REFERENCES taxiRank(taxiRankID)\n"
                + ");";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createLinkTable() {
        String url = "jdbc:sqlite:C:/Users/nomsa/sqlite/TransportSearch/transport_search.db";

        String sql = "CREATE TABLE IF NOT EXISTS link (\n"
                + "routePointID integer PRIMARY KEY,\n"
                + "taxiRankID integer,\n"
                + "routeID integer,\n"
                + "FOREIGN KEY(taxiRankID) REFERENCES taxiRank(taxiRankID),\n"
                + "FOREIGN KEY(routeID) REFERENCES route(routeID)\n"
                + ");";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<TransportSearchModel> selectTaxiRank(String taxiRankName) {

        String sql = "SELECT *"
                + "FROM taxiRank WHERE taxiRankName LIKE ?";
        ArrayList<TransportSearchModel> taxiRankList = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + taxiRankName + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TransportSearchModel transportSearch = new TransportSearchModel();
                transportSearch.setTaxiRankID(rs.getInt("taxiRankID"));
                transportSearch.setTaxiRankName(rs.getString("taxiRankName"));

                taxiRankList.add(transportSearch);
                System.out.println(rs.getInt("taxiRankID") + "\t" +
                        rs.getString("taxiRankName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return taxiRankList;
    }

    public ArrayList<TransportSearchModel> selectRoute(String routeName, Integer taxiRankID) {

        String sql = "SELECT *"
                + "FROM route WHERE routeName, taxiRankID LIKE ?, ?";
        ArrayList<TransportSearchModel> routList = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + routeName + "%");
            pstmt.setInt(2, taxiRankID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TransportSearchModel transportSearch = new TransportSearchModel();
                transportSearch.setRouteID(rs.getInt("routeID"));
                transportSearch.setRouteName(rs.getString("routeName"));
                transportSearch.setTaxiRankID(rs.getInt("taxiRankID"));

                routList.add(transportSearch);
                System.out.println(rs.getInt("routeID") + "\t" +
                        rs.getString("routeName") + "\t" +
                        rs.getInt("taxiRankID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return routList;
    }

    public ArrayList<TransportSearchModel> selectLink(Integer taxiRankID, Integer routeID) {

        String sql = "SELECT *"
                + "FROM link WHERE routeID, taxiRankID LIKE ?, ?";
        ArrayList<TransportSearchModel> linkList = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, routeID);
            pstmt.setInt(2, taxiRankID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TransportSearchModel transportSearch = new TransportSearchModel();
                transportSearch.setRouteID(rs.getInt("routeID"));
                transportSearch.setTaxiRankID(rs.getInt("taxiRankID"));

                linkList.add(transportSearch);
                System.out.println(rs.getInt("routeID") + "\t" +
                        rs.getInt("taxiRankID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return linkList;

    }

}