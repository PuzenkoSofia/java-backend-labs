package ru.puzenko.lab2.repository;

import ru.puzenko.lab2.entity.Building;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingRepositoryJdbc implements BuildingRepository {

    private final String url;
    private final String user;
    private final String password;

    public BuildingRepositoryJdbc(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS building (" +
                "id SERIAL PRIMARY KEY, " +
                "address VARCHAR(255) NOT NULL, " +
                "floors INT NOT NULL)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица building готова.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы", e);
        }
    }

    @Override
    public int save(Building building) {
        String sql = "INSERT INTO building (address, floors) VALUES (?, ?) RETURNING id";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, building.getAddress());
            pstmt.setInt(2, building.getFloors());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    building.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении", e);
        }
        return -1;
    }

    @Override
    public Building findById(int id) {
        String sql = "SELECT * FROM building WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске по id", e);
        }
        return null;
    }

    @Override
    public Building findByAddress(String address) {
        String sql = "SELECT * FROM building WHERE address = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, address);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске по адресу", e);
        }
        return null;
    }

    @Override
    public List<Building> findAll() {
        List<Building> buildings = new ArrayList<>();
        String sql = "SELECT * FROM building";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) buildings.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении всех", e);
        }
        return buildings;
    }

    @Override
    public boolean update(Building building) {
        String sql = "UPDATE building SET address = ?, floors = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, building.getAddress());
            pstmt.setInt(2, building.getFloors());
            pstmt.setInt(3, building.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении", e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM building WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении", e);
        }
    }

    private Building mapRow(ResultSet rs) throws SQLException {
        return new Building(rs.getInt("id"), rs.getString("address"), rs.getInt("floors"));
    }
}