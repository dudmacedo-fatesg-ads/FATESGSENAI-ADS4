package br.com.eduardo.floricultura.dal;

import br.com.eduardo.floricultura.model.Produto;
import br.com.eduardo.floricultura.model.TipoProduto;
import br.com.eduardo.floricultura.util.DBFactory;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class ProdutoDAO implements EntityDAO<Produto> {

    private final Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "produto";
    }

    @Override
    public void create(Produto obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produto retrieve(Object key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Produto obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Produto obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> getAll() throws DatabaseException {
        String sql = String.format("SELECT * FROM %s", getTabela());
        List<Produto> lista = new ArrayList<>();

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto ret = new Produto();

                ret.setCodigo(rs.getLong("codigo"));
                ret.setNome(rs.getString("nome"));
                ret.setTipo(TipoProduto.getById(rs.getInt("tipo")));
                ret.setDescricao(rs.getString("descricao"));
                ret.setDtcadastro(rs.getDate("dtcadastro"));
                ret.setQuantidade(rs.getDouble("quantidade"));
                ret.setValor(rs.getDouble("valor"));
                ret.setStatus(rs.getBoolean("status"));

                lista.add(ret);
            }

        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Erro ao consultar registros");
        }

        return lista;
    }

}
