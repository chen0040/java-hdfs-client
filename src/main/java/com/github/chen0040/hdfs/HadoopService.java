package com.github.chen0040.hdfs;


/**
 * Created by xschen on 17/3/2017.
 */
public interface HadoopService {
   void init(HadoopProperties properties);
   void writeString(String path, String data);
   String readString(String path);
}
