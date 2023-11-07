public class Swimming extends Activity
{

    public double intensityValue;
    public Swimming(String name, int duration, double distance, int AHR, String date)
    {
        super(name, duration, distance, AHR, date);
    }
    public double kmph = (this.distance/(this.duration/60.0));


    public INTENSITY getEnergyExpended()
    {
        if(kmph < 0.5)
        {
            intensityValue = 5;
            return INTENSITY.VeryLight;
        }
        else if (kmph < 1.25)
        {
            intensityValue = 6.3;
            return INTENSITY.Light;
        }
        else if(kmph < 2)
        {
            intensityValue = 7.6;
            return INTENSITY.Moderate;
        }
        else if(kmph < 2.75)
        {
            intensityValue = 8.9;
            return INTENSITY.Vigorous;
        }
        else
        {
            intensityValue = 10.2;
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