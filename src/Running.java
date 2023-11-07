public class Running extends Activity
{

    public Running(String name, int duration, double distance, int AHR, String date)
    {
        super(name, duration, distance, AHR, date);
    }

    public double kmph = (this.distance/(this.duration/60.0));
    public double intensityValue;


    public INTENSITY getEnergyExpended()
    {
        if(kmph < 4)
        {
            intensityValue = 4.1;
            return INTENSITY.VeryLight;
        }
        else if (kmph >= 4 && kmph <8)
        {
            intensityValue = 7.2;
            return INTENSITY.Light;
        }
        else if(kmph >=8 && kmph < 12)
        {
            intensityValue = 10;
            return INTENSITY.Moderate;
        }
        else if(kmph >= 12 && kmph < 16)
        {
            intensityValue = 15.4;
            return INTENSITY.Vigorous;
        }
        else
        {
            intensityValue = 20.8;
            return INTENSITY.VeryVigorous;
        }
    }
    public double getIntensityValue()
    {
        this.getEnergyExpended();
        return intensityValue;
    }
    public double getKmph()
    {
        return Math.round(kmph);
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
