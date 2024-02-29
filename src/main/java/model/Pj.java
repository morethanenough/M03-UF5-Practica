package model;

public abstract class Pj {
    protected String name;
    protected String photos;

    public Pj(String name, String photos) {
        this.name = name;
        this.photos = photos;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPhotos(String photos) { this.photos = photos; }
    public String getPhotos() { return photos; }
}
