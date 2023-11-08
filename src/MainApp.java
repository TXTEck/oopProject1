import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.List;

//Group Members Xu Teck Tan, Wiktor Teter
public class MainApp {
    public static void readFile(String filename, ArrayList<Activity> activities,boolean hasHeaders) throws IOException {
        File input = new File("activity_data_1000.csv");
        Scanner sc = new Scanner(input);
        String line;
        boolean headersRead = false;
        while (sc.hasNextLine())
        {
            line = sc.nextLine();
            if(!hasHeaders || hasHeaders && headersRead)
            {
                if(line != "") //makes sure the line is not empty
                {
                    Activity newActivity = parseLine(line);
                    activities.add(newActivity);
                }
            }
            else
            {
                headersRead = true;
            }
        }
    }

    public static Activity parseLine(String line)
    {
        String name;
        int duration;
        double distance;
        int AHR;
        String date;
        StringTokenizer st = new StringTokenizer(line,",");
        name = st.nextToken();
        date = st.nextToken();
        duration = Integer.parseInt(st.nextToken().trim());
        distance = Double.parseDouble(st.nextToken().trim());
        AHR = Integer.parseInt(st.nextToken().trim());

        if(name.equalsIgnoreCase("Swimming"))
        {
            return new Swimming(name, duration, distance, AHR, date);
        }
        else if (name.equalsIgnoreCase("Running"))
        {
            return new Running(name, duration, distance, AHR, date);
        }
        else
        {
            return new Cycling(name, duration, distance, AHR, date);
        }
    }
    public static void findDuplicate(ArrayList<Activity> activities)
    {

            for(int i = 0; i < activities.size(); i++)
            {
                for(int j = i+1; j < activities.size(); j++)
                {
                    if(activities.get(i).toString().equalsIgnoreCase(activities.get(j).toString()))
                    {
                    activities.remove(j);
                    j--;
                    }


                }
            }
    }

    public static void displayMenu()
    {
        int menuWidth = 35;
        for (int i = 0; i < menuWidth; i++) {
            System.out.print("~");
        }
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("1. View all activities");
        System.out.println("2. Search for activities");
        System.out.println("3. View average");
        System.out.println("4. View sorted data");
        System.out.println("5. Add new activity");
        for (int i = 0; i < menuWidth; i++) {
            System.out.print("~");
        }
        System.out.println();
    }

    public static void addActivity(ArrayList<Activity> activities)
    {
        Scanner keyboard = new Scanner(System.in);
        int menuWidth = 35;
        int choice;
        String name;
        int duration;
        double distance;
        int AHR;
        String date;
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
        System.out.println("Select Activity Type:");
        System.out.println("0. Go Back");
        System.out.println("1. Swimming");
        System.out.println("2. Running");
        System.out.println("3. Cycling");
        for (int i = 0; i < menuWidth; i++)
        {
            System.out.print("=");
        }
        System.out.println();
        choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 1)
        {
            name = "Swimming";
        }
        else if(choice == 2)
        {
            name = "Running";
        }
        else
        {
            name = "Cycling";
        }
        System.out.println("Enter duration: ");
        duration = keyboard.nextInt();
        System.out.println("Enter distance: (Km/h)");
        distance = keyboard.nextDouble();
        System.out.println("Enter AHR: ");
        AHR = keyboard.nextInt();
        System.out.println("Enter date: (dd/mm/yyyy)");
        date = keyboard.next();
        if (name.equalsIgnoreCase("Swimming"))
        {
            Swimming newActivity = new Swimming(name, duration, distance, AHR, date);
            activities.add(newActivity);
        }
        else if (name.equalsIgnoreCase("Running"))
        {
            Running newActivity = new Running(name, duration, distance, AHR, date);
            activities.add(newActivity);
        }
        else
        {
            Cycling newActivity = new Cycling(name, duration, distance, AHR, date);
            activities.add(newActivity);
        }
    }

    public static void searchFor(ArrayList<Activity> activities)
    {
        Scanner keyboard = new Scanner(System.in);
        int menuWidth = 35;
        int choice;
        ArrayList<Activity> activitiesFound = new ArrayList<>();
        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("1. Activity Type");
            System.out.println("2. Above a minimum distance");
            System.out.println("3. Type of energy expended");
            System.out.println("4. Above a minimum duration");
            System.out.println("0. Go Back");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();

            switch (choice) {
                case 0:
                    break;
                case 1:
                    typeMenu(activities);
                    break;
                case 2:
                    System.out.println("Enter minimum distance: ");
                    int minDist = keyboard.nextInt();
                    for (Activity a : activities) {
                        if (a.getDistance() >= minDist)
                        {
                            activitiesFound.add(a);
                        }
                    }
                    distanceCompareASC(activitiesFound);
                    for (Activity a : activitiesFound)
                    {
                        System.out.println(a);
                    }
                    break;
                case 3:
                    energyExpended(activities);
                    break;

                case 4:
                    System.out.println("Enter minimum duration: ");
                    int minDur = keyboard.nextInt();
                    for (Activity a : activities) {
                        if (a.getDuration() >= minDur) {
                            activitiesFound.add(a);
                        }
                    }
                    DurationComparator durationComparator = new DurationComparator(DurationComparator.SortDirection.ASCENDING);
                    Collections.sort(activitiesFound, durationComparator);
                    for (Activity a : activitiesFound)
                    {
                        System.out.println(a);
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    public static void energyExpended(ArrayList<Activity> activities)
    {
        int menuWidth = 35;
        Scanner keyboard = new Scanner(System.in);
        int choice;
        boolean activitiesFound = false;
        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("Select Intensity Level:");
            System.out.println("1. Very Light");
            System.out.println("2. Light");
            System.out.println("3. Moderate");
            System.out.println("4. Vigorous");
            System.out.println("5. Very Vigorous");
            System.out.println("0. Go Back");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();

            switch (choice) {
                case 0:
                    break;
                case 1:
                    for (Activity a : activities) {
                        if (a instanceof Swimming && ((Swimming) a).getEnergyExpended() == Swimming.INTENSITY.VeryLight)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Running && ((Running) a).getEnergyExpended() == Running.INTENSITY.VeryLight)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Cycling && ((Cycling) a).getEnergyExpended() == Cycling.INTENSITY.VeryLight)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                    }
                    if(!activitiesFound)
                    {
                        System.out.println("No activities found");
                    }
                    break;
                case 2:
                    for (Activity a : activities) {
                        if (a instanceof Swimming && ((Swimming) a).getEnergyExpended() == Swimming.INTENSITY.Light)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Running && ((Running) a).getEnergyExpended() == Running.INTENSITY.Light)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Cycling && ((Cycling) a).getEnergyExpended() == Cycling.INTENSITY.Light)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if(!activitiesFound)
                        {
                            System.out.println("No activities found");
                        }
                    }
                    break;
                case 3:
                    for (Activity a : activities) {
                        if (a instanceof Swimming && ((Swimming) a).getEnergyExpended() == Swimming.INTENSITY.Moderate)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Running && ((Running) a).getEnergyExpended() == Running.INTENSITY.Moderate)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Cycling && ((Cycling) a).getEnergyExpended() == Cycling.INTENSITY.Moderate)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                    }
                    if(!activitiesFound)
                    {
                        System.out.println("No activities found");
                    }
                    break;
                case 4:
                    for (Activity a : activities) {
                        if (a instanceof Swimming && ((Swimming) a).getEnergyExpended() == Swimming.INTENSITY.Vigorous)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Running && ((Running) a).getEnergyExpended() == Running.INTENSITY.Vigorous)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Cycling && ((Cycling) a).getEnergyExpended() == Cycling.INTENSITY.Vigorous)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                    }
                    if(!activitiesFound)
                    {
                        System.out.println("No activities found");
                    }
                    break;
                case 5:
                    for (Activity a : activities) {
                        if (a instanceof Swimming && ((Swimming) a).getEnergyExpended() == Swimming.INTENSITY.VeryVigorous)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Running && ((Running) a).getEnergyExpended() == Running.INTENSITY.VeryVigorous)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                        if (a instanceof Cycling && ((Cycling) a).getEnergyExpended() == Cycling.INTENSITY.VeryVigorous)
                        {
                            System.out.println(a);
                            activitiesFound = true;
                        }
                    }
                    if(!activitiesFound)
                    {
                        System.out.println("No activities found");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while (choice != 0);

    }

    public static void typeMenu(ArrayList<Activity> activities)
    {
        int menuWidth = 35;
        Scanner keyboard = new Scanner(System.in);
        int choice;

        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("1. Running");
            System.out.println("2. Swimming");
            System.out.println("3. Cycling");
            System.out.println("0. Go Back");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    for (Activity a : activities) {
                        if (a instanceof Running) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 2:
                    for (Activity a : activities) {
                        if (a instanceof Swimming) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 3:
                    for (Activity a : activities) {
                        if (a instanceof Cycling) {
                            System.out.println(a);
                        }
                    }
                    break;
            }
        } while (choice != 0);
    }

    public static void averageMenu(ArrayList<Activity> activities)
    {
        int menuWidth = 35;
        Scanner keyboard = new Scanner(System.in);
        int choice;
        int total = 0;
        double totalCalories = 0, avgCalories = 0;
        int totalRunning = 0, totalSwimming = 0, totalCycling = 0;
        double totalRunningDist = 0, totalSwimmingDist = 0, totalCyclingDist = 0;
        double avgRunning = 0, avgSwimming = 0, avgCycling = 0;
        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("1. Average distance per activity");
            System.out.println("2. Average Calories Burnt");
            System.out.println("0. Go Back");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();

            switch (choice) {
                case 0:
                    break;
                case 1:
                    for (Activity a : activities) {
                        if (a instanceof Running) {
                            totalRunning++;
                            totalRunningDist += a.getDistance();
                            avgRunning = totalRunningDist / totalRunning;
                        } else if (a instanceof Swimming) {
                            totalSwimming++;
                            totalSwimmingDist += a.getDistance();
                            avgSwimming = totalSwimmingDist / totalSwimming;
                        } else if (a instanceof Cycling) {
                            totalCycling++;
                            totalCyclingDist += a.getDistance();
                            avgCycling = totalCyclingDist / totalCycling;
                        }

                    }
                    System.out.printf("The average distance for %d running activities is: %.2f Km\n", totalRunning, avgRunning);
                    System.out.printf("The average distance for %d swimming activities is: %.2f Km\n", totalSwimming, avgSwimming);
                    System.out.printf("The average distance for %d cycling activities is: %.2f Km\n", totalCycling, avgCycling);
                    break;
                case 2:
                    for (Activity a : activities) {
                        total++;
                        totalCalories += a.countCaloriesBurnt();
                        avgCalories = totalCalories / total;
                    }
                    System.out.printf("The average calories burnt for %d activities is: %.2f\n", total, avgCalories);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while (choice != 0) ;
    }

    public static void displayAll(ArrayList<Activity> activities)
    {
        System.out.printf("%-10s %-10s  %-10s %-10s     %-10s  %-10s\n", "Type", "Dur", "Dist", "AHR", "Date","Calories");
        System.out.println("========================================================================");
        for(Activity a : activities)
        {
            System.out.printf("|%-10s %-10d %-10.2f %-10d %-10s     %-10.2f|\n",
                    a.getName(), a.getDuration(), a.getDistance(), a.getAHR(), a.getDate(), a.countCaloriesBurnt());
        }
        System.out.println("========================================================================");
    }

    public static void sortMenu(ArrayList<Activity> activities)
    {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        int menuWidth = 35;
        System.out.println();
        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("0. Go Back");
            System.out.println("1. Sort by Calories Burnt (Descending)");
            System.out.println("2. Sort by Date");
            System.out.println("3. Sort by Distance");
            System.out.println("4. Sort by Duration");
            System.out.println("5. Sort by Type");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1: {
                    calorieCompare(activities);
                    displayAll(activities);
                    break;
                }
                case 2:
                {
                    orderMenuDate(activities);
                    break;
                }
                case 3:
                {
                    orderMenuDistance(activities);
                    break;
                }
                case 4:
                {
                    orderMenuDuration(activities);
                    break;
                }
                case 5:
                {
                    nameCompare(activities);
                    displayAll(activities);
                    break;
                }
            }
        }while(choice != 0);
    }

    public static void orderMenuDate(ArrayList<Activity> activities)
    {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        int menuWidth = 35;
        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("0. Go Back");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                {
                    DateComparator dateComparator = new DateComparator(DateComparator.SortDirection.ASCENDING);
                    Collections.sort(activities, dateComparator);
                    displayAll(activities);
                    break;
                }
                case 2:
                {
                    DateComparator dateComparator = new DateComparator(DateComparator.SortDirection.DESCENDING);
                    Collections.sort(activities, dateComparator);
                    displayAll(activities);
                    break;
                }
            }
        }while(choice != 0);
    }

    public static void orderMenuDistance(ArrayList<Activity> activities)
    {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        int menuWidth = 35;
        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("0. Go Back");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                {
                    distanceCompareASC(activities);
                    displayAll(activities);
                    break;
                }
                case 2:
                {
                    distanceCompareDSC(activities);
                    displayAll(activities);
                    break;
                }
            }
        }while(choice != 0);
    }

    public static void orderMenuDuration(ArrayList<Activity> activities)
    {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        int menuWidth = 35;
        do {
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            System.out.println("0. Go Back");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            for (int i = 0; i < menuWidth; i++) {
                System.out.print("=");
            }
            System.out.println();
            choice = keyboard.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                {
                    DurationComparator durationComparator = new DurationComparator(DurationComparator.SortDirection.ASCENDING);
                    Collections.sort(activities, durationComparator);
                    displayAll(activities);
                    break;
                }
                case 2:
                {
                    DurationComparator durationComparator = new DurationComparator(DurationComparator.SortDirection.DESCENDING);
                    Collections.sort(activities, durationComparator);
                    displayAll(activities);
                    break;
                }
            }
        }while(choice != 0);
    }
    public static void main(String[] args) throws IOException
    {
        ArrayList<Activity> activities = new ArrayList<>();
        readFile("activity_data_1000.csv", activities, true);
        findDuplicate(activities);
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        do{
            displayMenu();
            choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice)
            {
                case 0:
                    break;
                case 1:
                {
                    displayAll(activities);
                    break;
                }
                case 2:
                {
                    searchFor(activities);
                    break;
                }
                case 3:
                {
                    averageMenu(activities);
                    break;
                }
                case 4:
                {
                    sortMenu(activities);
                    break;
                }
                case 5:
                {
                    addActivity(activities);
                    break;
                }
                default:
                {
                    System.out.println("Invalid choice");
                }
            }
        }   while(choice != 0);
    }
    public static List<Activity> calorieCompare(List<Activity> activities)
    {
        Collections.sort(activities, (a1, a2) -> {
        if (a1.countCaloriesBurnt() > a2.countCaloriesBurnt()) {
        return -1;
        } else if (a1.countCaloriesBurnt() < a2.countCaloriesBurnt()) {
        return 1;
        } else {
        return 0;
        }
        });

        return activities; // Return the sorted list
    }
    public static List<Activity> nameCompare(List<Activity> activities)
    {
        Collections.sort(activities, (a1, a2) -> {
            if (a1.getName().compareTo(a2.getName()) > 0) {
                return -1;
            } else if (a1.getName().compareTo(a2.getName()) < 0) {
                return 1;
            } else {
            return 0;
        }
        });
        return activities;
    }
    public static List<Activity> distanceCompareDSC(List<Activity> activities)
    {
        Collections.sort(activities, (l1, l2) -> {
            if (l1.getDistance() > l2.getDistance())
            {
                return -1;
            }
            else if (l1.getDistance() < l2.getDistance())
            {
                return 1;
            }
            else
            {
            return 0;
            }
        });
        return activities;
    }
    public static List<Activity> distanceCompareASC(List<Activity> activities)
    {
        Collections.sort(activities, (a1, a2) -> {
            if (a1.getDistance() < a2.getDistance())
            {
                return -1;
            }
            else if (a1.getDistance() > a2.getDistance())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        });
        return activities;
    }
}
