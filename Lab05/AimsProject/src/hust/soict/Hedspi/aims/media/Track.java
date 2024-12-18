package hust.soict.Hedspi.aims.media;

import java.util.Objects;

import hust.soict.Hedspi.aims.exception.PlayerException;

public class Track implements Playable{
    private String title;
    private int length;

    public Track() {

    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    @Override
	public int hashCode() {
		return Objects.hash(length, title);
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return title.equals(track.title) && length == track.length;
    }

    public String playMedia() throws PlayerException{
		if (this.getLength() > 0) {
			String out = "Playing track: " + this.getTitle() + "\n" + "Track length: " + this.getLength();
			return out;
		}else {
			throw new PlayerException("ERROR: DVD length is non-positive!");
		}
	}
}
