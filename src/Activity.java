
public abstract class Activity
{
    protected enum INTENSITY {VeryLight, Light, Moderate, Vigorous, VeryVigorous}
    protected String name;
    protected int duration;
    protected double distance;
    protected int AHR;
    protected String date;

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

    //Methods

    public abstract double countCaloriesBurnt();

    //toString
    @Override
    public String toString()
    {
        return String.format("%-10s Duration: %-5d Distance: %-6.2f AHR: %-3d Date: %s%n",
                this.name, this.duration, this.distance, this.AHR, this.date);
    }
}
