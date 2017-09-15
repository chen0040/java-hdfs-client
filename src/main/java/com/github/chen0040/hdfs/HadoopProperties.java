package com.github.chen0040.hdfs;


import java.io.Serializable;


/**
 * Created by xschen on 12/2/2017.
 */
public class HadoopProperties implements Serializable {
   private String username = "hadoop";
   private String uri;


   public String getUsername() {
      return username;
   }


   public void setUsername(String username) {
      this.username = username;
   }


   public String getUri() {
      return uri;
   }


   public void setUri(String uri) {
      this.uri = uri;
   }


   public void copy(HadoopProperties rhs) {
      this.username = rhs.username;
      this.uri = rhs.uri;
   }
}
