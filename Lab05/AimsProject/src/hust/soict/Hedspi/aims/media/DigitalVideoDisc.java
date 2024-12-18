package hust.soict.Hedspi.aims.media;

import hust.soict.Hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
    private String director;
    private int length;
    private Media media;
    public DigitalVideoDisc(){
        
    }

    public String getDirector() {
        return director;
    }
    public int getLength() {
        return length;
    }
    

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, director, length);  // Gọi constructor của Disc
    }

    // Constructor với 3 tham số: title, category, cost
    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost, "", 0); // Gọi constructor của Disc và truyền giá trị mặc định cho director và length
    }

    // Constructor với 1 tham số: title
    public DigitalVideoDisc(String title) {
        super(title, "", 0, "", 0); // Gọi constructor của Disc và truyền giá trị mặc định
    }
    public DigitalVideoDisc(int i, String text, String text2, float float1) {
        //TODO Auto-generated constructor stub
    }

    public DigitalVideoDisc(int i, String string, String string2, float f, String string3, int j) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());

    }

    public String playMedia() throws PlayerException{
		if (this.getLength() > 0) {
			String out = "Playing DVD: " + this.getTitle() + "\n" + "DVD length: " + this.getLength();
			return out;
		}else {
			throw new PlayerException("ERROR: DVD length is non-positive!");
		}
	}
    
}
