package name.aggibb.userlist;

import lombok.Data;

/**
 * Created by agibb on 2016-10-06.
 */
@Data
public class FilterText {
    private String filterText;

    public FilterText(){}

    public FilterText(String filterText) {
        this.filterText = filterText;
    }
}
