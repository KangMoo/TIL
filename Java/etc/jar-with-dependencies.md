

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-assembly-plugin</artifactId>
  <executions>
    <execution>
      <id>build-App</id>
      <goals>
        <goal>attached</goal>
      </goals>
      <phase>package</phase>
      <configuration>
        <descriptorRefs>
          <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        <finalName>core-acs_amf</finalName>
        <archive>
          <manifest>
            <mainClass>[Main Class]</mainClass>
          </manifest>
        </archive>
      </configuration>
    </execution>
  </executions>
</plugin>
```

