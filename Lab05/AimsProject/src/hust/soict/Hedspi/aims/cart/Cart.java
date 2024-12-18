package hust.soict.Hedspi.aims.cart;

import java.util.Collections;

import hust.soict.Hedspi.aims.media.Media;
import hust.soict.Hedspi.aims.media.Playable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
	private String deliDest = new String("null");
	private float deliFee = 20;
	private String orderState = new String("null");
	private Media freeDVD = null;

	public float totalCost() {
		float total = 0;
		for (Media media : itemsOrdered) {
			if (media != null) {
				total += media.getCost();
			}
		}
		return total;
	}

	public void emptyCart() {
		itemsOrdered.clear();
	}

	public ObservableList<Media> getItemsOrdered() {
		return itemsOrdered;
	}

	// print the list of ordered items of a cart
	public String PrintCart() {
		String info = "";
		if (itemsOrdered.isEmpty()) {
			info = "The cart is empty";
		} else {
			info += ("***********************CART***********************\n");
			info += "Ordered Items:\n";
			for (Media item : itemsOrdered) {
				info += (item.toString()) + "\n";
			}
			info = info + "Total cost: " + totalCost();
			info += "***************************************************\n";
		}
		return info;
	}

	public void SearchById(int id) {
		boolean found = false;
		try {
			for (Media media : itemsOrdered) {
				if (media.getId() == id) {
					System.out.println("Found by id " + id + ": " + media.toString());
					found = true;
					break;
				}
			}
		} catch (NullPointerException e) {
		}
		if (!found) {
			System.out.println("No DVD found with by " + id);
		}
	}

	public void SearchBytitle(String title) {
		boolean found = false;
		try {
			for (Media media : itemsOrdered) {
				if (media.getTitle() == title) {
					System.out.println("Found by title " + title + ": " + media.toString());
					found = true;
					break;
				}
			}
		} catch (NullPointerException e) {
		}
		if (!found) {
			System.out.println("No DVD found with by " + title);
		}
	}

	public void addMedia(Media media) {
		if (media == null) {
			System.out.println("Media cannot be null!");
			return;
		}

		if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
			System.out.println("Cart full!");
			return;
		}

		itemsOrdered.add(media);
		System.out.println("Added media \"" + media.getTitle() + "\" to cart.");

		if (itemsOrdered.size() >= 15) {
			System.out.println("Warning:Cart full!");
		}
	}

	public void removeMedia(Media media) {
		if (media == null) {
			System.out.println("Media cannot be null!");
			return;
		}

		if (itemsOrdered.remove(media)) {
			System.out.println("Removed media \"" + media.getTitle() + "\" from cart.");
		} else {
			System.out.println("Media \"" + media.getTitle() + "\" not in cart!");
		}
	}

	public void sortCartbyTitle() {
		if (!itemsOrdered.isEmpty()) {
			Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
			System.out.println("Sorted by Title");
		} else {
			System.out.println("Cart is empty!");
		}
	}

	public void sortCartbyCost() {
		if (!itemsOrdered.isEmpty()) {
			Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
			System.out.println("Sorted by Cost");
		} else {
			System.out.println("Cart is empty!");
		}
	}

	public void placeOrder() {
		if (itemsOrdered.isEmpty()) {
			System.out.println("Cart null!");
			return;
		}
		orderState = "pending";
		System.out.println("The order has been created!");
		System.out.println("Status: " + orderState);

		// Xóa tất cả media khỏi giỏ hàng
		itemsOrdered.clear();
		System.out.println("All media removed from cart.");

		// Reset các thông tin khác
		deliDest = "null";
		deliFee = 20;
		freeDVD = null;
	}

	public void showCart() {
		if (!itemsOrdered.isEmpty()) {
			System.out.println("\n================ SHOPPING CART ================");
			System.out.printf("%-4s %-4s %-20s %-15s %-15s %-10s %-10s%n",
					"STT", "ID", "Title", "Category", "Director", "Length", "Price");

			for (int i = 0; i < itemsOrdered.size(); i++) {
				Media media = itemsOrdered.get(i);
				String priceDisplay = media.getCost() == 0 ? "FREE!" : String.format("$%.2f", media.getCost());
				System.out.printf("%-4d %-4d %-20s %-15s %-15s %-10s %-10s%n",
						(i + 1),
						media.getId(),
						truncateString(media.getTitle(), 18),
						truncateString(media.getCategory(), 13),
						"N/A", // Director is not available in Media class
						"N/A", // Length is not available in Media class
						priceDisplay);
			}

			System.out.println("----------------------------------------------");
			System.out.printf("Total cost: $%.2f%n", totalCost());

			if (freeDVD != null) {
				System.out.printf("Media raised: %s (Original price: $%.2f)%n",
						freeDVD.getTitle(),
						freeDVD.getCost());
			}

			if (!orderState.equals("null")) {
				System.out.printf("Status cart: %s%n", orderState.toUpperCase());
			}

			if (!deliDest.equals("null")) {
				System.out.printf("Delivery address: %s%n", deliDest);
			}
			System.out.println("=============================================\n");
		} else {
			System.out.println("\n================ SHOPPING CART ================");
			System.out.println("Cart NULL!");
			System.out.println("=============================================\n");
		}
	}

	private String truncateString(String str, int length) {
		if (str == null)
			return "";
		if (str.length() <= length)
			return str;
		return str.substring(0, length - 2) + "..";
	}

	public void playMedia(String title) {
		Media media = null;
		for (int i = 0; i < itemsOrdered.size(); i++) {
			if (itemsOrdered.get(i).getTitle().equals(title)) {
				media = itemsOrdered.get(i);
				break;
			}
		}
		if (media instanceof Playable) {
			try {
				((Playable) media).play();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}