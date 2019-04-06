package Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface IRepository<T extends Entity > {
    T findById (String id);
    void insert (T invoice);
    List <T> getAll();

}
