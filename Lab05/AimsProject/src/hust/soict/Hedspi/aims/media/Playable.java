package hust.soict.Hedspi.aims.media;

import hust.soict.Hedspi.aims.exception.PlayerException;

public interface Playable {
    public void play();

    public String playMedia() throws PlayerException;
}
