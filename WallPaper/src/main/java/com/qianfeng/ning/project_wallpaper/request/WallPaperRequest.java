package com.qianfeng.ning.project_wallpaper.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by Ning on 2016/5/31.
 */
public class WallPaperRequest<T> extends Request<T> {

    public Response.Listener<T> listener;
    //某类的class类对象类型
    private Class<T> clazz;

    public WallPaperRequest(Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorlistener) {
        this(clazz, Method.GET, url, listener, errorlistener);
    }

    public WallPaperRequest(Class<T> clazz, int method, String url, Response.Listener<T> listener, Response.ErrorListener errorlistener) {
        super(method, url, errorlistener);
        this.listener = listener;
        this.clazz = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String s;
        try {
            s = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            s = new String(response.data);
        }
        //解析
        T t = new Gson().fromJson(s, clazz);
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));

    }

    @Override
    protected void deliverResponse(T response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        listener = null;
    }
}
