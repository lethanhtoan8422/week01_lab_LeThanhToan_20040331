package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/grant-access")
public class GrantAccessServlet extends HttpServlet {
    private AccountRepository accountRepository = new AccountRepository();
    private RoleRepository roleRepository = new RoleRepository();
    GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GrantAccess> grantAccess = grantAccessRepository.getAll();
        List<Account> acs = accountRepository.getAll();
        List<Role> rls = roleRepository.getAll();
        String btnSubmitFeature = req.getParameter("btnSubmitFeature");
        GrantAccess grChosen = new GrantAccess(acs.get(0), rls.get(0), 1, "");
        String btnSubmit = "Thêm";

        if(btnSubmitFeature.equals("Thêm")){
            String account = req.getParameter("selectAccount");
            String role = req.getParameter("selectRole");
            int isGrant = Integer.parseInt(req.getParameter("selectIsGrant"));
            String note = req.getParameter("note");

            GrantAccess grant = new GrantAccess(accountRepository.getByID(Long.parseLong(account)).get(), roleRepository.getByID(Long.parseLong(role)).get(), isGrant, note);
            boolean result = grantAccessRepository.create(grant);
            if(result){
                grantAccess = grantAccessRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("selectRow")){
            btnSubmit = "Cập Nhật";
            String accountID = req.getParameter("accountID");
            String roleID = req.getParameter("roleID");
            int isGrant = Integer.parseInt(req.getParameter("isGrant"));
            String note = req.getParameter("note");
            grChosen.setAccount(accountRepository.getByID(Long.parseLong(accountID)).get());
            grChosen.setRole(roleRepository.getByID(Long.parseLong(roleID)).get());
            grChosen.setIsGrant(isGrant);
            grChosen.setNote(note);
        }
        else if(btnSubmitFeature.equalsIgnoreCase("Cập Nhật")){
            long accountID = Long.parseLong(req.getParameter("selectAccount"));
            long roleID = Long.parseLong(req.getParameter("selectRole"));
            int isGrant = Integer.parseInt(req.getParameter("selectIsGrant"));
            String note = req.getParameter("note");

            GrantAccess grantAs = grantAccessRepository.getByID(grChosen.getAccount().getId(), grChosen.getRole().getId());
            grantAs.setAccount(accountRepository.getByID(accountID).get());
            grantAs.setRole(roleRepository.getByID(roleID).get());
            grantAs.setNote(note);
            grantAs.setIsGrant(isGrant);

            boolean result = grantAccessRepository.update(grantAs);
            if(result){
                grantAccess = grantAccessRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("XÓA")){
            boolean rs = grantAccessRepository.delete(Long.parseLong(req.getParameter("accountID")), Long.parseLong(req.getParameter("roleID")));
            if(rs){
                grantAccess = grantAccessRepository.getAll();
            }
        }
        req.setAttribute("grant", grChosen);
        req.setAttribute("grantAccess", grantAccess);
        req.setAttribute("accounts", acs);
        req.setAttribute("roles", rls);
        req.setAttribute("btnSubmit", btnSubmit);
        req.getRequestDispatcher("managerGrantAccess.jsp").forward(req,resp);
    }
}
