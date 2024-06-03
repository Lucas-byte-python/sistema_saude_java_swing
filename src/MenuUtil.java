import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuUtil {

    public static void addMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu homeMenu = new JMenu("Home");
        JMenu consultaMenu = new JMenu("Consulta");
        JMenu planosMenu = new JMenu("Planos");
        JMenu agendamentoMenu = new JMenu("Agendamento");
        JMenu loginMenu = new JMenu("Login");

        menuBar.add(homeMenu);
        menuBar.add(consultaMenu);
        menuBar.add(planosMenu);
        menuBar.add(agendamentoMenu);
        menuBar.add(loginMenu);

        homeMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                home.main(null);
            }
        });

        consultaMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                consulta.main(null);
            }
        });

        planosMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                planos.main(null);
            }
        });

        agendamentoMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                agendamento.main(null);
            }
        });

        loginMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                login_usu.main(null);
            }
        });

        frame.setJMenuBar(menuBar);
    }
}