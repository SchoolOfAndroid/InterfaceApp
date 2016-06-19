package module.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bharti on 6/6/2016.
 */
public class Chat implements Serializable {

    @SerializedName("status")
    public String status = null;

    @SerializedName("count")
    public String count = null ;

    @SerializedName("result")
    public List<Message> result=new ArrayList<>();
}
