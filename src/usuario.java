import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class usuario {
    private static planosSelecionado planosSelecionado = new planosSelecionado();
    private static JLabel userInfoLabel;
    private static JLabel planoLabel; // Adicionado para mostrar o plano selecionado
    private static JLabel tempoRestanteLabel; // Adicionado para mostrar o tempo restante
    private static JFrame frame;
    private String nome; // Atributo privado nome
    private String email; // Atributo privado email
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public static void createAndShowGUI() {
        frame = new JFrame("Usuário - Plano de Saúde");
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
        userInfoLabel = new JLabel(getUserInfoHTML());
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewPanel.add(userInfoLabel);

        // Adiciona JLabels para plano e tempo restante
        planoLabel = new JLabel();
        tempoRestanteLabel = new JLabel();
        viewPanel.add(planoLabel);
        viewPanel.add(tempoRestanteLabel);

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

        // Botão de deletar conta
        JButton deleteAccountButton = new JButton("Deletar Conta");
        deleteAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja deletar sua conta?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                if (deleteUserAccount()) {
                    JOptionPane.showMessageDialog(frame, "Conta deletada com sucesso!");
                    confiConfig.logOut();
                    frame.dispose();
                    login_usu.main(null);
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro ao deletar a conta.");
                }
            }
        });
        viewPanel.add(deleteAccountButton);

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

        // Adiciona o painel principal ao frame
        frame.add(containerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static String getUserInfoHTML() {
        String planoAtivo = planosSelecionado.getPlano() != null ? planosSelecionado.getPlano() : "Sem plano escolhido";
        String dataExpiracao = getExpirationDate();
        return "<html><div style='text-align: center;'>" +
                "<h2>Bem-vindo, " + confiConfig.getNome() + "</h2>" +
                "<p>Aqui você pode visualizar e atualizar suas informações pessoais.</p>" +
                "<h3>Detalhes do Usuário:</h3>" +
                "<p>Nome: " + confiConfig.getNome() + "</p>" +
                "<p>Email: " + confiConfig.getEmail() + "</p>" +
                "<p>Plano Ativo: " + planoAtivo + "</p>" +
                "<p>Data de Expiração: " + dataExpiracao + "</p>" +
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

    private static boolean deleteUserAccount() {
        String sql = "DELETE FROM usuarios WHERE email = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, confiConfig.getEmail());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getExpirationDate() {
        String plano = planosSelecionado.getPlano();
        if (plano == null) {
            return "Sem data marcada";
        }
        LocalDate today = LocalDate.now();
        LocalDate expirationDate;
        if (plano.equals("Plano Mensal")) {
            expirationDate = today.plusMonths(1);
        } else if (plano.equals("Plano Anual")) {
            expirationDate = today.plusYears(1);
        } else {
            return "Sem data marcada";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return expirationDate.format(formatter);
    }

    // Método para atualizar a exibição do plano selecionado na tela do usuário
    public static void updatePlanoLabel() {
        String planoSelecionado = planosSelecionado.getPlano();
        if (planoSelecionado != null) {
            planoLabel.setText("Plano Selecionado: " + planoSelecionado);
        } else {
            planoLabel.setText("Nenhum Plano Selecionado");
        }
    }

    // Método para atualizar a exibição do tempo restante na tela do usuário
    public static void updateTempoRestanteLabel() {
        String dataExpiracao = getExpirationDate();
        tempoRestanteLabel.setText("Tempo Restante: " + dataExpiracao);
    }
}
