package com.github.chen0040.hdfs;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Created by xschen on 17/3/2017.
 */
public class HadoopServiceImpl implements HadoopService {
   private final HadoopProperties properties = new HadoopProperties();

   private static final Logger logger = LoggerFactory.getLogger(HadoopServiceImpl.class);

   private static InputStream getResource(String filename) throws IOException {
      ClassLoader classLoader = HadoopServiceImpl.class.getClassLoader();
      URL dataFile = classLoader.getResource(filename);
      return dataFile.openStream();
   }

   public HadoopServiceImpl(){
      setupHadoopHome();
   }


   public static void setupHadoopHome(){
      boolean windowsOS = System.getProperty("os.name").toLowerCase().contains("windows");
      if (windowsOS) {

         // for test only on windows OS
         String hadoop_dir = System.getProperty("hadoop.home.dir");
         if (hadoop_dir == null) {
            File tmpDir = new File("C:/tmp");
            if(!tmpDir.exists()){
               tmpDir.mkdir();
               tmpDir.setWritable(true);
            }
            File binDir = new File("C:/tmp/bin");
            if(!binDir.exists()) {
               binDir.mkdir();
               binDir.setWritable(true);

               String zipFileName = "C:/tmp/bin/win-bin.zip";
               try {

                  InputStream inStream = getResource("win-bin.zip");

                  FileOutputStream outStream = new FileOutputStream(new File(zipFileName));

                  byte[] buffer = new byte[1024];

                  int length;
                  while ((length = inStream.read(buffer)) > 0){
                     outStream.write(buffer, 0, length);
                  }

                  inStream.close();
                  outStream.close();

                  ZipFile zipFile = new ZipFile(zipFileName);
                  zipFile.extractAll("C:/tmp/bin");
               }
               catch (IOException e) {
                  logger.error("Failed to copy the dict.zip from resources to C:/tmp/bin", e);
               }
               catch (ZipException e) {
                  logger.error("Failed to unzip " + zipFileName, e);
               }
            }
            System.setProperty("hadoop.home.dir", "C:/tmp");
         }
      }
   }

   @Override public void init(HadoopProperties properties) {
      this.properties.copy(properties);
   }


   @Override public void writeString(String path, String data) {
      HadoopFileUtils.writeToHdfs(properties, data, path);
   }


   @Override public String readString(String path) {
      return HadoopFileUtils.readFromHdfs(properties, path);
   }
}
