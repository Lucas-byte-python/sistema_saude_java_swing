import javax.swing.*;
import java.awt.*;

public class planos {
    private static JLabel selectedPlanLabel;
    private String mensal;
    private String anual;

    public void setMensal(String mensal) {
        this.mensal = mensal;
    }

    public String getMensal() {
        return mensal;
    }

    public void setAnual(String anual) {
        this.anual = anual;
    }

    public String getAnual() {
        return anual;
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Planos - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Adiciona o menu
        MenuUtil.addMenu(frame);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Nossos Planos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2, 20, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel mensalPanel = new JPanel();
        mensalPanel.setLayout(new BoxLayout(mensalPanel, BoxLayout.Y_AXIS));
        mensalPanel.setBorder(BorderFactory.createTitledBorder("Plano Mensal"));

        JLabel mensalPrice = new JLabel("R$ 199,00 / mês");
        mensalPrice.setFont(new Font("Arial", Font.BOLD, 18));
        mensalPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensalPanel.add(mensalPrice);

        JLabel mensalDesc = new JLabel("<html><div style='text-align: center;'>" +
                "<ul>" +
                "<li>Consultas ilimitadas</li>" +
                "<li>Exames laboratoriais</li>" +
                "<li>Atendimento de urgência</li>" +
                "<li>Telemedicina</li>" +
                "</ul></div></html>");
        mensalDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensalPanel.add(mensalDesc);

        JButton mensalButton = new JButton("Escolher Plano Mensal");
        mensalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensalButton.addActionListener(e -> {
            planosSelecionado.setPlano("Plano Mensal");
            updateSelectedPlanLabel();
            JOptionPane.showMessageDialog(frame, "Plano Mensal selecionado!");
        });
        mensalPanel.add(mensalButton);

        centerPanel.add(mensalPanel);

        JPanel anualPanel = new JPanel();
        anualPanel.setLayout(new BoxLayout(anualPanel, BoxLayout.Y_AXIS));
        anualPanel.setBorder(BorderFactory.createTitledBorder("Plano Anual"));

        JLabel anualPrice = new JLabel("R$ 1999,00 / ano");
        anualPrice.setFont(new Font("Arial", Font.BOLD, 18));
        anualPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        anualPanel.add(anualPrice);

        JLabel anualDesc = new JLabel("<html><div style='text-align: center;'>" +
                "<ul>" +
                "<li>Consultas ilimitadas</li>" +
                "<li>Exames laboratoriais</li>" +
                "<li>Atendimento de urgência</li>" +
                "<li>Telemedicina</li>" +
                "<li>Desconto em medicamentos</li>" +
                "</ul></div></html>");
        anualDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        anualPanel.add(anualDesc);

        JButton anualButton = new JButton("Escolher Plano Anual");
        anualButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        anualButton.addActionListener(e -> {
            planosSelecionado.setPlano("Plano Anual");
            updateSelectedPlanLabel();
            JOptionPane.showMessageDialog(frame, "Plano Anual selecionado!");
        });
        anualPanel.add(anualButton);

        centerPanel.add(anualPanel);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        selectedPlanLabel = new JLabel();
        selectedPlanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateSelectedPlanLabel();
        bottomPanel.add(selectedPlanLabel);

        JButton cancelPlanButton = new JButton("Cancelar Plano");
        cancelPlanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelPlanButton.addActionListener(e -> {
            planosSelecionado.setPlano(null);
            updateSelectedPlanLabel();
            JOptionPane.showMessageDialog(frame, "Plano cancelado!");
        });
        bottomPanel.add(cancelPlanButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void updateSelectedPlanLabel() {
        String planoAtivo = planosSelecionado.getPlano() != null ? planosSelecionado.getPlano() : "Nenhum plano selecionado";
        selectedPlanLabel.setText("Plano Ativo: " + planoAtivo);
    }
}
