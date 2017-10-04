package br.com.eduardo.floricultura.dal;

import br.com.eduardo.floricultura.model.Produto;
import br.com.eduardo.floricultura.model.TipoProduto;
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
public class ProdutoDAO implements EntityDAO<Produto> {

    private final Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "produto";
    }

    @Override
    public void create(Produto obj) throws DatabaseException {
        String sql = String.format(
                "INSERT INTO %s (codigo, nome, tipo, descricao, dtcadastro, unidade, quantidade, valor, status) "
                + "VALUES(?,?,?,?,?,?,?,?,?)", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, obj.getCodigo());
            pstmt.setString(2, obj.getNome());
            pstmt.setInt(3, obj.getTipo().getId());
            pstmt.setString(4, obj.getDescricao());
            pstmt.setDate(5, new Date(obj.getDtcadastro().getTime()));
            pstmt.setString(6, obj.getUnidade());
            pstmt.setDouble(7, obj.getQuantidade());
            pstmt.setDouble(8, obj.getValor());
            pstmt.setBoolean(9, obj.getStatus());

            pstmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    @Override
    public Produto retrieve(Object key) throws DatabaseException {
        String sql = String.format("SELECT * FROM %s WHERE codigo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, (long) key);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Produto ret = new Produto();

                ret.setCodigo(rs.getLong("codigo"));
                ret.setNome(rs.getString("nome"));
                ret.setTipo(TipoProduto.getById(rs.getInt("tipo")));
                ret.setDescricao(rs.getString("descricao"));
                ret.setDtcadastro(new java.util.Date(rs.getDate("dtcadastro").getTime()));
                ret.setUnidade(rs.getString("unidade"));
                ret.setQuantidade(rs.getDouble("quantidade"));
                ret.setValor(rs.getDouble("valor"));
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
    public void update(Produto obj) throws DatabaseException {
        String sql = String.format(
                "UPDATE %s SET "
                + "(nome, tipo, descricao, dtcadastro, unidade, quantidade, valor, status) = "
                + "(?,?,?,?,?,?,?,?) "
                + "WHERE codigo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setString(1, obj.getNome());
            pstmt.setInt(2, obj.getTipo().getId());
            pstmt.setString(3, obj.getDescricao());
            pstmt.setDate(4, new Date(obj.getDtcadastro().getTime()));
            pstmt.setString(5, obj.getUnidade());
            pstmt.setDouble(6, obj.getQuantidade());
            pstmt.setDouble(7, obj.getValor());
            pstmt.setBoolean(8, obj.getStatus());
            
            pstmt.setLong(9, obj.getCodigo());

            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao atualizar registro");
        }
    }

    @Override
    public void delete(Produto obj) throws DatabaseException {
        String sql = String.format(
                "DELETE FROM %s "
                + "WHERE codigo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, obj.getCodigo());

            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao excluir registro");
        }
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
                ret.setDtcadastro(new java.util.Date(rs.getDate("dtcadastro").getTime()));
                ret.setUnidade(rs.getString("unidade"));
                ret.setQuantidade(rs.getDouble("quantidade"));
                ret.setValor(rs.getDouble("valor"));
                ret.setStatus(rs.getBoolean("status"));

                lista.add(ret);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao consultar registros");
        }

        return lista;
    }
}
