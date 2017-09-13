package br.com.eduardo.floricultura.model;

/**
 *
 * @author eduardo
 */
public enum TipoProduto {
    MUDA(1, "Muda"),
    BUQUE(2, "Buquê"),
    VASO(3, "Vaso");

    private final int id;
    private final String descricao;

    private TipoProduto(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static TipoProduto getById(int id) {
        for (TipoProduto t : TipoProduto.values()) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }
}
