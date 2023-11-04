package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;
import vn.edu.iuh.fit.models.Log;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            case "Đăng Xuất" :
                Optional<Log> log = logRepository.getLogByAccount(Long.parseLong(req.getParameter("accountID")));
                log.get().setLogOut(LocalDateTime.now());
                logRepository.update(log.get());
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
            case "ACCOUNT" :
                List<Account> accounts = accountRepository.getAll();
                List<Role> roles = roleRepository.getAll();
                Account account = new Account("","","","",1);
                req.setAttribute("btnSubmit", "Thêm");
                req.setAttribute("account", account);
                req.setAttribute("accounts", accounts);
                req.setAttribute("roles", roles);
                req.getRequestDispatcher("managerAccount.jsp").forward(req,resp);
                break;
            case "ROLE" :
                break;
            case "GRANT-ACCESS" :
                List<GrantAccess> grantAccess = grantAccessRepository.getAll();
                List<Account> acs = accountRepository.getAll();
                List<Role> rls = roleRepository.getAll();
                GrantAccess grChosen = new GrantAccess(acs.get(0), rls.get(0), 1, "");
                req.setAttribute("btnSubmit", "Thêm");
                req.setAttribute("grantAccess", grantAccess);
                req.setAttribute("grant", grChosen);
                req.setAttribute("accounts", acs);
                req.setAttribute("roles", rls);
                req.getRequestDispatcher("managerGrantAccess.jsp").forward(req,resp);
                break;
            case "LOG" :
                break;
        }
    }
}
