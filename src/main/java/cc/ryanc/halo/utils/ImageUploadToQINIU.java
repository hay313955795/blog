package cc.ryanc.halo.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.InputStream;

public class ImageUploadToQINIU {
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
        return "http://ovp48417e.bkt.clouddn.com/"+newFilename;
    }
}
