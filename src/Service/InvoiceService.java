package Service;

import Domain.Invoice;
import Repository.IRepository;

import java.util.List;

public class InvoiceService {
    private IRepository < Invoice > repository;

    public InvoiceService(IRepository < Invoice > repository) {
        this.repository = repository;
    }


    public void insert(String id, double amount, String briefing, String date) {
        Invoice invoice = new Invoice( id, amount, briefing, date );
        repository.insert( invoice );
    }

    public List <Invoice> getAll() {
        return repository.getAll();
    }
}