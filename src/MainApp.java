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
        int menuWidth = 25;
        for (int i = 0; i < menuWidth; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("1. View all activities");
        for (int i = 0; i < menuWidth; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static void displayTable(ArrayList<Activity> activities)
    {
        System.out.printf("%-10s %-10s %-20s %-10s     %-10s\n", "Type", "Dur", "Dist", "AHR", "Date");
        System.out.println("=====================================================================");
        for(Activity a : activities)
        {
            System.out.printf("%-10s %-10d %-20.2f %-10d %-10s\n", a.getName(), a.getDuration(), a.getDistance(), a.getAHR(), a.getDate());
        }
        System.out.println("=====================================================================");
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
            }
        }   while(choice != 0);
    }
}