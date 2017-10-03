package br.com.eduardo.floricultura.model;

import br.com.eduardo.floricultura.util.Formatador;
import java.util.Date;

/**
 *
 * @author eduardo
 */
public class Fornecedor {

    public static final char PESSOA_JURIDICA = 'J';
    public static final char PESSOA_FISICA = 'F';

    private long idf;
    private char tipo;
    private String nome;
    private String endereco;
    private String fone;
    private String email;
    private Date dtcadastro;
    private boolean status;

    public String getIdf_formatado() {
        switch (tipo) {
            case PESSOA_FISICA:
                return Formatador.formataIdfPF(idf);
            case PESSOA_JURIDICA:
                return Formatador.formataIdfPJ(idf);
            default:
                return Long.toString(idf);
        }
    }

    public long getIdf() {
        return idf;
    }

    public void setIdf(long idf) {
        this.idf = idf;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
