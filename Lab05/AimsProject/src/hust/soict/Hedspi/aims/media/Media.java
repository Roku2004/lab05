package hust.soict.Hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    private int nbDigitalVideoDisc = 0;
    
    
    public Media() {

    }

    public Media(String title, String category, float cost) {
        this.id =++ nbDigitalVideoDisc;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public float getCost() {
        return cost;
    }


    public void setCost(float cost) {
        this.cost = cost;
    }

    public String toString(){
        return id + ".DVD - " +
        title + " - " +
        category+ "- " +
        cost + "$";
    }

    @Override
    public boolean equals(Object o) throws NullPointerException, ClassCastException{
		if (o != null) {
			if (o instanceof Media) {
				Media media = (Media) o;
				return (this.getTitle().equals(media.getTitle()) && (this.getCost() == media.getCost()));
			}
			else {
				throw new ClassCastException();
			}
		}
		else {
			throw new NullPointerException();
		}
	}

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    
}
