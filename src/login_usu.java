import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login_usu {
    public static void main(String[] args) {
        // Criação do JFrame
        JFrame frame = new JFrame("Login - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Painel superior com o título e logo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Bem-vindo ao Plano de Saúde");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel);

        // Adicionando uma imagem de logo
        // JLabel logoLabel = new JLabel(new ImageIcon("path/to/your/logo.png"));
        // topPanel.add(logoLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        // Painel central com os campos de login
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Email:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordText = new JPasswordField(20);

        centerPanel.add(userLabel);
        centerPanel.add(userText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior com os botões de login e cadastro
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userText.getText();
                String senha = new String(passwordText.getPassword());

                // Verificar as credenciais no banco de dados
                if (authenticateUser(email, senha)) {
                    JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                    frame.dispose(); // Fecha a tela de login
                    usuario.main(null); // Abre a tela de Usuário
                } else {
                    JOptionPane.showMessageDialog(frame, "Email ou Senha incorretos!");
                }
            }
        });

        JButton registerButton = new JButton("Cadastrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha a tela de login
                cadastro_usu.main(null); // Abre a tela de cadastro
            }
        });

        bottomPanel.add(loginButton);
        bottomPanel.add(registerButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Tornar o frame visível
        frame.setVisible(true);
    }

    private static boolean authenticateUser(String email, String senha) {
        String sql = "SELECT nome, email FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String emailFromDb = rs.getString("email");
                    confiConfig.logIn(nome, emailFromDb);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
