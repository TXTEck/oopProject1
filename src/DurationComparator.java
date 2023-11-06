import java.util.Comparator;

public class DurationComparator implements Comparator<Activity> {
    private SortDirection direction;

    public DurationComparator(SortDirection direction) {
        this.direction = direction;
    }

    @Override
    public int compare(Activity a1, Activity a2) {
        if (direction == SortDirection.ASCENDING) {
            if (a1.getDuration() < a2.getDuration()) {
                return -1;
            } else if (a1.getDuration() > a2.getDuration()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            // For DESCENDING direction
            if (a1.getDuration() < a2.getDuration()) {
                return 1;
            } else if (a1.getDuration() > a2.getDuration()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    enum SortDirection {
        ASCENDING, DESCENDING
    }
}