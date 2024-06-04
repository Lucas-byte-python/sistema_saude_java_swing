import javax.swing.*;
import java.awt.*;

public class home {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Home - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Adiciona o menu
        MenuUtil.addMenu(frame);

        // Painel superior com título e logo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Plano de Saúde XYZ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel bannerLabel = new JLabel(new ImageIcon("path/to/your/banner.png"));
        topPanel.add(bannerLabel, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);

        // Painel central com informações e notícias
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de informações
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel InfoLabel = new JLabel("<html><div style='text-align: center;'>" +
                "<h2>Bem-vindo ao Plano de Saúde XYZ</h2>" +
                "<p>Nosso plano de saúde oferece uma ampla gama de serviços para garantir o seu bem-estar e de sua família.</p>" +
                "<h3>Serviços Oferecidos:</h3>" +
                "<ul style='list-style-type: disc; padding-center: 20px;'>" +
                "<li>Consultas Médicas</li>" +
                "<li>Exames Laboratoriais</li>" +
                "<li>Cirurgias</li>" +
                "<li>Atendimento de Urgência</li>" +
                "<li>Telemedicina</li>" +
                "</ul>" +
                "<h3>Contatos:</h3>" +
                "<p>Email: atendimento@planosaude.xyz</p>" +
                "<p>Telefone: (11) 1234-5678</p>" +
                "</div></html>");
        InfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        InfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(InfoLabel);

        centerPanel.add(infoPanel);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior com depoimentos e botão de logout
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel testimonialsPanel = new JPanel();
        testimonialsPanel.setLayout(new BoxLayout(testimonialsPanel, BoxLayout.Y_AXIS));


        bottomPanel.add(testimonialsPanel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e -> {
            frame.dispose();
            login_usu.main(null);
        });
        bottomPanel.add(logoutButton, BorderLayout.SOUTH);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
