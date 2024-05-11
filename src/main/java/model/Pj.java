package model;

public abstract class Pj {
    protected String name;
    protected int id_foto;

    public Pj(String name, int id_foto) {
        this.name = name;
        this.id_foto = id_foto;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPhotos(int id_foto) { this.id_foto = id_foto; }
    public int getPhotos() { return id_foto; }
}
