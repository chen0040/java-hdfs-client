package com.github.chen0040.hdfs;


import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * Created by xschen on 12/2/2017.
 */
public class HadoopFileUtilsUnitTest {

   @Test
   public void test_copyFromLocalToHdfs() throws IOException {
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/test.txt")));
      writer.write("Hello World");
      writer.close();

      HadoopProperties properties = new HadoopProperties();
      properties.setUri("hdfs://10.0.1.23:9000/");
      properties.setUsername("hadoop");

      HadoopFileUtils.copyFromLocalToHdfs(properties, "/tmp/test.txt", "/tmp/test.txt");
   }

   @Test
   public void test_delete() throws IOException {
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/test.txt")));
      writer.write("Hello World");
      writer.close();

      HadoopProperties properties = new HadoopProperties();
      properties.setUri("hdfs://10.0.1.23:9000/");
      properties.setUsername("hadoop");

      HadoopFileUtils.deleteHdfsFile(properties, "/tmp/test.txt");
   }
}
