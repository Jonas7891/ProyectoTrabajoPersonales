public class Session {
    private User activeUser;

    public void startSession(User user) {
        activeUser = user;
        System.out.println("Sesión iniciada para el usuario: " + activeUser.getUsername());
    }

    public void endSession() {
        activeUser = null;
        System.out.println("Sesión cerrada.");
    }

    public User getActiveUser() {
        return activeUser;
    }

    public boolean isLoggedIn() {
        return activeUser != null;
    }
}