package name.aggibb.userlist;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by agibb on 2016-10-06.
 */
@Data
public class FilterText {
    @NotEmpty
    private String nameText;

    public FilterText(){}

    public FilterText(String nameText) {
        this.nameText = nameText;
    }
}
