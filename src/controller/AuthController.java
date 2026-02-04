package controller;

import dao.UserDAO;
import model.User;

public class AuthController {

    private UserDAO userDAO = new UserDAO();

    public User authenticate(String userId, String password, String role) {
        return userDAO.login(userId, password, role);
    }
}
