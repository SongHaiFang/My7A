package demo.song.com.my7a;

import java.util.List;

/**
 * data:2017/9/26 0026.
 * Created by ：宋海防  song on
 */

public class MyBean {

    public int dataSize;
    public List<ApkBean> apk;

    public static class ApkBean {

        public String id;
        public String name;
        public String iconUrl;
        public String downloadUrl;
        public String packageName;
        public String versionName;
        public String versionCode;
        public String apkSize;
        public String downloadTimes;
        public String categoryName;
        public String from;
        public int markid;
    }
}
