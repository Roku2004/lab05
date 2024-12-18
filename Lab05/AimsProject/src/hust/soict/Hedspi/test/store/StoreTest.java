package hust.soict.Hedspi.test.store;
import hust.soict.Hedspi.aims.media.Book;
import hust.soict.Hedspi.aims.media.CompactDisc;
import hust.soict.Hedspi.aims.media.DigitalVideoDisc;
import hust.soict.Hedspi.aims.media.Media;
import hust.soict.Hedspi.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        // Khởi tạo store
        Store store = new Store();
        
        // Test thêm media
        System.out.println("=== Test thêm media ===");
        
        // Tạo các media để test với dữ liệu giả định
        Media dvd1 = new DigitalVideoDisc(
            "Test Film 1",
            "Action",
            "Director A",
            120,
            50.50f
        );
        
        Media dvd2 = new DigitalVideoDisc(
            "Test Film 2",
            "Comedy",
            "Director B",
            95,
            45.75f
        );
        
        Media dvd3 = new DigitalVideoDisc(
            "Test Film 3",
            "Drama",
            35.25f
        );

        // Tạo Book để test
        Media book1 = new Book();

        // Tạo CD để test
        Media cd1 = new CompactDisc(1, "Album 1", "Music", 15.99f, "Artist 1");
        
        // Test thêm media hợp lệ
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(book1);
        store.addMedia(cd1);
        
        // Test thêm nhiều media cùng lúc
        System.out.println("\nTest thêm nhiều media cùng lúc:");
        Media dvd4 = new DigitalVideoDisc("Test Film 4", "Action", 29.99f);
        Media dvd5 = new DigitalVideoDisc("Test Film 5", "Drama", 39.99f);
        store.addMedia(dvd4);
        store.addMedia(dvd5);
        
        // Test thêm media null
        System.out.println("\nTest thêm media null:");
        store.addMedia((Media)null);
        
        // Test thêm media đã tồn tại
        System.out.println("\nTest thêm media đã tồn tại:");
        store.addMedia(dvd1);
        
        // In danh sách store sau khi thêm
        System.out.println("\nStore sau khi thêm:");
        store.printStorelist();
        
        // Test xóa media
        System.out.println("\n=== Test xóa media ===");
        
        // Test xóa media hợp lệ
        store.removeMedia(dvd2);
        
        // Test xóa media không tồn tại
        System.out.println("\nTest xóa media không tồn tại:");
        Media dvd6 = new DigitalVideoDisc("Test Film 6");
        store.removeMedia(dvd6);
        
        // Test xóa media null
        System.out.println("\nTest xóa media null:");
        store.removeMedia(null);
        
        // In danh sách store sau khi xóa
        System.out.println("\nStore sau khi xóa:");
        store.printStorelist();
    }
}
