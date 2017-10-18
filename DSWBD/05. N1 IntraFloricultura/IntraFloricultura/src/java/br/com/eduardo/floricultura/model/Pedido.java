package br.com.eduardo.floricultura.model;

import br.com.eduardo.floricultura.dal.ClienteDAO;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class Pedido {

    private int codigo;
    private Cliente cliente;
    private Date data;
    private List<ItemPedido> itens;
    
    public Pedido() {
        
    }
    
    public Pedido(int codigo) {
        this.codigo = codigo;
    }
    
    public double getValortotal() {
        double total = 0;
        
        for (ItemPedido i : itens) {
            total += i.getQuantidade() * i.getValor();
        }
        
        return total;
    }
    
    public String getValortotal_formatado() {
        return NumberFormat.getCurrencyInstance().format(getValortotal());
    }
    
    public String getClientenome() {
        if (cliente.getNome() == null) {
            ClienteDAO clidao = new ClienteDAO();
            try {
                cliente = clidao.retrieve(cliente.getCodigo());
            } catch (DatabaseException ex) {
                Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente.getNome();
    }
    
    public Cliente getClienteobj() {
        if (cliente.getNome() == null) {
            ClienteDAO clidao = new ClienteDAO();
            try {
                cliente = clidao.retrieve(cliente.getCodigo());
            } catch (DatabaseException ex) {
                Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }
    
    // Getters e Setters padrão
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

}
