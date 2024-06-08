import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class farmaceutico {
    private static JComboBox<String> cityComboBox;
    private static JComboBox<String> neighborhoodComboBox;
    private static JComboBox<String> medicineComboBox;

    private static Map<String, String[]> cityNeighborhoodMap;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Farmacêutico - Plano de Saúde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Adicionar menu à tela
        MenuUtil.addMenu(frame);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 10 pixels de espaçamento horizontal e vertical
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campo de seleção de remédio
        JLabel medicineLabel = new JLabel("Remédio:");
        String[] medicines = {"Paracetamol", "Ibuprofeno", "Dipirona", "Amoxicilina"};
        medicineComboBox = new JComboBox<>(medicines);

        inputPanel.add(medicineLabel);
        inputPanel.add(medicineComboBox);

        // Campo de seleção de cidade
        JLabel cityLabel = new JLabel("Cidade:");
        String[] cities = {"São Paulo - SP", "Rio de Janeiro - RJ", "Belo Horizonte - MG"};
        cityComboBox = new JComboBox<>(cities);

        // Adiciona listener para atualizar bairros com base na cidade selecionada
        cityComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNeighborhoods();
            }
        });

        inputPanel.add(cityLabel);
        inputPanel.add(cityComboBox);

        // Campo de seleção de bairro
        JLabel neighborhoodLabel = new JLabel("Bairro:");
        neighborhoodComboBox = new JComboBox<>();

        inputPanel.add(neighborhoodLabel);
        inputPanel.add(neighborhoodComboBox);

        // Mapa de cidades e bairros
        cityNeighborhoodMap = new HashMap<>();
        cityNeighborhoodMap.put("São Paulo - SP", new String[]{"Centro", "Moema", "Vila Madalena"});
        cityNeighborhoodMap.put("Rio de Janeiro - RJ", new String[]{"Copacabana", "Ipanema", "Leblon"});
        cityNeighborhoodMap.put("Belo Horizonte - MG", new String[]{"Savassi", "Lourdes", "Pampulha"});

        // Inicializar bairros com base na cidade inicial
        updateNeighborhoods();

        // Botão de Aguardar
        JButton awaitButton = new JButton("Aguardar");
        awaitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMedicine = (String) medicineComboBox.getSelectedItem();
                String selectedCity = (String) cityComboBox.getSelectedItem();
                String selectedNeighborhood = (String) neighborhoodComboBox.getSelectedItem();

                // Exibir mensagem com os itens escolhidos
                JOptionPane.showMessageDialog(frame, "Remédio: " + selectedMedicine + "\nCidade: " + selectedCity + "\nBairro: " + selectedNeighborhood);

                // Armazenar no banco de dados
                storeInDatabase(selectedMedicine, selectedCity, selectedNeighborhood);
            }
        });

        // Botão de Cancelar Remédio
        JButton cancelButton = new JButton("Cancelar Remédio");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar diálogo para inserção do ID do remédio
                showDeleteDialog(frame);
            }
        });

        inputPanel.add(awaitButton);
        inputPanel.add(cancelButton);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void updateNeighborhoods() {
        String selectedCity = (String) cityComboBox.getSelectedItem();
        String[] neighborhoods = cityNeighborhoodMap.get(selectedCity);

        neighborhoodComboBox.removeAllItems();
        if (neighborhoods != null) {
            for (String neighborhood : neighborhoods) {
                neighborhoodComboBox.addItem(neighborhood);
            }
        }
    }

    private static void storeInDatabase(String medicine, String city, String neighborhood) {
        String url = "jdbc:postgresql://localhost:5432/plano_saude";
        String username = "postgres";
        String password = "lucas2007";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO medicamentos (remedio, cidade, bairro) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, medicine);
                stmt.setString(2, city);
                stmt.setString(3, neighborhood);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFromDatabase(int id) {
        String url = "jdbc:postgresql://localhost:5432/plano_saude";
        String username = "postgres";
        String password = "lucas2007";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM medicamentos WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Remédio deletado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum remédio encontrado com o ID especificado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showDeleteDialog(JFrame parentFrame) {
        // Cria um JDialog modal
        JDialog deleteDialog = new JDialog(parentFrame, "Cancelar Remédio", true);
        deleteDialog.setSize(300, 150);
        deleteDialog.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel deleteIdLabel = new JLabel("ID do Remédio:");
        JTextField deleteIdTextField = new JTextField();

        JButton confirmDeleteButton = new JButton("Confirmar");
        confirmDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteId = deleteIdTextField.getText();
                if (!deleteId.isEmpty()) {
                    try {
                        int id = Integer.parseInt(deleteId);
                        // Deletar do banco de dados
                        deleteFromDatabase(id);
                        deleteDialog.dispose(); // Fechar o diálogo
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(deleteDialog, "ID inválido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(deleteDialog, "Por favor, insira o ID do remédio.");
                }
            }
        });

        deleteDialog.add(deleteIdLabel);
        deleteDialog.add(deleteIdTextField);
        deleteDialog.add(confirmDeleteButton);

        deleteDialog.setLocationRelativeTo(parentFrame); // Centralizar em relação ao frame pai
        deleteDialog.setVisible(true);
    }
}
