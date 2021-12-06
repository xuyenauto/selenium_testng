package helpers;

public class Constants {

    public static final int TIME_OUT_SECOND = Integer.parseInt(new ReadDataFile().readJsonFile("/config.json","Constant","time_out_second"));

}
