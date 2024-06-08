import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUtil {
    public static void addMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenuItem homeItem = new JMenuItem("Home");
        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                home.main(null);
            }
        });

        JMenuItem plansItem = new JMenuItem("Planos");
        plansItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                planos.main(null);
            }
        });

        JMenuItem consultItem = new JMenuItem("Consulta");
        consultItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                consulta.main(null);
            }
        });

        JMenuItem farmaceuticoItem = new JMenuItem("Farmacêutico");
        farmaceuticoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                farmaceutico.main(null);
            }
        });

        JMenuItem usuarioItem = new JMenuItem("Usuário");
        usuarioItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                usuario.main(null);
            }
        });

        menu.add(homeItem);
        menu.add(plansItem);
        menu.add(consultItem);
        menu.add(farmaceuticoItem);
        menu.add(usuarioItem);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }
}
