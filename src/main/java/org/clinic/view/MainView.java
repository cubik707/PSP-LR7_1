package org.clinic.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.clinic.controller.DoctorController;
import org.clinic.model.Doctor;

import java.time.LocalDate;
import java.util.List;

public class MainView {

    @FXML
    private TableView<Doctor> doctorTable;
    @FXML
    private TableColumn<Doctor, Integer> idColumn;
    @FXML
    private TableColumn<Doctor, String> firstNameColumn;
    @FXML
    private TableColumn<Doctor, String> lastNameColumn;
    @FXML
    private TableColumn<Doctor, String> middleNameColumn;
    @FXML
    private TableColumn<Doctor, LocalDate> birthDateColumn;
    @FXML
    private TableColumn<Doctor, String> positionColumn;
    @FXML
    private TableColumn<Doctor, String> specializationColumn;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField middleNameField;
    @FXML
    private DatePicker birthDateField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField specializationField;

    private final DoctorController controller = new DoctorController();

    @FXML
    public void initialize() {
        // Настраиваем таблицу
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        // Загружаем данные
        loadDoctors();
    }

    @FXML
    private void addDoctor() {
        try {
            Doctor doctor = new Doctor(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    middleNameField.getText(),
                    birthDateField.getValue(),
                    positionField.getText(),
                    specializationField.getText()
            );
            controller.addDoctor(doctor);
            loadDoctors();
        } catch (Exception e) {
            showError("Ошибка добавления", e.getMessage());
        }
    }

    @FXML
    private void updateDoctor() {
        Doctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            try {
                selectedDoctor.setFirstName(firstNameField.getText());
                selectedDoctor.setLastName(lastNameField.getText());
                selectedDoctor.setMiddleName(middleNameField.getText());
                selectedDoctor.setBirthDate(birthDateField.getValue());
                selectedDoctor.setPosition(positionField.getText());
                selectedDoctor.setSpecialization(specializationField.getText());
                controller.updateDoctor(selectedDoctor);
                loadDoctors();
            } catch (Exception e) {
                showError("Ошибка обновления", e.getMessage());
            }
        } else {
            showError("Ошибка", "Не выбран врач для обновления");
        }
    }

    @FXML
    private void deleteDoctor() {
        Doctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            try {
                controller.deleteDoctor(selectedDoctor.getId());
                loadDoctors();
            } catch (Exception e) {
                showError("Ошибка удаления", e.getMessage());
            }
        } else {
            showError("Ошибка", "Не выбран врач для удаления");
        }
    }

    @FXML
    private void refreshDoctors() {
        loadDoctors();
    }

    private void loadDoctors() {
        try {
            List<Doctor> doctors = controller.getAllDoctors();
            ObservableList<Doctor> doctorList = FXCollections.observableArrayList(doctors);
            doctorTable.setItems(doctorList);
        } catch (Exception e) {
            showError("Ошибка загрузки данных", e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
