
public class Activity
{
    private String name;
    private int duration;
    private double distance;
    private int AHR;
    private String date;

    //Constructor
    public Activity(String name, int duration, double distance, int AHR, String date)
    {
        this.name = name;
        this.duration = duration;
        this.distance = distance;
        this.AHR = AHR;
        this.date = date;
    }

    public Activity()
    {
        this.name = "Default";
        this.duration = 0;
        this.distance = 0;
        this.AHR = 0;
        this.date = "00/00/0000";
    }

    //Getters
    public String getName()
    {
        return this.name;
    }
    public int getDuration()
    {
        return this.duration;
    }
    public double getDistance()
    {
        return this.distance;
    }
    public int getAHR()
    {
        return this.AHR;
    }
    public String getDate()
    {
        return this.date;
    }

    //Setters
    public void setName(String name)
    {
        this.name = name;
    }
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    public void setDistance(double distance)
    {
        this.distance = distance;
    }
    public void setAHR(int AHR)
    {
        this.AHR = AHR;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    //toString
    public String toString()
    {
        return "Name: " + this.name +
                "\nDuration: " + this.duration + "\nDistance: " +
                this.distance + "\nAHR: " + this.AHR + "\nDate: " + this.date;
    }
}
