import java.util.Comparator;

public class DateComparator implements Comparator<Activity> {
    private SortDirection direction;
    public DateComparator(SortDirection direction) {
        this.direction = direction;
    }
    @Override
    public int compare(Activity a1, Activity a2) {
        if (direction == SortDirection.ASCENDING) {
            return a1.getDate().compareTo(a2.getDate());
        }
        else {
            return a2.getDate().compareTo(a1.getDate());
        }

    }
    enum SortDirection {
        ASCENDING, DESCENDING
    }
}


