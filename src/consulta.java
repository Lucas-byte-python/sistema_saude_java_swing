import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class consulta {
    private static String nomeConsulta;
    private static String dataConsulta;
    private static String medicoConsulta;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Consulta - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Adicionar menu à tela
        MenuUtil.addMenu(frame);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 10 pixels de espaçamento horizontal e vertical

        // Panel para Nome
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Panel para Data
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dateLabel = new JLabel("Data:");
        JComboBox<Integer> dayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        });
        JComboBox<Integer> yearComboBox = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i <= currentYear + 10; i++) {
            yearComboBox.addItem(i);
        }
        datePanel.add(dateLabel);
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);

        // Panel para Médico
        JPanel doctorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel doctorLabel = new JLabel("Médico:");
        String[] doctors = {"Dr. João", "Dr. Maria", "Dr. Pedro", "Dr. Ana"};
        JComboBox<String> doctorComboBox = new JComboBox<>(doctors);
        doctorPanel.add(doctorLabel);
        doctorPanel.add(doctorComboBox);

        // Adicionar componentes aos painéis
        inputPanel.add(namePanel);
        inputPanel.add(datePanel);
        inputPanel.add(doctorPanel);

        // Panel para Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton bookButton = new JButton("Agendar");
        JButton deleteButton = new JButton("Deletar");
        buttonPanel.add(bookButton);
        buttonPanel.add(deleteButton);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        bookButton.addActionListener(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um nome para agendar a consulta.");
                return;
            }

            int day = (int) dayComboBox.getSelectedItem();
            int month = monthComboBox.getSelectedIndex() + 1; // meses começam de 0
            int year = (int) yearComboBox.getSelectedItem();
            String date = String.format("%04d-%02d-%02d", year, month, day);
            String doctor = (String) doctorComboBox.getSelectedItem();

            nomeConsulta = name;
            dataConsulta = date;
            medicoConsulta = doctor;

            if (insertConsulta(name, date, doctor)) {
                JOptionPane.showMessageDialog(frame, "Consulta agendada com sucesso!\nNome: " + nomeConsulta + "\nData: " + dataConsulta + "\nMédico: " + medicoConsulta);
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao agendar consulta.");
            }
        });

        deleteButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Insira o ID da consulta que deseja deletar:");
            if (input != null && !input.isEmpty()) {
                int consultaID = Integer.parseInt(input);
                if (deleteConsulta(consultaID)) {
                    JOptionPane.showMessageDialog(frame, "Consulta deletada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro ao deletar consulta.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static boolean insertConsulta(String name, String date, String doctor) {
        String sql = "INSERT INTO consultas (nome, dat, medico) VALUES (?, ?, ?)";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, date);
            pstmt.setString(3, doctor);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static boolean deleteConsulta(int consultaID) {
        String sql = "DELETE FROM consultas WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, consultaID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
