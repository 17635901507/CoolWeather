package com.kkk.mvp.ok;


import com.jy.coolweather.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * created by taofu on 2019-08-25
 **/
public class ApiDataService {


    private static final long DEFAULT_TIMEOUT = 20000; // 默认超时20s


    private static volatile Object mApiService;

    private static String mBaseUrl;
    private static Class mAClass;


    public static void init(String baseUrl,Class aClass){
        mBaseUrl = baseUrl;
        mAClass = aClass;
    }

    public static Object getApiService(){

        if(mBaseUrl == null || mAClass == null){
            throw new NullPointerException(" Must init ( ApiDataService.init(url,class) ) before invoke getApiService");
        }


        if(mApiService == null){

            synchronized (ApiDataService.class){

                if(mApiService == null){

                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();


                    /**
                     * 注意，如果有大文件下载，或者 response 里面的body 很大，要么不加HttpLoggingInterceptor 拦截器
                     * 如果非要加，日志级别不能是 BODY,否则容易内存溢出。
                     */
                    if (BuildConfig.DEBUG) {
                        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                    } else {
                        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }

                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .client(httpClient)
                            .addConverterFactory(GsonConverterFactory.create()) // 帮我们把json 窜转为 entity 对象
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 结合 rxjava 使用
                            .baseUrl(mBaseUrl)
                            .build();


                    mApiService = retrofit.create(mAClass);

                }

            }
        }
        return mApiService;
    }
}
