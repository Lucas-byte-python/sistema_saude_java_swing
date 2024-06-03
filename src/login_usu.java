import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel logoLabel = new JLabel(new ImageIcon("path/to/your/logo.png"));
        topPanel.add(logoLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        // Painel central com os campos de login
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Usuário:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordText = new JPasswordField(20);

        centerPanel.add(userLabel);
        centerPanel.add(userText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior com o botão de login
        JPanel bottomPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                // Aqui você pode adicionar a lógica de autenticação
                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                    frame.dispose(); // Fecha a tela de login
                    home.main(null); // Abre a tela de Home
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha incorretos!");
                }
            }
        });
        bottomPanel.add(loginButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Tornar o frame visível
        frame.setVisible(true);
    }
}
