package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private AccountRepository accountRepository = new AccountRepository();
    private RoleRepository roleRepository = new RoleRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnSubmitFeature = req.getParameter("btnSubmitFeature");
        String btnCRUD = req.getParameter("btnCRUD");
        Optional<Account> account = Optional.of(new Account("", "", "", "", 1));
        List<Account> accounts = null;
        accounts = accountRepository.getAll();
        String btnSubmit = "Thêm";

        if(btnSubmitFeature.equalsIgnoreCase("Filter")){
            long filter = Long.parseLong(req.getParameter("filter"));
            if(filter != 0){
                accounts = accountRepository.getAccountsByRole(filter);
            }
        }
        else if(btnSubmitFeature.equals("Thêm")){
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            int status = Integer.parseInt(req.getParameter("status"));

            Account ac = new Account(fullName, email, password, phone, status);
            boolean result = accountRepository.create(ac);
            if(result){
                accounts = accountRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("Cập Nhật")){
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            int status = Integer.parseInt(req.getParameter("status"));
            account = accountRepository.getByID(Long.parseLong(req.getParameter("accountID")));
            account.get().setFullName(fullName);
            account.get().setEmail(email);
            account.get().setPassword(password);
            account.get().setPhone(phone);
            account.get().setStatus(status);
            boolean result = accountRepository.update(account.get());
            if(result){
                accounts = accountRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("XÓA")){
            boolean rs = accountRepository.delete(Long.parseLong(req.getParameter("accountID")));
            if(rs){
                accounts = accountRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equals("THÊM")){
            btnSubmit = "Thêm";
        }
        else{
            account = accountRepository.getByID(Long.parseLong(btnSubmitFeature));
            btnSubmit = "Cập Nhật";
        }
        List<Role> roles = roleRepository.getAll();
        req.setAttribute("account", account.get());
        req.setAttribute("accounts", accounts);
        req.setAttribute("roles", roles);
        req.setAttribute("btnSubmit", btnSubmit);
        req.getRequestDispatcher("managerAccount.jsp").forward(req,resp);
    }
}
