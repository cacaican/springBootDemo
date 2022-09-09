package com.xiaocai.base;

import java.io.*;
import java.util.ArrayList;

import okhttp3.*;

/**
 * @description:
 * @author: xiaocai
 * @time: 2021/12/22 10:00
 */
public class BatchClearPrintCache {


    public static void main(String[] args) throws IOException {
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();

        ArrayList<String> address = new ArrayList<>();
        address.add("9.20.130.86");
        address.add("9.20.130.85");
        address.add("9.20.130.84");
        address.add("9.20.130.83");
        address.add("9.20.130.82");
        address.add("9.20.130.81");
        address.add("9.20.130.80");
        address.add("9.20.130.79");
        address.add("9.20.130.78");
        address.add("9.20.130.77");
//        address.add("9.20.130.76");
        address.add("9.20.128.123");
        address.add("9.20.128.124");
        address.add("9.20.128.125");
        address.add("9.20.128.127");
        address.add("9.20.128.128");
        address.add("9.20.128.214");
        address.add("9.20.128.215");

        address.add("9.20.129.35");
        address.add("9.20.129.36");
        address.add("9.20.129.37");
        address.add("9.20.129.38");
        address.add("9.20.129.39");
        address.add("9.20.129.83");
        address.add("9.20.129.84");
        address.add("9.20.129.85");
        address.add("9.20.129.86");
        address.add("9.20.129.87");
        address.add("9.20.129.88");
        address.add("9.20.129.89");
        address.add("9.20.129.90");
        address.add("9.20.129.91");
        address.add("9.20.129.92");
        address.add("9.20.129.93");
        address.add("9.20.129.94");
        address.add("9.20.129.95");
        address.add("9.20.129.96");
        address.add("9.20.129.97");
        address.add("9.20.129.98");
        address.add("9.20.129.99");
        address.add("9.20.129.226");
        address.add("9.20.129.227");
        address.add("9.20.129.228");
        address.add("9.20.129.229");
        address.add("9.20.129.135");
        address.add("9.20.129.136");
        address.add("9.20.129.137");
        address.add("9.20.129.138");
        address.add("9.20.129.139");
        address.add("9.20.128.51");
        address.add("9.20.128.52");
        address.add("9.20.128.53");

        String kindCode ="NPT-01-27050001-00000001";

//        for (int i = 0; i <address.size() ; i++) {
//            String ip = address.get(i);
//            String url = "http://"+ip+":7001//policycenter//controller/gui/dataReport/removeFromCache?kind="+kindCode;
//
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            MediaType mediaType = MediaType.parse("text/plain");
//            RequestBody body = RequestBody.create(mediaType, "");
//            System.out.println(url);
//            Request request = new Request.Builder()
//                    .url(url)
//                    .method("POST", body)
//                    .build();
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//        }

    }


}
