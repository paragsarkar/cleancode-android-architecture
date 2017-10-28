package com.self.poc.httpHandler;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * This class puts requests in Volley queue.
 * <p/>
 */
public class RequestHandler<RequestBody, ResponseBody, Domain> {

    /**
     * The source provider class.
     */
    private HttpHandler<RequestBody, ResponseBody, Domain> httpHandler;

    /**
     * Default Error Listener.
     */
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Error: ", error.getLocalizedMessage());
            // TODO: commented this line becaues we are not using this object and I changed the onErrorResponse method
            // httpHandler.onErrorResponse(error);
        }
    };

    /**
     * Constructor for the source definitions.
     *
     * @param httpHandler
     */
    public RequestHandler(HttpHandler<RequestBody, ResponseBody, Domain> httpHandler) {
        this.httpHandler = httpHandler;
    }

    /**
     * This method prepares the request object and puts it in Volley Queue
     *
     * @param tag
     */
    public void executeOnNetwork(String tag) {
        final String url;
        if (!httpHandler.getRequestParams().isEmpty() && httpHandler.responseBodyType != HttpHandler.RESPONSE_BODY_STRING) {
            // prepare URL
            StringBuilder sb = new StringBuilder(httpHandler.getURL());
            sb.append("?");
            for (Map.Entry<String, String> entry : httpHandler.getRequestParams().entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            url = sb.toString();
        } else {
            url = httpHandler.getURL();
        }

        GenericRequest request = new GenericRequest<>(
                httpHandler.getHttpRequestMethod(),
                url,
                httpHandler.getRequestBody(),
                httpHandler.beanTypeClass,
                httpHandler.domainClass,
                new GenericRequest.ResponseListener<ResponseBody>() {
                    @Override
                    public void onResponse(ResponseBody response, Map<String, String> header) {
                        httpHandler.onResponse(response, header);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.toString());
                        Response response = null;
                        NetworkResponse networkResponse = error.networkResponse;
                        if(networkResponse != null)
                        if(networkResponse.data != null && networkResponse.data.length > 0) {
                            try {
                                String json = new String(
                                        networkResponse.data,
                                        HttpHeaderParser.parseCharset(networkResponse.headers, GenericRequest.PROTOCOL_CHARSET));
                                response = new Gson().fromJson(json, Response.class);
                            } catch (UnsupportedEncodingException ex) {
                                 Log.e("", "Data encoding error");
                            } catch (JsonSyntaxException ex) {
                                 Log.e("", "Data encoding error");
                            }
                        }

                        httpHandler.onErrorResponse(response);
                    }
                });
        if (!httpHandler.getRequestHeaders().isEmpty()) {
            request.setRequestHeader(httpHandler.getRequestHeaders());
        }
        // add the request object to the queue to be executed
        RequestManager.getInstance().addToRequestQueue(request, tag);
    }

}
