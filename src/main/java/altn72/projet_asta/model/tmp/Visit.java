package altn72.projet_asta.model.tmp;

import java.util.Date;

public class Visit {
    enum VisitFormat {
        VISIO,
        IN_PERSON
    }

    private Date date;
    private VisitFormat format;
    private String comments;
}
