import javax.swing.*;
import java.awt.*;

public class usuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Usuário - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Adiciona o menu
        MenuUtil.addMenu(frame);

        // Painel principal com informações do usuário
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Informações do Usuário");
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(userLabel);

        // Informações do usuário
        JLabel userInfoLabel = new JLabel("<html><div style='text-align: center;'>" +
                "<h2>Bem-vindo, Lucas</h2>" +
                "<p>Aqui você pode visualizar e atualizar suas informações pessoais.</p>" +
                "<h3>Detalhes do Usuário:</h3>" +
                "<p>Nome: Lucas Santil</p>" +
                "<p>Email: lucas@gmail</p>" +
                "<p>Plano Ativo: Há escolher</p>" +
                "<p>Data de Expiração: 1 ano</p>" +
                "</div></html>");
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(userInfoLabel);

        // Botão de Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e -> {
            frame.dispose();
            login_usu.main(null);
        });
        mainPanel.add(logoutButton);

        // Painel de contenção para centralizar mainPanel
        JPanel containerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        containerPanel.add(mainPanel, gbc);

        frame.add(containerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
