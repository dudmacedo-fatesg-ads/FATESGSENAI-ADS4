package br.com.eduardo.floricultura.dal;

import br.com.eduardo.floricultura.model.ItemPedido;
import br.com.eduardo.floricultura.model.Pedido;
import br.com.eduardo.floricultura.model.Produto;
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
public class ItemPedidoDAO implements EntityDAO<ItemPedido> {

    private final Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "itempedido";
    }
    
    @Override
    public void create(ItemPedido obj) throws DatabaseException {
        String sql = String.format(
                "INSERT INTO %s (cod_pedido, cod_produto, quantidade, valor) "
                + "VALUES(?,?,?,?)", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getPedido().getCodigo());
            pstmt.setLong(2, obj.getProduto().getCodigo());
            pstmt.setDouble(3, obj.getQuantidade());
            pstmt.setDouble(4, obj.getValor());

            pstmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    @Override
    public ItemPedido retrieve(Object key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ItemPedido obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ItemPedido obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void deleteByPedido(Pedido obj) throws DatabaseException {
        String sql = String.format(
                "DELETE FROM %s "
                + "WHERE cod_pedido = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getCodigo());

            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao excluir registro");
        }
    }

    @Override
    public List<ItemPedido> getAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<ItemPedido> getByCodPedido(int cod_pedido) throws DatabaseException {
        String sql = String.format("SELECT * FROM %s WHERE cod_pedido = ?", getTabela());
        List<ItemPedido> lista = new ArrayList<>();

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, cod_pedido);
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ItemPedido ret = new ItemPedido();
                
                Produto prod = new Produto();
                prod.setCodigo(rs.getLong("cod_produto"));
                ret.setProduto(prod);
                
                ret.setQuantidade(rs.getDouble("quantidade"));
                ret.setValor(rs.getDouble("valor"));

                lista.add(ret);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao consultar registros");

        }

        return lista;
    }
}
