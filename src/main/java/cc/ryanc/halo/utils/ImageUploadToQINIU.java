package cc.ryanc.halo.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUploadToQINIU {


    private  static String  BASE_URL ="http://ovp48417e.bkt.clouddn.com/";
    public static String upload( InputStream inputStream){

        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);


        String accressKey = "9S49ClvUKXGKJHRLmYApTGQGIWcfMslj2K1h7Xbx";
        String secretKey = "aVknm38Jngk3FEgx8B31QJ3JtwdcMcLszXpZadIZ";
        String bucket = "tracenight";

        String key = null;
        String newFilename = "";
        try{
            Auth auth = Auth.create(accressKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try{
                Response response = uploadManager.put(inputStream,key,upToken,null, null);

                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                newFilename = putRet.key;
            }catch(QiniuException ex){
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }


        }catch(Exception ex){

        }
        return BASE_URL+newFilename;
    }


    public static void delete(String url){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        String accessKey = "9S49ClvUKXGKJHRLmYApTGQGIWcfMslj2K1h7Xbx";
        String secretKey = "aVknm38Jngk3FEgx8B31QJ3JtwdcMcLszXpZadIZ";
        String bucket = "tracenight";
        String key = url.replaceAll(BASE_URL,"").trim();
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }





    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
        {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }

            InputStream input = new ByteArrayInputStream(b);
            System.out.println(upload(input));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
