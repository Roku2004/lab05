package hust.soict.Hedspi.aims;

import java.util.Scanner;

import hust.soict.Hedspi.aims.cart.Cart;
import hust.soict.Hedspi.aims.media.Book;
import hust.soict.Hedspi.aims.media.CompactDisc;
import hust.soict.Hedspi.aims.media.DigitalVideoDisc;
import hust.soict.Hedspi.aims.media.Media;
import hust.soict.Hedspi.aims.media.Playable;
import hust.soict.Hedspi.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    // Menu chính
    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    // Menu store
    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    // Menu chi tiết media
    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    // Menu cart
    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    // Xử lý menu chính
    public static void handleMainMenu() {
        while (true) {
            showMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1: handleStoreMenu(); break;
                case 2: handleUpdateStore(); break;
                case 3: handleCartMenu(); break;
                case 0:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    // Xử lý menu store
    private static void handleStoreMenu() {
        while (true) {
            store.printStorelist();
            storeMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1: handleSeeMediaDetails(); break;
                case 2: handleAddToCart(); break;
                case 3: handlePlayMedia(); break;
                case 4:
                    System.out.println("Options: ");
                    System.out.println("--------------------------------");
                    System.out.println("1. Filter medias in cart");
                    System.out.println("2. Sort medias in cart");
                    System.out.println("3. Remove media from cart");
                    System.out.println("4. Play a media");
                    System.out.println("5. Place order");
                    System.out.println("0. Back");
                    System.out.println("--------------------------------");
                    System.out.println("Please choose a number: 0-1-2-3-4-5");
                    
                    cart.showCart();
                    int cartChoice = getIntInput();
                    switch (cartChoice) {
                        case 1: handleFilterCart(); break;
                        case 2: handleSortCart(); break;
                        case 3: handleRemoveFromCart(); break;
                        case 4: handlePlayMedia(); break;
                        case 5:
                            cart.placeOrder();
                            return;
                        case 0: break;
                        default:
                            System.out.println("Invalid selection!");
                    }
                    break;
                case 0: return;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    // Xử lý menu cart
    private static void handleCartMenu() {
        while (true) {
            cart.showCart();
            cartMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1: handleFilterCart(); break;
                case 2: handleSortCart(); break;
                case 3: handleRemoveFromCart(); break;
                case 4: handlePlayMedia(); break;
                case 5:
                    cart.placeOrder();
                    return;
                case 0: return;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    // Các phương thức xử lý chi tiết
    private static void handleFilterCart() {
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by title");
        int choice = getIntInput();
        switch (choice) {
            case 1:
                System.out.print("Enter the ID: ");
                int id = getIntInput();
                cart.SearchById(id);
                break;
            case 2:
                System.out.print("Enter the title: ");
                String title = scanner.nextLine();
                cart.SearchBytitle(title);
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }

    private static void handleSortCart() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Sort medias in cart by title");
        System.out.println("2. Sort medias in cart by cost");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
        
        int choice = getIntInput();
        switch (choice) {
            case 1: cart.sortCartbyTitle(); break;
            case 2: cart.sortCartbyCost(); break;
            case 0: return;  // Thêm lựa chọn quay lại
            default:
                System.out.println("Invalid selection!");
        }
    }

    private static void handleUpdateStore() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
        
        int choice = getIntInput();
        switch (choice) {
            case 1: handleAddMediaToStore(); break;
            case 2: handleRemoveMediaFromStore(); break;
            case 0: return;  // Thêm lựa chọn quay lại
            default:
                System.out.println("Invalid selection!");
        }
    }

    // Các phương thức tiện ích
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Enter number!");
            return -1;
        }
    }

    private static Media findMediaByTitle(String title) {
        return store.SearchBytitle(title);
    }

    // Tìm media theo ID
    private static Media findMediaById(int id) {
        return store.SearchById(id);
    }

    // Xử lý xem chi tiết media
    private static void handleSeeMediaDetails() {
        System.out.print("Enter the id: ");
        int id = getIntInput();
        if (id == -1) return;  // Người dùng Enter không phải số
        
        Media media = findMediaById(id);
        if (media == null) {
            System.out.println("Media with ID not found: " + id);
            return;
        }

        System.out.println(media.toString());
        while (true) {
            mediaDetailsMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("Media này không thể phát!");
                    }
                    break;
                case 0: return;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    // Xử lý thêm media vào cart
    private static void handleAddToCart() {
        System.out.print("Enter the ID: ");
        int id = getIntInput();
        if (id == -1) return;
        
        Media media = findMediaById(id);
        if (media == null) {
            System.out.println("Media with ID not found: " + id);
            return;
        }
        
        cart.addMedia(media);
    }

    // Xử lý phát media
    private static void handlePlayMedia() {
        System.out.print("Enter the ID: ");
        int id = getIntInput();
        if (id == -1) return;
        
        Media media = findMediaById(id);
        if (media == null) {
            System.out.println("Media with ID not found: " + id);
            return;
        }
        
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Media not play!");
        }
    }

    // Xử lý xóa media khỏi cart
    private static void handleRemoveFromCart() {
        System.out.print("Enter the ID: ");
        int id = getIntInput();
        if (id == -1) return;
        
        Media media = findMediaById(id);
        if (media == null) {
            System.out.println("Media with ID not found: " + id);
            return;
        }
        
        cart.removeMedia(media);
    }

    // Xử lý thêm media vào store
    private static void handleAddMediaToStore() {
        System.out.println("Select media type:");
        System.out.println("1. Book");
        System.out.println("2. CD");
        System.out.println("3. DVD");
        int choice = getIntInput();
        
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter cost: ");
        float cost = Float.parseFloat(scanner.nextLine());
        
        Media media = null;
        int nextId = store.getQtyInStore() + 1;  // Lấy ID tiếp theo
        
        switch (choice) {
            case 1:
                Book book = new Book(nextId, title, category, cost);  // Sử dụng ID từ store
                System.out.print("Enter author name (Enter 'done' to end): ");
                String authorName;
                while (!(authorName = scanner.nextLine()).equalsIgnoreCase("done")) {
                    book.addAuthor(authorName);
                    System.out.print("Enter author name next (Enter 'done' to end): ");
                }
                media = book;
                break;
            case 2:
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                media = new CompactDisc(nextId, title, category, cost, artist);
                break;
            case 3:
                System.out.print("Enter director: ");
                String director = scanner.nextLine();
                System.out.print("Enter length: ");
                int length = Integer.parseInt(scanner.nextLine());
                media = new DigitalVideoDisc(title, category, director, length, cost);
                break;
            default:
                System.out.println("Invalid selection!");
                return;
        }
        
        store.addMedia(media);
    }

    // Xử lý xóa media khỏi store
    private static void handleRemoveMediaFromStore() {
        System.out.print("Enter the ID : ");
        int id = getIntInput();
        if (id == -1) return;
        
        Media media = findMediaById(id);
        if (media == null) {
            System.out.println("Media with ID not found: " + id);
            return;
        }
        
        store.removeMedia(media);
    }

    public static void main(String[] args) {
        // Khởi tạo một số media mẫu trong store
        // DVDs
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 121, 24.95f));
        store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 29.99f));
        store.addMedia(new DigitalVideoDisc("The Godfather", "Drama", "Francis Ford Coppola", 175, 22.99f));
        store.addMedia(new DigitalVideoDisc("Jurassic Park", "Adventure", "Steven Spielberg", 127, 21.50f));

        // CDs
        store.addMedia(new CompactDisc(6, "Greatest Hits", "Music", 29.99f, "Various Artists"));
        store.addMedia(new CompactDisc(7, "Thriller", "Pop", 19.99f, "Michael Jackson"));
        store.addMedia(new CompactDisc(8, "Back in Black", "Rock", 24.99f, "AC/DC"));
        store.addMedia(new CompactDisc(9, "21", "Pop", 18.99f, "Adele"));
        store.addMedia(new CompactDisc(10, "The Dark Side of the Moon", "Rock", 27.99f, "Pink Floyd"));

        // Books
        Book book1 = new Book(11, "Harry Potter", "Fantasy", 24.99f);
        book1.addAuthor("J.K. Rowling");
        store.addMedia(book1);

        Book book2 = new Book(12, "A Game of Thrones", "Fantasy", 29.99f);
        book2.addAuthor("George R.R. Martin");
        store.addMedia(book2);

        Book book3 = new Book(13, "The Shining", "Horror", 19.99f);
        book3.addAuthor("Stephen King");
        store.addMedia(book3);

        // Chạy chương trình
        handleMainMenu();
        
        // Đóng scanner khi kết thúc
        scanner.close();
    }
}
