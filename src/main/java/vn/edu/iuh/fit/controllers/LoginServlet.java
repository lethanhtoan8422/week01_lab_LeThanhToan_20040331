package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.Log;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();
    RoleRepository roleRepository = new RoleRepository();
    LogRepository logRepository = new LogRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<Account> account = accountRepository.checkAccount(email, password);
        req.setAttribute("account", account.get());
        if(account.get() != null){
            if(account.get().getEmail().equalsIgnoreCase("admin@gmail.com")){
                req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
            }
            else{
                List<Role> roles = roleRepository.getRolesByAccount(account.get().getId());
                req.setAttribute("roles", roles);
                req.getRequestDispatcher("account.jsp").forward(req, resp);
            }
            logRepository.create(new Log(LocalDateTime.now(), null, "note", account.get()));
        }
    }
}
