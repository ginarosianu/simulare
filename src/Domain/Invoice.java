package Domain;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice extends Entity {
    //private String id;
    double amount;
    String briefing;
    String date;

    public Invoice(String id, double amount, String briefing, String date) {
        super(id);
        this.amount = amount;
        this.briefing = briefing;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBriefing() {
        return briefing;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Invoice )) return false;
        if (!super.equals( o )) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare( invoice.getAmount(), getAmount() ) == 0 &&
                Objects.equals( getBriefing(), invoice.getBriefing() ) &&
                Objects.equals( getDate(), invoice.getDate() );
    }


    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), getAmount(), getBriefing(), getDate() );
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "amount=" + amount +
                ", briefing='" + briefing + '\'' +
                ", date='" + date + '\'' +
                ", id='" + getId() + '\'' +
                '}';
    }
}

