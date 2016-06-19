package module.http;

import android.content.Context;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import module.base.ApplicationPrefs;


/**
 * Created by om on 10/14/2015.
 */
public class BaseHttpResponseToken extends StringRequest {
    private Context context;
    private ApplicationPrefs prefs;
    private Map<String, String> mParams;

    public BaseHttpResponseToken(Context context, int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.context = context;
        prefs = ApplicationPrefs.getInstance(this.context);
    }

    public BaseHttpResponseToken(Context context, int method, String url, Map<String, String> mymap, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.context = context;
        prefs = ApplicationPrefs.getInstance(this.context);
        this.mParams=mymap;
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

            String[] s = responseHeaders.get("Set-Cookie").split(";");

            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));


            /**
             * Into the object
             */
//          return Response.success(gson.fromJson(jsonStr, clazz), HttpHeaderParser.parseCacheHeaders(response));
            return Response.success(jsonStr, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }
}
