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
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
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

        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>" +
                "<h2>Bem-vindo ao Plano de Saúde XYZ</h2>" +
                "<p>Nosso plano de saúde oferece uma ampla gama de serviços para garantir o seu bem-estar e de sua família.</p>" +
                "<h3>Serviços Oferecidos:</h3>" +
                "<ul>" +
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
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(infoLabel);

        centerPanel.add(infoPanel);

        // Painel de notícias e atualizações
        JPanel newsPanel = new JPanel();
        newsPanel.setLayout(new BoxLayout(newsPanel, BoxLayout.Y_AXIS));

        JLabel newsTitleLabel = new JLabel("Notícias e Atualizações");
        newsTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        newsTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newsPanel.add(newsTitleLabel);

        JLabel newsLabel = new JLabel("<html><div style='text-align: left;'>" +
                "<ul>" +
                "<li><strong>01/06/2024:</strong> Novo serviço de telemedicina disponível 24 horas por dia.</li>" +
                "<li><strong>15/05/2024:</strong> Inauguração de uma nova unidade no centro da cidade.</li>" +
                "<li><strong>30/04/2024:</strong> Desconto especial para novos membros. Confira!</li>" +
                "</ul>" +
                "</div></html>");
        newsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        newsPanel.add(newsLabel);

        centerPanel.add(newsPanel);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior com depoimentos e botão de logout
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel testimonialsPanel = new JPanel();
        testimonialsPanel.setLayout(new BoxLayout(testimonialsPanel, BoxLayout.Y_AXIS));

        JLabel testimonialsTitleLabel = new JLabel("Depoimentos de Usuários");
        testimonialsTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        testimonialsTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        testimonialsPanel.add(testimonialsTitleLabel);

        JLabel testimonialsLabel = new JLabel("<html><div style='text-align: left;'>" +
                "<ul>" +
                "<li>\"O atendimento é excelente! Sinto-me muito bem cuidado.\" - Maria Silva</li>" +
                "<li>\"A variedade de serviços oferecidos é incrível. Recomendo a todos!\" - João Santos</li>" +
                "<li>\"Os médicos são muito atenciosos e profissionais.\" - Ana Oliveira</li>" +
                "</ul>" +
                "</div></html>");
        testimonialsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        testimonialsPanel.add(testimonialsLabel);

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