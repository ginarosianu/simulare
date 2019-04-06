package UI;

import Domain.Invoice;
import Service.InvoiceService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    public TableView tableViewInvoices;
    public TableColumn tableColumnId;
    public TableColumn tableColumnAmount;
    public TableColumn tableColumnBriefing;
    public TableColumn tableColumnDate;
    public Button btnInvoiceAdd;

    private InvoiceService invoiceService;

    private ObservableList < Invoice > invoices = FXCollections.observableArrayList();
    public void setServices(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    FXML
    private void initialize() {

        Platform.runLater(() -> {
            tableColumnAmount.setCellFactory( TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            invoices.addAll(invoiceService.getAll());
            tableViewInvoices.setItems(invoices);
        });
    }
    public void editInvoiceDate(TableColumn.CellEditEvent cellEditEvent) {
        Invoice editedInvoice = (Invoice)cellEditEvent.getRowValue();
        try {
            String newDate = (String)cellEditEvent.getNewValue();
            invoiceService.insert(editedInvoice.getId(), editedInvoice.getAmount(), editedInvoice.getBriefing(), editedInvoice.getDate();
            editedInvoice.setDate(newDate);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewInvoices.refresh();
    }

    public void btnInvoiceAddClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("invoiceAdd.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle("Invoice add");
            stage.setScene(scene);
            stage.initModality( Modality.APPLICATION_MODAL);
            InvoiceAddController controller =  fxmlLoader.getController();
            controller.setService(invoiceService);
            stage.showAndWait();
            invoices.clear();
            invoices.addAll(invoiceService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log( Level.SEVERE, "Failed to create new Window: Cake add.", e);
        }
    }

    public void editInvoiceAmount(TableColumn.CellEditEvent cellEditEvent) {
        Invoice editedInvoice = (Invoice)cellEditEvent.getRowValue();
        try {
            double newAmount = (double)cellEditEvent.getNewValue();
            invoiceService.insert(editedInvoice.getId(), editedInvoice.getAmount(), editedInvoice.getBriefing(), editedInvoice.getDate());
            editedInvoice.setAmount(newAmount);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewInvoices.refresh();
    }
}
