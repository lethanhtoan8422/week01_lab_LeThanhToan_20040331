package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();
    RoleRepository roleRepository = new RoleRepository();
    GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
    LogRepository logRepository = new LogRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnSubmit = req.getParameter("btnSubmit");
        switch(btnSubmit){
            case "ACCOUNT" :
                List<Account> accounts = accountRepository.getAll();
                req.setAttribute("accounts", accounts);
                req.getRequestDispatcher("managerAccount.jsp").forward(req,resp);
                break;
            case "ROLE" :
                break;
            case "GRANT-ACCESS" :
                break;
            case "LOG" :
                break;
        }
    }
}
