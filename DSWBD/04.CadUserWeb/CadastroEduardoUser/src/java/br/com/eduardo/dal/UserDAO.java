package br.com.eduardo.dal;

import br.com.eduardo.error.DatabaseException;
import br.com.eduardo.model.EUser;
import br.com.eduardo.util.DBFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author eduardo
 */
public class UserDAO implements EntityDAO<EUser> {

    private Connection cnx;

    public UserDAO() {
        cnx = DBFactory.getConnection();
    }

    @Override
    public String getTabela() {
        return "eduardo";
    }

    @Override
    public void create(EUser obj) throws DatabaseException {
        String sql = String.format(
                "INSERT INTO %s "
                + "(eduardocpf, eduardodatacadastro, eduardonome, eduardoendereco) "
                + "VALUES(?,?,?,?)", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, obj.getEduardocpf());
            pstmt.setDate(2, (Date) obj.getEduardodatacadastro());
            pstmt.setString(3, obj.getEduardonome());
            pstmt.setString(4, obj.getEduardoendereco());

            pstmt.execute();
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    @Override
    public EUser retrieve(EUser obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(EUser obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(EUser obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
