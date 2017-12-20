package id.ac.unsri.ilkom.kelompok6students.cafetune;

/**
 * Created by hp on 26/09/2017.
 */

public class Model {
    String judul;
    String desc;

    public Model(String judul,String desc){
        this.setJudul(judul);
        this.setDesc(desc);
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
