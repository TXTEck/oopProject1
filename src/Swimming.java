public class Swimming extends Activity
{
    public double kmph = (this.distance/(this.duration/60.0));
    public double intensityValue;
    public Swimming(String name, int duration, double distance, int AHR, String date)
    {
        super(name, duration, distance, AHR, date);
    }


    public INTENSITY getIntensityValue()
    {
        if(kmph < 0.5)
        {
            intensityValue = 5;
            return Activity.INTENSITY.VeryLight;
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

    @Override
    public double countCaloriesBurnt()
    {
        return intensityValue * this.duration;
    }

}