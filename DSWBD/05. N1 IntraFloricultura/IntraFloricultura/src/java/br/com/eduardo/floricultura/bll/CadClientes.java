package br.com.eduardo.floricultura.bll;

import br.com.eduardo.floricultura.dal.ClienteDAO;
import br.com.eduardo.floricultura.model.Cliente;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "cadClientes", urlPatterns = {"/cadClientes"})
public class CadClientes extends HttpServlet {

    private static final String INSERT_OR_EDIT = "?p=editClientes";
    private static final String LIST = "?p=listClientes";
    private final ClienteDAO dao;

    public CadClientes() {
        super();
        dao = new ClienteDAO();
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
        String forward = LIST;
        String action = request.getParameter("action");

        // Listar (Padrão)
        if (action == null || action.equalsIgnoreCase("list")) {
            forward = LIST;
            try {
                request.setAttribute("clientes", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar clientes");
                Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Excluir
        else if (action.equals("delete")) {
            long idf = Long.parseLong(request.getParameter("idf"));
            char tipo = request.getParameter("tipo").charAt(0);

            Cliente cliente = new Cliente();
            cliente.setIdf(idf);
            cliente.setTipo(tipo);
            try {
                dao.delete(cliente);
                request.setAttribute("alert", "Cliente excluído com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao excluir cliente");
                Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = LIST;
            try {
                request.setAttribute("clientes", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar clientes");
                Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("edit")) {
//            
            forward = INSERT_OR_EDIT;
            long idf = Long.parseLong(request.getParameter("idf"));
            char tipo = request.getParameter("tipo").charAt(0);
            try {
                Cliente cliente = dao.retrieve(idf, tipo);
                request.setAttribute("cliente", cliente);
                request.setAttribute("action", "update");
            } catch (DatabaseException ex) {
                Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("alert", "Erro ao buscar dados para edição");
            }
        } else if (action.equals("insert")) {

            forward = INSERT_OR_EDIT;
            request.setAttribute("action", "insert");
        } // Consulta IDF Cliente
        else if (action.equals("checkidf")) {
            response.setContentType("text/html;charset=UTF-8");
            try {
                Cliente cli = dao.retrieve(
                        Long.parseLong(request.getParameter("idf")),
                        request.getParameter("tipo").charAt(0));
                try (PrintWriter out = response.getWriter()) {
                    out.println(cli.getNome());
                }
            } catch (DatabaseException ex) {
                Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
                try (PrintWriter out = response.getWriter()) {
                    out.println("Cliente não encontrado");
                }
            }
            return;
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
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
//
        if (action.equals("insert")) {
            Cliente cliente = new Cliente();

            cliente.setIdf(
                    Long.parseLong(
                            request.getParameter("idf")
                                    .replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", ""))
            );
            cliente.setTipo(request.getParameter("tipo").charAt(0));
            cliente.setNome(request.getParameter("nome"));
            cliente.setEndereco(request.getParameter("endereco"));
            cliente.setFone(request.getParameter("fone"));
            cliente.setEmail(request.getParameter("email"));
            cliente.setDtcadastro(new Date());
            cliente.setStatus(Boolean.valueOf(request.getParameter("status")));

            try {
                dao.create(cliente);
                request.setAttribute("alert", "Usuário cadastrado com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao cadastrar cliente.");
                Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("update")) {
            try {
                Cliente cliente = dao.retrieve(
                        Long.parseLong(request.getParameter("idf")
                                .replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "")),
                        request.getParameter("tipo").charAt(0));

                cliente.setNome(request.getParameter("nome"));
                cliente.setEndereco(request.getParameter("endereco"));
                cliente.setFone(request.getParameter("fone"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setStatus(Boolean.valueOf(request.getParameter("status")));

                dao.update(cliente);
                request.setAttribute("alert", "Cliente atualizado com sucesso.");

            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao atualizar cliente.");
                Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        try {
            request.setAttribute("clientes", dao.getAll());
        } catch (DatabaseException ex) {
            request.setAttribute("alert", "Erro ao listar clientes");
            Logger.getLogger(CadClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.forward(request, response);
    }
}
