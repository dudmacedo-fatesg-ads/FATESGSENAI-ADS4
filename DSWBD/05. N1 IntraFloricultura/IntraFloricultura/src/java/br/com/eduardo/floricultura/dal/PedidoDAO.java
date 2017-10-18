package br.com.eduardo.floricultura.dal;

import br.com.eduardo.floricultura.model.Cliente;
import br.com.eduardo.floricultura.model.ItemPedido;
import br.com.eduardo.floricultura.model.Pedido;
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
public class PedidoDAO implements EntityDAO<Pedido> {

    private final Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "pedido";
    }

    @Override
    public void create(Pedido obj) throws DatabaseException {
        String sql = String.format("INSERT INTO %s (cod_cliente, data) VALUES (?, ?) RETURNING codigo", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getCliente().getCodigo());
            pstmt.setDate(2, new java.sql.Date(obj.getData().getTime()));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj.setCodigo(rs.getInt("codigo"));
            }

            ItemPedidoDAO itempedido_dao = new ItemPedidoDAO();
            for (ItemPedido i : obj.getItens()) {
                i.setPedido(obj);

                itempedido_dao.create(i);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    @Override
    public Pedido retrieve(Object key) throws DatabaseException {
        String sql = String.format("SELECT * FROM %s WHERE codigo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, (int) key);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Pedido ret = new Pedido();

                ret.setCodigo(rs.getInt("codigo"));
                ret.setCliente(new Cliente(rs.getInt("cod_cliente")));
                ret.setData(new java.util.Date(rs.getDate("data").getTime()));
                ret.setItens(new ItemPedidoDAO().getByCodPedido(ret.getCodigo()));
                
                return ret;
            } else {
                throw new DatabaseException(null, "Não existe nenhum registro com a chave informada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao consultar registros");
        }
    }

    @Override
    public void update(Pedido obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Pedido obj) throws DatabaseException {
        ItemPedidoDAO itemdao = new ItemPedidoDAO();
        itemdao.deleteByPedido(obj);

        String sql = String.format(
                "DELETE FROM %s "
                + "WHERE codigo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, obj.getCodigo());

            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao excluir registro");
        }
    }

    @Override
    public List<Pedido> getAll() throws DatabaseException {
        String sql = String.format("SELECT * FROM %s", getTabela());
        List<Pedido> lista = new ArrayList<>();

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Pedido ret = new Pedido();

                ret.setCodigo(rs.getInt("codigo"));

                Cliente cli = new Cliente();
                cli.setCodigo(rs.getInt("cod_cliente"));
                ret.setCliente(cli);

                ret.setData(rs.getDate("data"));

                ret.setItens(new ItemPedidoDAO().getByCodPedido(ret.getCodigo()));

                lista.add(ret);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao consultar registros");

        }

        return lista;
    }

}
