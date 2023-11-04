import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainApp {
    public static void readFile(String filename, ArrayList<Activity> activities,boolean hasHeaders) throws IOException {
        File input = new File("activity_data_10.csv");
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


    public static void displayMenu()
    {
        int menuWidth = 35;
        for (int i = 0; i < menuWidth; i++) {
            System.out.print("~");
        }
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("1. View all activities");
        System.out.println("2. View all activities by type");
        System.out.println("3. View average");
        for (int i = 0; i < menuWidth; i++) {
            System.out.print("~");
        }
        System.out.println();
    }



    public static void typeMenu(ArrayList<Activity> activities) {
        int menuWidth = 25;
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
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    public static void averageMenu(ArrayList<Activity> activities) {
        int menuWidth = 25;
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

    public static void displayTable(ArrayList<Activity> activities)
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

    public static void main(String[] args) throws IOException
    {
        ArrayList<Activity> activities = new ArrayList<>();
        readFile("activity_data_10.csv", activities, true);

        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        do{
            displayMenu();
            choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice)
            {
                case 1:
                {
                    displayTable(activities);
                    break;
                }
                case 2:
                {
                    typeMenu(activities);
                    break;
                }
                case 3:
                {
                    averageMenu(activities);
                    break;
                }
                default:
                {
                    System.out.println("Invalid choice");
                }
            }
        }   while(choice != 0);


    }
}