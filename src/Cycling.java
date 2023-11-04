public class Cycling extends Activity
{
    public double kmph = (this.distance/(this.duration/60.0));
    public double intensityValue;
    public Cycling(String name, int duration, double distance, int AHR, String date)
    {
        super(name, duration, distance, AHR, date);
    }


    public INTENSITY getIntensityValue()
    {
        if(kmph < 8)
        {
            intensityValue = 2;
            return Activity.INTENSITY.VeryLight;
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

    @Override
    public double countCaloriesBurnt()
    {
        this.getIntensityValue();
        return intensityValue * this.duration;
    }

}