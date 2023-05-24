package Model;

import java.util.Objects;

public class Consult {

    private int idConsult;

    int registrationNo;
    private int pret;

    private String dataConsult;

    public int getRegistrationNo() {
        return registrationNo;
    }

    public int getIdConsult() {
        return idConsult;
    }

    public int getPret() {
        return pret;
    }

    public String getDataConsult() {
        return dataConsult;
    }

    public void setRegistrationNo(int registrationNo) {
        this.registrationNo = registrationNo;
    }

    public void setDataConsult(String dataConsult) {
        this.dataConsult = dataConsult;
    }

    public void setIdConsult(int idConsult) {
        this.idConsult = idConsult;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public Consult() {
        this(0,0,0,"");
    }

    public Consult(int idConsult, int pret, String dataConsult) {
        this.idConsult = idConsult;
        this.pret = pret;
        this.dataConsult = dataConsult;
    }

    public Consult(int idConsult, int registrationNo, int pret, String dataConsult) {
        this.idConsult = idConsult;
        this.registrationNo = registrationNo;
        this.pret = pret;
        this.dataConsult = dataConsult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consult consult = (Consult) o;
        return idConsult == consult.idConsult && registrationNo==consult.registrationNo && pret == consult.pret && dataConsult.equals(consult.dataConsult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsult, pret, dataConsult);
    }

    @Override
    public String toString() {
        return "Consult{" +
                " idConsult=" + idConsult +
                ", registrationNo=" + registrationNo +
                ", pret=" + pret +
                ", dataConsult='" + dataConsult + '\'' +
                '}';
    }
}
