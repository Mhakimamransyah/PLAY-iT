package id.ac.unsri.ilkom.kelompok6students.cafetune.Entity;

/**
 * Created by hp on 11/11/2017.
 */

public class Music {
    String judul;
    String tahun;
    String artist;
    String album;

    int req;


    String genre;
    int id_music;
    boolean isRequested;


    public Music(String njudul, String ntahun, String nartist, String nalbum, String ngenre,int nid_music,int nReq){
        this.judul = njudul;
        this.tahun = ntahun;
        this.artist = nartist;
        this.album = nalbum;
        this.id_music = nid_music;
        this.genre = ngenre;
        this.req = nReq;
        this.isRequested = false;
    }

    public void setReq(int req) {
        this.req = req;
    }

    public int getReq() {
        return req;
    }

    public int getId_music() {
        return id_music;
    }

    public String getGenre() {
        return genre;
    }

    public String getJudul() {
        return judul;
    }

    public String getTahun() {
        return tahun;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public boolean isRequested() {
        return isRequested;
    }

    public void setRequested(boolean requested) {
        isRequested = requested;
    }

}
