package controller.Routes;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import services.AccountService;

@WebServlet(name = "AccountController", urlPatterns = {"/accounts/*"})
public class AccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Account currUser = (Account) request.getSession().getAttribute("user");

        if (currUser.getRoleInSystem() != 1) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        String path = request.getPathInfo();

        if (path == null) {
            path = "/";
        }

        switch (path) {
            case "/":
                List<Account> accounts = new AccountService().getAll();
                request.setAttribute("accounts", accounts);
                request.getRequestDispatcher("/Pages/Accounts/Accounts.jsp").forward(request, response);
                break;
            case "/new":
                request.getRequestDispatcher("/Pages/Accounts/NewAccount.jsp").forward(request, response);
                break;
            case "/delete":
                String id = request.getParameter("account");

                if (id != null) {
                    if (new AccountService().delete(id)) {
                        response.sendRedirect("/accounts");
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing account username");
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("account");
        AccountService accountService = new AccountService();

        if (idParam != null && !idParam.isEmpty()) {
            Account account = new Account(
                    request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("lastName"),
                    request.getParameter("firstName"),
                    Date.valueOf(request.getParameter("birthday")),
                    Boolean.parseBoolean(request.getParameter("gender")),
                    request.getParameter("phone"),
                    Boolean.parseBoolean(request.getParameter("editStatus")),
                    Integer.parseInt(request.getParameter("role"))
            );
            accountService.update(idParam, account);
        } else {
            Account account = new Account(
                    request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("lastName"),
                    request.getParameter("firstName"),
                    Date.valueOf(request.getParameter("birthday")),
                    Boolean.parseBoolean(request.getParameter("gender")),
                    request.getParameter("phone"),
                    true,
                    Integer.parseInt(request.getParameter("role"))
            );

            accountService.post(account);
        }

        response.sendRedirect("/accounts");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
