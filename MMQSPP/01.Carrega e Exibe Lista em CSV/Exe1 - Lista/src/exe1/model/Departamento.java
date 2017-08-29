package exe1.model;

/**
 *
 * @author eduardo
 */
public enum Departamento {
    CMP,
    CBM,
    JUR,
    ENG,
    FIT,
    LET,
    MAF;

    public static Departamento getDepartamento(String departamento) {
        for (Departamento d : Departamento.values()) {
            if (d.toString().equals(departamento)) {
                return d;
            }
        }
        return null;
    }
}
