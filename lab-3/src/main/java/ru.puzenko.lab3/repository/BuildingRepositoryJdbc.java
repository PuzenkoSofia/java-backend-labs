package ru.puzenko.lab3.repository;

import ru.puzenko.lab3.model.Building;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuildingRepositoryJdbc implements BuildingRepository {
    private final String url;
    private final String user;
    private final String password;

    public BuildingRepositoryJdbc(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public int save(Building building) {
        String sql = "INSERT INTO buildings (address, floors) VALUES (?, ?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, building.getAddress());
            stmt.setInt(2, building.getFloors());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка сохранения", e);
        }
        return -1;
    }

    @Override
    public Optional<Building> findById(int id) {
        String sql = "SELECT * FROM buildings WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Building(rs.getInt("id"), rs.getString("address"), rs.getInt("floors")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Building> findAll() {
        List<Building> list = new ArrayList<>();
        String sql = "SELECT * FROM buildings";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Building(rs.getInt("id"), rs.getString("address"), rs.getInt("floors")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка", e);
        }
        return list;
    }

    @Override
    public void update(Building building) {
        String sql = "UPDATE buildings SET address = ?, floors = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, building.getAddress());
            stmt.setInt(2, building.getFloors());
            stmt.setInt(3, building.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления", e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM buildings WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления", e);
        }
    }
}