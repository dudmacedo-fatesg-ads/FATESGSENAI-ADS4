package br.com.eduardo.floricultura.dal;

import br.com.eduardo.floricultura.model.Cliente;
import br.com.eduardo.floricultura.util.DBFactory;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.sql.Connection;
import java.sql.Date;
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
        String sql = String.format(
                "INSERT INTO %s (idf, tipo, nome, endereco, fone, email, dtcadastro, status) "
                + "VALUES(?,?,?,?,?,?,?,?)", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, obj.getIdf());
            pstmt.setString(2, String.valueOf(obj.getTipo()));
            pstmt.setString(3, obj.getNome());
            pstmt.setString(4, obj.getEndereco());
            pstmt.setString(5, obj.getFone());
            pstmt.setString(6, obj.getEmail());
            pstmt.setDate(7, new Date(obj.getDtcadastro().getTime()));
            pstmt.setBoolean(8, obj.getStatus());

            pstmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    public Cliente retrieve(long key, char tipo) throws DatabaseException {
        String sql = String.format("SELECT * FROM %s WHERE idf = ? AND tipo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, key);
            pstmt.setString(2, String.valueOf(tipo));

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Cliente ret = new Cliente();

                ret.setIdf(rs.getLong("idf"));
                ret.setTipo(rs.getString("tipo").charAt(0));
                ret.setNome(rs.getString("nome"));
                ret.setEndereco(rs.getString("endereco"));
                ret.setFone(rs.getString("fone"));
                ret.setEmail(rs.getString("email"));
                ret.setDtcadastro(new Date(rs.getDate("dtcadastro").getTime()));
                ret.setStatus(rs.getBoolean("status"));

                return ret;
            } else {
                throw new DatabaseException(null, "Não existe nenhum registro com a chave informada");
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Erro ao consultar registro");
        }
    }

    @Override
    public Cliente retrieve(Object key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente obj) throws DatabaseException {
        String sql = String.format(
                "UPDATE %s SET "
                + "(nome, endereco, fone, email, status) = "
                + "(?,?,?,?,?) "
                + "WHERE idf = ? AND tipo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getEndereco());
            pstmt.setString(3, obj.getFone());
            pstmt.setString(4, obj.getEmail());
            pstmt.setBoolean(5, obj.getStatus());
            
            pstmt.setLong(6, obj.getIdf());
            pstmt.setString(7, String.valueOf(obj.getTipo()));

            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao atualizar registro");
        }
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
