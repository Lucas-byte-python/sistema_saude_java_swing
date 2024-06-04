import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuUtil {

    public static void addMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu homeMenu = new JMenu("Home");
        JMenu consultaMenu = new JMenu("Consulta");
        JMenu planosMenu = new JMenu("Planos");
        JMenu usuarioMenu = new JMenu("Usuário");

        menuBar.add(homeMenu);
        menuBar.add(consultaMenu);
        menuBar.add(planosMenu);
        menuBar.add(usuarioMenu);

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

        usuarioMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                usuario.main(null);
            }
        });

        frame.setJMenuBar(menuBar);
    }
}
