import javax.swing.*;
import java.awt.*;

public class home {
    private String nome = "Consultoria Plano Saúde";
    private String email = "consultoria@gmail.com";
    private String telefone = "(85) 98484-8484";

    public void createHomeUI() {
        JFrame frame = new JFrame("Home - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Adiciona o menu
        MenuUtil.addMenu(frame);

        // Painel superior com título e logo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Plano de Saúde Viver-Bem", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel bannerLabel = new JLabel(new ImageIcon("path/to/your/banner.png"));
        topPanel.add(bannerLabel, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);

        // Painel central com informações e notícias
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de informações
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>" +
                "<h2>Bem-vindo ao Plano de Saúde Viver-Bem</h2>" +
                "<p>Nosso plano de saúde oferece uma ampla gama de serviços para garantir o seu bem-estar e de sua família.</p>" +
                "<h2>Serviços Oferecidos:</h2>" +
                "<ul style='list-style-type: none; padding-left: 20px;'>" +
                "<li>Consultas Médicas</li>" +
                "<li>Exames Laboratoriais</li>" +
                "<li>Cirurgias</li>" +
                "<li>Atendimento de Urgência</li>" +
                "<li>Telemedicina</li>" +
                "</ul>" +
                "<h3>Contatos:</h3>" +
                "<p>Email: " + getEmail() + "</p>" +
                "<p>Telefone: " + getTelefone() + "</p>" +
                "</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionar o JLabel dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(infoLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        infoPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(infoPanel, BorderLayout.CENTER);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior com depoimentos e botão de logout
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel testimonialsPanel = new JPanel();
        testimonialsPanel.setLayout(new BoxLayout(testimonialsPanel, BoxLayout.Y_AXIS));

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private String getNome() {
        return nome;
    }

    private String getEmail() {
        return email;
    }

    private String getTelefone() {
        return telefone;
    }
}
