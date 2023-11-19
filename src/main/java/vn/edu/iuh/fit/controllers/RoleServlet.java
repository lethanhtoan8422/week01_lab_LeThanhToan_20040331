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

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    private AccountRepository accountRepository = new AccountRepository();
    private RoleRepository roleRepository = new RoleRepository();
    GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> rs = roleRepository.getAll();
        String btnSubmitFeature = req.getParameter("btnSubmitFeature");
        Role roleChosen = new Role("", "", 1);
        String btnSubmit = "Thêm";

        if(btnSubmitFeature.equals("Thêm")){
            String roleName = req.getParameter("roleName");
            String description = req.getParameter("description");
            int status = Integer.parseInt(req.getParameter("selectStatus"));

            Role role = new Role(roleName, description, status);
            boolean result = roleRepository.create(role);
            if(result){
                rs = roleRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("selectRow")){
            btnSubmit = "Cập Nhật";
            String roleID = req.getParameter("roleID");
            roleChosen = roleRepository.getByID(Long.parseLong(roleID)).get();
        }
        else if(btnSubmitFeature.equalsIgnoreCase("Cập Nhật")){
            String roleName = req.getParameter("roleName");
            String description = req.getParameter("description");
            int status = Integer.parseInt(req.getParameter("selectStatus"));

            Role r = roleRepository.getByID(Long.parseLong(req.getParameter("roleID"))).get();
            r.setName(roleName);
            r.setDescription(description);
            r.setStatus(status);

            boolean result = roleRepository.update(r);
            if(result){
                rs = roleRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("XÓA")){
            boolean result = roleRepository.delete(Long.parseLong(req.getParameter("roleID")));
            if(result){
                rs = roleRepository.getAll();
            }
        }
        req.setAttribute("role", roleChosen);
        req.setAttribute("roles", rs);
        req.setAttribute("btnSubmit", btnSubmit);
        req.getRequestDispatcher("managerRole.jsp").forward(req,resp);
    }
}
