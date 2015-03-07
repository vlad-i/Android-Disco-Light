package ro.vl_d.android.discolight.data;

/**
 * Interface to be used for callbacks when new data is present in the data
 * population thread
 * 
 * @author Vlad
 *
 */
public interface Executable {
    public void execute(int value);
}
