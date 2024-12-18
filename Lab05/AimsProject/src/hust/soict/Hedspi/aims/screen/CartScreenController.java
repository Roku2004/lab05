package hust.soict.Hedspi.aims.screen;

import javafx.scene.control.Label;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import hust.soict.Hedspi.aims.cart.Cart;
import hust.soict.Hedspi.aims.exception.PlayerException;
import hust.soict.Hedspi.aims.media.Media;
import hust.soict.Hedspi.aims.media.Playable;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
    private Cart cart;
    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Label costLabel;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
        updateCostLabel();
    }

    @FXML
    private TextField tfFilter;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart;
    }

    @FXML
    private void initialize() {

        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        tblMedia.setItems(this.cart.getItemsOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {

                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                });
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    void showFilteredMedia(String newValue) {
        FilteredList<Media> filteredData = new FilteredList<>(cart.getItemsOrdered());
        if (((RadioButton) filterCategory.getSelectedToggle()).getText().equals(radioBtnFilterTitle.getText())) {
            filteredData.setPredicate(media -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase().trim();
                if (media.getTitle().toLowerCase().trim().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        } else {
            filteredData.setPredicate(media -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseId = newValue.toLowerCase();
                if (String.valueOf(media.getId()).toLowerCase().indexOf(lowerCaseId.trim()) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        }
        SortedList<Media> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblMedia.comparatorProperty());
        tblMedia.setItems(sortedData);
    }

    private void updateCostLabel() {
        float cost = 0;
        for (Media media : cart.getItemsOrdered()) {
            cost += media.getCost();
        }
        costLabel.setText("" + String.format("%.2f$", cost));
    }

    private void updateBtnPlaceOrder() {
        if (cart.getItemsOrdered().size() == 0) {
            btnPlaceOrder.setDisable(true);
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Place order");
        alert.setHeaderText("Do you want to place order? ");
        alert.setContentText(cart.PrintCart());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setTitle("Place Order ");
            alert1.setHeaderText("Status: ");
            alert1.setContentText("Success");
            alert1.showAndWait();
            cart.emptyCart();
            btnPlaceOrder.setDisable(true);
            updateBtnPlaceOrder();
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) throws PlayerException {
        try {
            Media media = tblMedia.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Play media");
            alert.setHeaderText("Playing " + media.getTitle());

            if (media instanceof Playable) {
                alert.setContentText(((Playable) media).playMedia());
                alert.showAndWait();
            } else {
                throw new ClassCastException("This media is not playable!");
            }
        } catch (ClassCastException e) {
            Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(e.getMessage());
            alert1.showAndWait();
        }
    }
}
