package hust.soict.Hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class TestPolymorphism {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<>();
        mediae.add(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 120, 24.95f));
        mediae.add(new CompactDisc("Thriller", "Pop", 30.99f, "Michael Jackson"));
        mediae.add(new Book("Harry Potter", "Fantasy", 19.95f));

        // Duyệt qua danh sách và gọi phương thức toString() để in thông tin
        for (Media media : mediae) {
            System.out.println(media.toString());  // Gọi phương thức toString() của từng đối tượng
        }
    }
}
