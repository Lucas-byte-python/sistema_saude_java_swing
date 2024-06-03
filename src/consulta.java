import javax.swing.*;
import java.awt.*;


public class consulta {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Consulta - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Adiciona o menu
        MenuUtil.addMenu(frame);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Consulta");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField(15); // Menos colunas
        JLabel dateLabel = new JLabel("Data:");
        JTextField dateField = new JTextField(8); // Menos colunas
        JLabel doctorLabel = new JLabel("Médico:");
        JTextField doctorField = new JTextField(15); // Menos colunas
        JButton bookButton = new JButton("Agendar Consulta");

        bookButton.addActionListener(e -> {
            String name = nameField.getText();
            String date = dateField.getText();
            String doctor = doctorField.getText();

            JOptionPane.showMessageDialog(frame, "Consulta agendada com sucesso!\nNome: " + name + "\nData: " + date + "\nMédico: " + doctor);
        });

        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        centerPanel.add(dateLabel);
        centerPanel.add(dateField);
        centerPanel.add(doctorLabel);
        centerPanel.add(doctorField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(bookButton);

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
