import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class agendamento {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Agendamento - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Adiciona o menu
        MenuUtil.addMenu(frame);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Agendamento de Consultas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Calendário para seleção da data
        JLabel dateLabel = new JLabel("Selecione a Data:");
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(dateLabel);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        dateSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(dateSpinner);

        // Horários disponíveis
        JLabel timeLabel = new JLabel("Selecione o Horário:");
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(timeLabel);

        String[] times = {"08:00", "09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00"};
        JComboBox<String> timeComboBox = new JComboBox<>(times);
        timeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(timeComboBox);

        // Lista de médicos
        JLabel doctorLabel = new JLabel("Selecione o Médico:");
        doctorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(doctorLabel);

        List<String> doctors = Arrays.asList("Dr. João Silva", "Dr. Ana Pereira", "Dr. Carlos Souza", "Dra. Maria Oliveira");
        JComboBox<String> doctorComboBox = new JComboBox<>(doctors.toArray(new String[0]));
        doctorComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(doctorComboBox);

        // Botão de confirmação
        JButton confirmButton = new JButton("Confirmar Agendamento");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(e -> {
            LocalDate selectedDate = (LocalDate) dateSpinner.getValue();
            String selectedTime = (String) timeComboBox.getSelectedItem();
            String selectedDoctor = (String) doctorComboBox.getSelectedItem();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = selectedDate.format(formatter);

            JOptionPane.showMessageDialog(frame, "Agendamento confirmado!\nData: " + formattedDate + "\nHorário: " + selectedTime + "\nMédico: " + selectedDoctor);
        });
        centerPanel.add(confirmButton);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            frame.dispose();
            home.main(null);
        });
        bottomPanel.add(backButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}