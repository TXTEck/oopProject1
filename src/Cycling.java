public class Cycling extends Activity
{
    public double kmph = (this.distance/(this.duration/60.0));
    public double intensityValue;
    public Cycling(String name, int duration, double distance, int AHR, String date)
    {
        super(name, duration, distance, AHR, date);
    }


    public INTENSITY getEnergyExpended()
    {
        if(kmph < 8)
        {
            intensityValue = 2;
            return INTENSITY.VeryLight;
        }
        else if (kmph >= 8 && kmph <16)
        {
            intensityValue = 5;
            return INTENSITY.Light;
        }
        else if(kmph >= 17 && kmph <25)
        {
            intensityValue = 7;
            return INTENSITY.Moderate;
        }
        else if(kmph >= 25 && kmph <33)
        {
            intensityValue = 13;
            return INTENSITY.Vigorous;
        }
        else
        {
            intensityValue = 15;
            return INTENSITY.VeryVigorous;
        }
    }
    public double getIntensityValue()
    {
        return intensityValue;
    }
    @Override
    public double countCaloriesBurnt()
    {
        this.getEnergyExpended();
        return intensityValue * this.duration;
    }

    @Override
    public String toString()
    {
        return String.format("%-10s Duration: %-5d Distance: %-6.2f AHR: %-3d Date: %s Calories:  %-5.2f%n",
                this.name, this.duration, this.distance, this.AHR, this.date,this.countCaloriesBurnt());
    }

}