import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class DateComparator implements Comparator<Activity> {
    private SortDirection direction;
    public DateComparator(SortDirection direction) {
        this.direction = direction;
    }
    @Override
    public int compare(Activity a1, Activity a2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (direction == SortDirection.ASCENDING) {
                return dateFormat.parse(a1.getDate()).compareTo(dateFormat.parse(a2.getDate()));
            } else {
                return dateFormat.parse(a2.getDate()).compareTo(dateFormat.parse(a1.getDate()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    enum SortDirection {
        ASCENDING, DESCENDING
    }
}