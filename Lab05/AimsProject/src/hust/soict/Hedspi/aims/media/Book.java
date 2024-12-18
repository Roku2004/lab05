package hust.soict.Hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public Book() {

    }

    public Book(List<String> authors) {
        this.authors = authors;
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost); // Gọi constructor của Media
    }

    public Book(int nextId, String title, String category, float cost) {
        //TODO Auto-generated constructor stub
    }

    public Book(int nextId, String title) {
        //TODO Auto-generated constructor stub
    }

    public Book(int nextId, String title1, String category1, float cost1, String authorName1) {
        //TODO Auto-generated constructor stub
    }

    public Book(int i, String text, String text2, float float1, List<String> authors2) {
        //TODO Auto-generated constructor stub
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String authorName){
        try {
            for(String author : authors){
                if(authorName.equalsIgnoreCase(author)){
                    System.out.println("The author already exists.");
                }
                else{
                    authors.add(authorName);
                    System.out.println("Add author successfully.");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROL" + e);
        }
    }

    public void removeAuthor(String authorName){
        for(String author : authors){
            if(authorName.equalsIgnoreCase(author)){
                authors.remove(authorName);
                System.out.println("Successfully deleted author.");
            }
            else{
                System.out.println("Author does not exist.");
            }
        }
    }
}
