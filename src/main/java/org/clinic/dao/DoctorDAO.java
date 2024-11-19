package org.clinic.dao;

import org.clinic.model.DBConnection;
import org.clinic.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("middle_name"),
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getString("position"),
                        rs.getString("specialization")
                );
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    public void addDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO doctor (first_name, last_name, middle_name, birth_date, position, specialization) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getMiddleName());
            stmt.setDate(4, Date.valueOf(doctor.getBirthDate()));
            stmt.setString(5, doctor.getPosition());
            stmt.setString(6, doctor.getSpecialization());

            stmt.executeUpdate();
        }
    }

    public void updateDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE doctor SET first_name=?, last_name=?, middle_name=?, birth_date=?, position=?, specialization=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getMiddleName());
            stmt.setDate(4, Date.valueOf(doctor.getBirthDate()));
            stmt.setString(5, doctor.getPosition());
            stmt.setString(6, doctor.getSpecialization());
            stmt.setInt(7, doctor.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteDoctor(int id) throws SQLException {
        String query = "DELETE FROM doctor WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
