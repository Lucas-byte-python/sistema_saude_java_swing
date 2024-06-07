import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class usuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Usuário - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Adiciona o menu
        MenuUtil.addMenu(frame);

        // Painel principal com informações do usuário
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel de visualização
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));

        JLabel userLabel = new JLabel("Informações do Usuário");
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewPanel.add(userLabel);

        // Informações do usuário
        String planoAtivo = planosSelecionado.getPlano() != null ? planosSelecionado.getPlano() : "Sem plano escolhido";
        JLabel userInfoLabel = new JLabel(getUserInfoHTML());
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewPanel.add(userInfoLabel);

        // Espaçamento entre informações e botões
        viewPanel.add(Box.createVerticalStrut(20));

        // Botões de trocar nome e email
        JButton changeNameButton = new JButton("Trocar Nome");
        changeNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeNameButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "editNamePanel");
        });
        viewPanel.add(changeNameButton);

        JButton changeEmailButton = new JButton("Trocar Email");
        changeEmailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeEmailButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "editEmailPanel");
        });
        viewPanel.add(changeEmailButton);

        // Espaçamento entre botões
        viewPanel.add(Box.createVerticalStrut(20));

        // Botão de Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e -> {
            confiConfig.logOut();
            frame.dispose();
            login_usu.main(null);
        });
        viewPanel.add(logoutButton);

        // Painel de edição de nome
        JPanel editNamePanel = new JPanel();
        editNamePanel.setLayout(new BoxLayout(editNamePanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Alterar Nome:");
        JTextField nameField = new JTextField(confiConfig.getNome(), 20);
        nameField.setMaximumSize(nameField.getPreferredSize());

        JButton updateNameButton = new JButton("Atualizar Nome");
        updateNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateNameButton.addActionListener(e -> {
            String newName = nameField.getText();
            if (updateUserInfo("nome", newName)) {
                confiConfig.setNome(newName);
                JOptionPane.showMessageDialog(frame, "Nome atualizado com sucesso!");
                userInfoLabel.setText(getUserInfoHTML());
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "viewPanel");
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao atualizar o nome.");
            }
        });

        editNamePanel.add(nameLabel);
        editNamePanel.add(nameField);
        editNamePanel.add(updateNameButton);

        // Botão de cancelar
        JButton cancelNameButton = new JButton("Cancelar");
        cancelNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelNameButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "viewPanel");
        });
        editNamePanel.add(cancelNameButton);

        // Painel de edição de email
        JPanel editEmailPanel = new JPanel();
        editEmailPanel.setLayout(new BoxLayout(editEmailPanel, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Alterar Email:");
        JTextField emailField = new JTextField(confiConfig.getEmail(), 20);
        emailField.setMaximumSize(emailField.getPreferredSize());

        JButton updateEmailButton = new JButton("Atualizar Email");
        updateEmailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateEmailButton.addActionListener(e -> {
            String newEmail = emailField.getText();
            if (updateUserInfo("email", newEmail)) {
                confiConfig.setEmail(newEmail);
                JOptionPane.showMessageDialog(frame, "Email atualizado com sucesso!");
                userInfoLabel.setText(getUserInfoHTML());
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "viewPanel");
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao atualizar o email.");
            }
        });

        editEmailPanel.add(emailLabel);
        editEmailPanel.add(emailField);
        editEmailPanel.add(updateEmailButton);

        // Botão de cancelar
        JButton cancelEmailButton = new JButton("Cancelar");
        cancelEmailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelEmailButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "viewPanel");
        });
        editEmailPanel.add(cancelEmailButton);

        // Espaçamento no painel de edição de email
        editEmailPanel.add(Box.createVerticalStrut(20));

        // Adiciona os painéis ao painel principal
        mainPanel.add(viewPanel, "viewPanel");
        mainPanel.add(editNamePanel, "editNamePanel");
        mainPanel.add(editEmailPanel, "editEmailPanel");

        // Define o painel inicial
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "viewPanel");

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

    private static String getUserInfoHTML() {
        String planoAtivo = planosSelecionado.getPlano() != null ? planosSelecionado.getPlano() : "Sem plano escolhido";
        return "<html><div style='text-align: center;'>" +
                "<h2>Bem-vindo, " + confiConfig.getNome() + "</h2>" +
                "<p>Aqui você pode visualizar e atualizar suas informações pessoais.</p>" +
                "<h3>Detalhes do Usuário:</h3>" +
                "<p>Nome: " + confiConfig.getNome() + "</p>" +
                "<p>Email: " + confiConfig.getEmail() + "</p>" +
                "<p>Plano Ativo: " + planoAtivo + "</p>" +
                "<p>Data de Expiração: 1 ano</p>" +
                "</div></html>";
    }

    private static boolean updateUserInfo(String field, String newValue) {
        String sql = "UPDATE usuarios SET " + field + " = ? WHERE email = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newValue);
            pstmt.setString(2, confiConfig.getEmail());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

