package module.blu;


import com.sumod.interfaceapp.App;


/**
 * Created by Admin on 15-01-2015.
 */

public class ServerHelper {

    public static final String server = App.HOST + "chat/";

    public static final String CHECK_USER = server + "userCheck.php";
    public static final String GET_LIST = server + "getMessages.php";
    public static final String SEND_MESSAGE = server + "sendMessage.php";
}
