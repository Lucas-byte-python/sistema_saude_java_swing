public class confiConfig {
    private static String nome;
    private static String email;
    private static boolean isLoggedIn = false;

    public static String getNome() {
        return isLoggedIn ? nome : "";
    }

    public static void setNome(String nome) {
        confiConfig.nome = nome;
    }

    public static String getEmail() {
        return isLoggedIn ? email : "";
    }

    public static void setEmail(String email) {
        confiConfig.email = email;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void logIn(String nome, String email) {
        confiConfig.nome = nome;
        confiConfig.email = email;
        confiConfig.isLoggedIn = true;
    }

    public static void logOut() {
        confiConfig.nome = null;
        confiConfig.email = null;
        confiConfig.isLoggedIn = false;
    }
}