package com.avin.flickrapp.rest;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;

/**
 * Created by avin on 14/06/15.
 */
public class JsonpToGsonConvertor extends GsonConverter {
    public JsonpToGsonConvertor(Gson gson) {
        super(gson);
    }

    public JsonpToGsonConvertor(Gson gson, String charset) {
        super(gson, charset);
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        String dirty = toString(body);
        Log.d("-----DIRTY----",dirty);
        int startIndex= dirty.indexOf('(');
        int endIndex= dirty.lastIndexOf(')');
//        String clean = dirty.replaceAll("(^\\(|\\)$)", "");
        String clean = dirty.substring(startIndex+1, endIndex);
        Log.d("-----CLEAN------", clean);
        body = new JsonTypedInput(clean.getBytes(Charset.forName(HTTP.UTF_8)));
        return super.fromBody(body, type);
    }
    private String toString(TypedInput body){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(body.in()));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    public class JsonTypedInput implements TypedInput{

        private final byte[] mStringBytes;

        JsonTypedInput(byte[] stringBytes) {
            this.mStringBytes = stringBytes;
        }


        @Override
        public String mimeType() {
            return "application/json; charset=UTF-8";
        }



        @Override
        public long length() {
            return mStringBytes.length;
        }

        @Override
        public InputStream in() throws IOException {
            return new ByteArrayInputStream(mStringBytes);
        }
    }
}
