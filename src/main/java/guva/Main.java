package guva;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: jianjun.yu
 * Date: 14-11-17
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static  void TestLoadingCache() throws Exception{
        LoadingCache<String,String> cahceBuilder= CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, String>(){
                    @Override
                    public String load(String key) throws Exception {
                        String strProValue="hello "+key+"!";

                        System.out.println("transfer loading method ");
                        return strProValue;
                        //return  null;
                    }

                });

        System.out.println("jerry value:"+cahceBuilder.apply("jerry"));
        System.out.println("jerry value:"+cahceBuilder.get("jerry"));
        System.out.println("peida value:"+cahceBuilder.get("peida"));
        System.out.println("peida value:"+cahceBuilder.apply("peida"));
        System.out.println("lisa value:"+cahceBuilder.apply("lisa"));
        cahceBuilder.put("harry", "ssdded");
        System.out.println("harry value:"+cahceBuilder.get("harry"));
    }


    public static  void testcallableCache()throws Exception{
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
        String resultVal = cache.get("jerry", new Callable<String>() {
            public String call() {

                System.out.println("transfer call method");
                String strProValue="hello "+"jerry"+"!";
                return strProValue;
            }
        });
        System.out.println("jerry value : " + resultVal);

        resultVal = cache.get("peida", new Callable<String>() {
            public String call() {
                System.out.println("transfer call method");
                String strProValue="hello "+"peida"+"!";
                return strProValue;
            }
        });
        System.out.println("peida value : " + resultVal);
    }


    private static   LoadingCache<String, String> cacheLoader = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {

                @Override
                public String load(String key) throws Exception {
                    return  "cache".concat(key);
                }
            });
    public static void testExpireAfterWrite() throws  Exception{





    }

    public static  void testExprireAfterAccess(){

        LoadingCache<String, String> cacheLoader = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {

                    @Override
                    public String load(String key) throws Exception {
                        return  "cache".concat(key);
                    }
                });

    }

    public static void main(String[] args) throws Exception {

        TestLoadingCache();

        //testcallableCache();

    }
}
