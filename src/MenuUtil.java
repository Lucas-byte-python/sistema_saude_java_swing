import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUtil {
    private String homeString;
    private String planosString;
    private String consultasString;
    private String farmaceuticosString;
    private String usuarioString;

    public void setHome(String homeString) {
        this.homeString = homeString;
    }
    public String getHome() {
        return homeString;
    }
    public void setpla(String planosString) {
        this.planosString = planosString;
    }
    public String getpla() {
        return planosString;
    }
    public void setcon(String consultasString) {
        this.consultasString = consultasString;
    }
    public String getcon() {
        return consultasString;
    }
    public void setfar(String farmaceuticosString) {
        this.farmaceuticosString = farmaceuticosString;
    }
    public String getfar() {
        return farmaceuticosString;
    }
    public void setusu(String usuarioString) {
        this.farmaceuticosString = usuarioString;
    }
    public String getusu() {
        return usuarioString;
    }
    // Construtor padrão
    public MenuUtil() {
        // Inicialize os atributos conforme necessário
        homeString = "Home";
        planosString = "Planos";
        consultasString = "Consulta";
        farmaceuticosString = "Farmacêutico";
        usuarioString = "Usuário";
    }

    // Getters e Setters para os atributos
    public String getHomeString() {
        return homeString;
    }

    public void setHomeString(String homeString) {
        this.homeString = homeString;
    }

    public String getPlanosString() {
        return planosString;
    }

    public void setPlanosString(String planosString) {
        this.planosString = planosString;
    }

    public String getConsultasString() {
        return consultasString;
    }

    public void setConsultasString(String consultasString) {
        this.consultasString = consultasString;
    }

    public String getFarmaceuticosString() {
        return farmaceuticosString;
    }

    public void setFarmaceuticosString(String farmaceuticosString) {
        this.farmaceuticosString = farmaceuticosString;
    }

    public String getUsuarioString() {
        return usuarioString;
    }

    public void setUsuarioString(String usuarioString) {
        this.usuarioString = usuarioString;
    }

    public static void addMenu(JFrame frame) {
        MenuUtil menuUtil = new MenuUtil(); // Instanciar a classe MenuUtil para acessar seus métodos

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenuItem homeItem = new JMenuItem(menuUtil.getHomeString()); // Usar o getter
        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                home homePage = new home();
                homePage.createHomeUI();
            }
        });

        JMenuItem plansItem = new JMenuItem(menuUtil.getPlanosString()); // Usar o getter
        plansItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                planos.createAndShowGUI(); // Corrigido para chamar o método estático de planos
            }
        });

        JMenuItem consultItem = new JMenuItem(menuUtil.getConsultasString()); // Usar o getter
        consultItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                consulta consultaPage = new consulta();
                consultaPage.createAndShowGUI();
            }
        });

        JMenuItem farmaceuticoItem = new JMenuItem(menuUtil.getFarmaceuticosString()); // Usar o getter
        farmaceuticoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                farmaceutico farmaceuticoPage = new farmaceutico();
                farmaceuticoPage.createAndShowGUI();
            }
        });

        JMenuItem usuarioItem = new JMenuItem(menuUtil.getUsuarioString()); // Usar o getter
        usuarioItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                usuario usuarioPage = new usuario();
                usuarioPage.createAndShowGUI();
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
