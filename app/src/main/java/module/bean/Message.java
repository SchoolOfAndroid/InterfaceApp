package module.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by Bharti on 6/6/2016.
 */
public class Message implements Serializable {


    @SerializedName("nickname")
    public String nickname = null;

    @SerializedName("text")
    public String text = null;


}
