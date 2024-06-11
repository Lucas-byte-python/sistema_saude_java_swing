import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cadastro_usu {
    private static String nomeUsuario;
    private static String emailUsuario;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro de Usuário - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Painel superior com o título
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Cadastro de Usuário");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        // Painel central com os campos de cadastro
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameText = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordText = new JPasswordField(20);
        JLabel confirmPasswordLabel = new JLabel("Confirmar Senha:");
        JPasswordField confirmPasswordText = new JPasswordField(20);

        centerPanel.add(nameLabel);
        centerPanel.add(nameText);
        centerPanel.add(emailLabel);
        centerPanel.add(emailText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);
        centerPanel.add(confirmPasswordLabel);
        centerPanel.add(confirmPasswordText);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior com os botões de cadastrar e cancelar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton registerButton = new JButton("Cadastrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nameText.getText();
                String email = emailText.getText();
                String senha = new String(passwordText.getPassword());
                String confirmacaoSenha = new String(confirmPasswordText.getPassword());

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmacaoSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.");
                    return;
                }

                if (!senha.equals(confirmacaoSenha)) {
                    JOptionPane.showMessageDialog(frame, "As senhas não coincidem. Por favor, digite novamente.");
                    return;
                }

                // Código para inserir no banco de dados
                if (registerUser(nome, email, senha)) {
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!\nNome: " + nome + "\nEmail: " + email);
                    frame.dispose();
                    login_usu.main(null); // Volta para a tela de login
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar usuário.");
                }
            }
        });

        JButton cancelButton = new JButton("Já tem conta?");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha a tela de cadastro
                login_usu.main(null); // Volta para a tela de login
            }
        });

        bottomPanel.add(registerButton);
        bottomPanel.add(cancelButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Tornar o frame visível
        frame.setVisible(true);
    }

    private static boolean registerUser(String nome, String email, String senha) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/plano_saude", "postgres", "lucas2007");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
