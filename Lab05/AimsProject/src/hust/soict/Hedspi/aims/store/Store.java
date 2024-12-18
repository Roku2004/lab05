package hust.soict.Hedspi.aims.store;
import java.util.ArrayList;

import hust.soict.Hedspi.aims.media.Media;

public class Store {
    public ArrayList<Media> itemsInStore = new ArrayList<>();
    private static final int MAX_ITEMS_IN_STORE = 100;

    public ArrayList<Media> getItemsInStore(){
            return itemsInStore;
    }
    public void printStorelist(){
        System.out.println("\n--- Store ---\n");
        try {
            for(Media media : itemsInStore){
                System.out.println(media.toString() );
            }
        } catch (Exception e) {
        }
        System.out.println("-------------");
    }

    public void addMedia(Media media) {
        try {
            if (media == null) {
                throw new NullPointerException("Media cannot be null!");
            }
            
            if (itemsInStore.size() >= MAX_ITEMS_IN_STORE) {
                throw new IllegalStateException("Store full!");
            }
            
            // Kiểm tra media đã tồn tại chưa
            if (itemsInStore.contains(media)) {
                throw new IllegalArgumentException("Media not in store!");
            }
            
            itemsInStore.add(media);
            System.out.println("Added media \"" + media.getTitle() + "\" to store.");
            
        } catch (Exception e) {
            System.out.println("ERROL: " + e.getMessage());
        }
    }

    public void removeMedia(Media media) {
        try {
            if (media == null) {
                throw new NullPointerException("Media cannot be null!");
            }
            
            if (itemsInStore.isEmpty()) {
                throw new IllegalStateException("Store null!");
            }
            
            if (itemsInStore.remove(media)) {
                System.out.println("Removed media \"" + media.getTitle() + "\" from store.");
            } else {
                throw new IllegalArgumentException("Media not found in store!");
            }
            
        } catch (Exception e) {
            System.out.println("ERROL: " + e.getMessage());
        }
    }

    public Media SearchById(int id) {
        for (Media media : itemsInStore) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public Media SearchBytitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle() != null && media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return media;
            }
        }
        return null;
    }

    public int getQtyInStore() {
        return itemsInStore.size();
    }
}
