# java-hdfs-client

Java hadoop client that provides convenients api for file management and interaction with hadoop file system

# Install

Add the following dependency to your pom file:

```xml
<dependency>
    <groupId>com.github.chen0040</groupId>
    <artifactId>java-hdfs-client</artifactId>
    <version>1.0.1</version>
</dependency>
```

# Usage

To copy local file to hdfs:

```java
String hdfsUri = "hdfs://10.0.1.23:9000/";
BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/test.txt")));
writer.write("Hello World");
writer.close();

HadoopProperties properties = new HadoopProperties();
properties.setUri(hdfsUri);
properties.setUsername("hadoop");

HadoopFileUtils.copyFromLocalToHdfs(properties, "/tmp/test.txt", "/tmp/test.txt");
```

To copy a file from hdfs to local disk:

```java
HadoopProperties properties = new HadoopProperties();
properties.setUri(hdfsUri);
properties.setUsername("hadoop");

HadoopFileUtils.copyFromHdfsToLocal(properties, "/tmp/test.txt", "/tmp/test.txt");
```

To check if a file exists in hdfs

```java
HadoopProperties properties = new HadoopProperties();
properties.setUri(hdfsUri);
properties.setUsername("hadoop");

boolean exists = HadoopFileUtils.pathExistsOnHdfs(properties, "/tmp/test.txt");
```

To delete a file in hdfs:

```java
String hdfsUri = "hdfs://10.0.1.23:9000/";
HadoopProperties properties = new HadoopProperties();
properties.setUri(hdfsUri);
properties.setUsername("hadoop");

HadoopFileUtils.deleteHdfsFile(properties, "/tmp/test.txt");
```
