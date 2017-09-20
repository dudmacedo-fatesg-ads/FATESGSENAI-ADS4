package br.com.eduardo.floricultura.dal;

import br.com.eduardo.floricultura.model.Cliente;
import br.com.eduardo.floricultura.util.DBFactory;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class ClienteDAO implements EntityDAO<Cliente> {

    private final Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "cliente";
    }

    @Override
    public void create(Cliente obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente retrieve(Object key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cliente obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> getAll() throws DatabaseException {
        String sql = String.format("SELECT * FROM %s", getTabela());
        List<Cliente> lista = new ArrayList<>();

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente ret = new Cliente();
                
                ret.setIdf(rs.getLong("idf"));
                ret.setTipo(rs.getString("tipo").charAt(0));
                ret.setNome(rs.getString("nome"));
                ret.setEndereco(rs.getString("endereco"));
                ret.setFone(rs.getString("fone"));
                ret.setEmail(rs.getString("email"));
                ret.setDtcadastro(rs.getDate("dtcadastro"));
                ret.setStatus(rs.getBoolean("status"));

                lista.add(ret);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao consultar registros");
            
        }

        return lista;
    }
}
