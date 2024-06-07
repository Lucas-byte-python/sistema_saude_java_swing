import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class consulta {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Consulta - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Adicionar menu à tela
        MenuUtil.addMenu(frame);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 10 pixels de espaçamento horizontal e vertical

        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField();
        JLabel dateLabel = new JLabel("Data:");
        JTextField dateField = new JTextField();
        JLabel doctorLabel = new JLabel("Médico:");
        JTextField doctorField = new JTextField();
        JButton bookButton = new JButton("Agendar");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(doctorLabel);
        inputPanel.add(doctorField);
        inputPanel.add(bookButton);

        frame.add(inputPanel, BorderLayout.CENTER);

        bookButton.addActionListener(e -> {
            String name = nameField.getText();
            String date = dateField.getText();
            String doctor = doctorField.getText();

            if (insertConsulta(name, date, doctor)) {
                JOptionPane.showMessageDialog(frame, "Consulta agendada com sucesso!\nNome: " + name + "\nData: " + date + "\nMédico: " + doctor);
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao agendar consulta.");
            }
        });

        frame.setVisible(true);
    }

    private static boolean insertConsulta(String name, String date, String doctor) {
        String sql = "INSERT INTO consultas (nome, data, medico) VALUES (?, ?, ?)";

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
}
