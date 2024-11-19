package org.clinic.controller;

import org.clinic.dao.DoctorDAO;
import org.clinic.model.Doctor;

import java.sql.SQLException;
import java.util.List;

public class DoctorController {
    private final DoctorDAO doctorDAO;

    public DoctorController() {
        this.doctorDAO = new DoctorDAO();
    }

    /**
     * Получение всех врачей из базы данных.
     */
    public List<Doctor> getAllDoctors() throws SQLException {
        return doctorDAO.getAllDoctors();
    }

    /**
     * Добавление нового врача в базу данных.
     */
    public void addDoctor(Doctor doctor) throws SQLException {
        validateDoctor(doctor);
        doctorDAO.addDoctor(doctor);
    }

    /**
     * Обновление данных врача в базе данных.
     */
    public void updateDoctor(Doctor doctor) throws SQLException {
        validateDoctor(doctor);
        doctorDAO.updateDoctor(doctor);
    }

    /**
     * Удаление врача из базы данных.
     */
    public void deleteDoctor(int doctorId) throws SQLException {
        if (doctorId <= 0) {
            throw new IllegalArgumentException("Неверный идентификатор врача.");
        }
        doctorDAO.deleteDoctor(doctorId);
    }

    /**
     * Валидация данных врача.
     */
    private void validateDoctor(Doctor doctor) {
        if (doctor.getFirstName() == null || doctor.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Имя врача не должно быть пустым.");
        }
        if (doctor.getLastName() == null || doctor.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Фамилия врача не должна быть пустой.");
        }
        if (doctor.getBirthDate() == null || doctor.getBirthDate().isAfter(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("Дата рождения врача указана некорректно.");
        }
        if (doctor.getPosition() == null || doctor.getPosition().isEmpty()) {
            throw new IllegalArgumentException("Должность врача не указана.");
        }
        if (doctor.getSpecialization() == null || doctor.getSpecialization().isEmpty()) {
            throw new IllegalArgumentException("Специализация врача не указана.");
        }
    }
}
