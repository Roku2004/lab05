package hust.soict.Hedspi.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.Hedspi.aims.cart.Cart;
import hust.soict.Hedspi.aims.media.Book;
import hust.soict.Hedspi.aims.media.CompactDisc;
import hust.soict.Hedspi.aims.media.DigitalVideoDisc;
import hust.soict.Hedspi.aims.media.Media;
import hust.soict.Hedspi.aims.media.Track;
import hust.soict.Hedspi.aims.store.Store;

public class Storescreen extends JFrame {
    private static final long serialVersionUID = -3913201726916721062L;
    private Store store;
    private Container cp;
    private Cart cart;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBook = new JMenuItem("Add Book");
        smUpdateStore.add(addBook);
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookStoreScreen(store).setVisible(true);
            }
        });

        JMenuItem addCD = new JMenuItem("Add CD");
        smUpdateStore.add(addCD);
        addCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCDStoreScreen(store).setVisible(true);
            }
        });

        JMenuItem addDVD = new JMenuItem("Add DVD");
        smUpdateStore.add(addDVD);
        addDVD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddDVDStoreScreen(store).setVisible(true);
            }
        });
        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        menu.add(new JMenuItem("View cart"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart1 = new JButton("View cart");
        cart1.setPreferredSize(new Dimension(100, 50));
        cart1.setMaximumSize(new Dimension(100, 50));
        cart1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(cart).setVisible(true);
            }
        });
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart1);
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));
        ArrayList<Media> mediaStore = store.getItemsInStore();
        for (Media media : mediaStore) {
            MediaStore cell = new MediaStore(media, cart);
            center.add(cell);
        }
        return center;
    }

    public Storescreen(Store store, Cart myCart) {
        this.store = store;
        this.cart = myCart;
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    private class AddDVDStoreScreen extends JFrame {
        private static final long serialVersionUID = 1L;

        public AddDVDStoreScreen(Store store) {
            this.setLayout(new GridLayout(4, 2, 5, 5));
            this.add(new JLabel("Enter title: "));
            TextField title = new TextField(10);
            this.add(title);
            this.add(new JLabel("Enter category: "));
            TextField category = new TextField(10);
            this.add(category);
            this.add(new JLabel("Enter cost: "));
            TextField cost = new TextField(10);
            this.add(cost);

            this.setTitle("Add DVD");
            this.setSize(300, 100);
            JButton turnInBtn = new JButton("Add");
            this.add(turnInBtn);
            turnInBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DigitalVideoDisc dvd = new DigitalVideoDisc(2, title.getText(), category.getText(),
                            Float.parseFloat(cost.getText()));
                    store.addMedia(dvd);
                    cp.add(createCenter(), BorderLayout.CENTER);
                    cp.revalidate();
                    title.setText("");
                    category.setText("");
                    cost.setText("");
                }
            });
            this.setVisible(true);
        }
    }

    private class AddCDStoreScreen extends JFrame {
        private static final long serialVersionUID = 1L;

        public AddCDStoreScreen(Store store) {
            this.setLayout(new GridLayout(7, 2, 5, 5));
            this.add(new JLabel("Enter title: "));
            TextField title = new TextField(10);
            this.add(title);
            this.add(new JLabel("Enter category: "));
            TextField category = new TextField(10);
            this.add(category);
            this.add(new JLabel("Enter cost: "));
            TextField cost = new TextField(10);
            this.add(cost);
            this.add(new JLabel("Enter artist: "));
            TextField artist = new TextField(10);
            this.add(artist);
            this.setTitle("Add CD");
            this.add(new JLabel("Number of tracks: "));
            TextField numberOfTracks = new TextField(10);
            this.add(numberOfTracks);
            this.pack();
            JButton turnInBtn = new JButton("Add");
            this.add(turnInBtn);
            turnInBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CompactDisc dvd = new CompactDisc(2, title.getText(), category.getText(),
                            Float.parseFloat(cost.getText()), artist.getText(), new ArrayList<Track>());
                    store.addMedia(dvd);
                    cp.add(createCenter(), BorderLayout.CENTER);
                    cp.revalidate();
                    title.setText("");
                    category.setText("");
                    cost.setText("");
                }
            });
            this.setVisible(true);
        }
    }

    private class AddBookStoreScreen extends JFrame {
        private static final long serialVersionUID = -145726792135069230L;

        public AddBookStoreScreen(Store store) {
            this.setLayout(new GridLayout(7, 2, 5, 5));
            this.add(new JLabel("Enter title: "));
            TextField title = new TextField(10);
            this.add(title);
            this.add(new JLabel("Enter category: "));
            TextField category = new TextField(10);
            this.add(category);
            this.add(new JLabel("Enter cost: "));
            TextField cost = new TextField(10);
            this.add(cost);
            this.add(new JLabel("Enter author (If multiple authors, use commas to fill in): "));
            TextField author = new TextField(10);
            this.add(author);
            this.setTitle("Add Book");
            this.pack();
            JButton turnInBtn = new JButton("Add");
            this.add(turnInBtn);
            turnInBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] authorArray = author.getText().split(",");
                    List<String> authors = new ArrayList<String>();

                    for (String a : authorArray) {
                        authors.add(a.trim());
                    }
                    Book book = new Book(4, title.getText(), category.getText(),
                            Float.parseFloat(cost.getText()), authors);
                    store.addMedia(book);
                    cp.add(createCenter(), BorderLayout.CENTER);
                    cp.revalidate();
                    title.setText("");
                    category.setText("");
                    cost.setText("");
                    author.setText(null);
                }
            });
            this.setVisible(true);
        }
    }

    public static void main(String[] args) {
        DigitalVideoDisc dvd = new DigitalVideoDisc(1, "Jurassic World", "Action", 30.75f,
                "Colin Trevorrow", 124);
        DigitalVideoDisc dvd0 = new DigitalVideoDisc(2, "The Tomorrow War", "Science Fiction", 30.75f,
                "Chris McKay", 138);

        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track("Like I'm gonna lose you", 4));
        tracks.add(new Track("Let me down slowly", 3));
        CompactDisc cd = new CompactDisc(2, "Ban nhac buon", "Nhac Au - My", 10f, "Various artists",
                tracks);

        ArrayList<Track> hsr = new ArrayList<Track>();
        hsr.add(new Track("Hope Is the Thing With Feathers", 4));
        hsr.add(new Track("Sway to My Beat in Cosmos", 3));
        hsr.add(new Track("If I Can Stop One Heart From Breaking", 3));
        hsr.add(new Track("Had I Not Seen the Sun", 2));
        hsr.add(new Track("Sway to My Beat in Cosmos (Instrumental)", 3));
        hsr.add(new Track("If I Can Stop One Heart from Breaking (Instrumental)", 3));
        hsr.add(new Track("Hope is the Thing with Feathers (Instrumental)", 4));
        hsr.add(new Track("Had I Not Seen the Sun (Instrumental)", 2));
        CompactDisc cd2 = new CompactDisc(3, "INSIDE", "Penacony music", 37.25f, "Robin", hsr);

        List<String> authors = new ArrayList<String>();
        authors.add("Fujiko F. Fujio");
        Book book = new Book(4, "Doraemon", "Manga", 0.79f, authors);

        List<String> authors2 = new ArrayList<String>();
        authors2.add("Tappei Nagatsuki");
        Book book2 = new Book(5, "Re:Zero kara Hajimeru Isekai Seikatsu", "Light novel", 21.01f,
                authors2);

        Store store = new Store();
        store.addMedia(cd);
        store.addMedia(cd2);
        store.addMedia(dvd);
        store.addMedia(dvd0);
        store.addMedia(book);
        store.addMedia(book2);

        Cart myCart = new Cart();
        new Storescreen(store, myCart);
    }
}