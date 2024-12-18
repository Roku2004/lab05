package hust.soict.Hedspi.aims.media;

public class Disc extends Media {
    private int length;
    private String director;
    
    public Disc() {

    }

    public Disc(String title, String category, float cost, String director, int length) {
        super(title, category, cost); // Gọi constructor của Media
        this.director = director;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }

    
}
