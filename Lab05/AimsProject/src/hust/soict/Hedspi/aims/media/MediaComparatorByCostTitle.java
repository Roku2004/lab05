package hust.soict.Hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh cost giảm dần
        if (m1.getCost() > m2.getCost()) return -1;
        if (m1.getCost() < m2.getCost()) return 1;
        
        // Nếu cost bằng nhau, so sánh title
        if (m1.getTitle() != null && m2.getTitle() != null)
            return m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (m1.getTitle() == null && m2.getTitle() != null)
            return 1;  // null xếp sau
        if (m1.getTitle() != null && m2.getTitle() == null)
            return -1; // null xếp sau
        return 0;     // cả hai đều null
    }
}