package module.http;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import module.base.ApplicationPrefs;


/**
 * Created by om on 10/14/2015.
 */
public class BaseHttpRequestToken extends StringRequest {
    private Context context;
    private ApplicationPrefs prefs;
    private Map<String, String> mParams;


    public BaseHttpRequestToken(Context context, int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    //  LogUtil.debug("<<<++++ BaseHttpRequestToken +++++>>> ");
        this.context = context;
        prefs = ApplicationPrefs.getInstance(this.context);
    }

    public BaseHttpRequestToken(Context context, int method, String url, Map<String, String> mymap, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    //    LogUtil.debug("<<<++++ BaseHttpRequestToken +++++>>> ");
        this.context = context;
        prefs = ApplicationPrefs.getInstance(this.context);
        this.mParams=mymap;
    }

  /*  @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
       // LogUtil.debug("BaseHttpRequest Token --->>> " + prefs.getToken());
            return super.getHeaders();
    }*/

    private Map<String, String> createBasicAuthHeader(String tokenName, String tokenValue) {
//    private Map<String, String> createBasicAuthHeader(String username, String password) {
      Map<String, String> headerMap = new HashMap<String, String>();
//        String credentials = username + ":" + password;
//        String encodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
        headerMap.put(tokenName, tokenValue);
        return headerMap;
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            /**
             * Returns the data
             */

            Map<String, String> responseHeaders = response.headers;
            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));


            /**
             * Into the object
             */

            return Response.success(jsonStr, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

}
