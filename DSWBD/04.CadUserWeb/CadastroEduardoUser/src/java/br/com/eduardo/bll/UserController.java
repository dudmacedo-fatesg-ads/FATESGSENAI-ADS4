package br.com.eduardo.bll;

import br.com.eduardo.dal.UserDAO;
import br.com.eduardo.error.DatabaseException;
import br.com.eduardo.model.Eduardo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduardo
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final String INSERT_OR_EDIT = "";
    private static final String LIST = "";
    private final UserDAO dao;

    public UserController() {
        super();
        dao = new UserDAO();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward;

        switch (request.getParameter("action")) {
            case "delete":
                long eduardocpf = Integer.parseInt(request.getParameter("eduardocpf"));
                 {
                    try {
                        dao.deleteByID(eduardocpf);
                    } catch (DatabaseException ex) {
                        request.setAttribute("alert", ex.getMessage());
                    }
                }
                forward = LIST;
                request.setAttribute("alert", "Usuário excluído com sucesso.");
                request.setAttribute("users", dao.getAll());
                break;
            case "edit":
                forward = INSERT_OR_EDIT;
                eduardocpf = Long.parseLong(request.getParameter("eduardocpf"));
                try {
                    Eduardo user = dao.retrieve(eduardocpf);
                    request.setAttribute("user", user);
                } catch (DatabaseException ex) {
                    request.setAttribute("alert", ex.getMessage());
                }
                break;
            case "listUser":
                forward = LIST;
                request.setAttribute("users", dao.getAll());
                break;
            default:
                forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
