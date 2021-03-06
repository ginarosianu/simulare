package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InvoiceValidator implements IValidator <Invoice>{
    public void validate (Invoice invoice){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            format.parse(invoice.getDate());
        } catch (ParseException pe) {
            throw new RuntimeException("The invoice date is not in a correct format!");
        }
    }
}
