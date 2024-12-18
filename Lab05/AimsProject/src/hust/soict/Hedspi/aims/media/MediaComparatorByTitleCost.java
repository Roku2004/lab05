package hust.soict.Hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh title trước
        if (m1.getTitle() != null && m2.getTitle() != null) {
            int titleCompare = m1.getTitle().compareToIgnoreCase(m2.getTitle());
            if (titleCompare != 0) {
                return titleCompare;
            }
            // Nếu title giống nhau, sắp xếp theo cost giảm dần
            if (m1.getCost() > m2.getCost()) return -1;
            if (m1.getCost() < m2.getCost()) return 1;
            return 0;
        }
        
        // Xử lý các trường hợp title null
        if (m1.getTitle() == null && m2.getTitle() != null)
            return 1;  // null xếp sau
        if (m1.getTitle() != null && m2.getTitle() == null)
            return -1; // null xếp sau
        
        // Cả hai title đều null, so sánh theo cost
        if (m1.getCost() > m2.getCost()) return -1;
        if (m1.getCost() < m2.getCost()) return 1;
        return 0;
    }
}
