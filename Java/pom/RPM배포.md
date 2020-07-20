## RPM 배포

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>rpm-maven-plugin</artifactId>
    <version>2.0.1</version>
    <executions>
        <execution>
            <phase>none</phase>
            <goals>
                <goal>rpm</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <copyright>[저작권자]</copyright>
        <group>[그룹명]</group>
        <description>[설명]</description>
        <release>[릴리즈 버전]</release>
        <autoRequires>false</autoRequires>
        <targetOS>linux</targetOS>
        <defaultDirmode>755</defaultDirmode>
        <defaultFilemode>644</defaultFilemode>
        <defaultUsername>[계정 명]</defaultUsername>
        <defaultGroupname>[그룹 명]</defaultGroupname>
        <mappings>
            <mapping>
                <directory>[매핑할 디렉토리]/</directory>
                <sources>
                    <source>
                        <location>[저장할 소스]</location>
                    </source>
                </sources>
            </mapping>
        </mappings>
    </configuration>
</plugin>
```





### Sample

```xml
<properties>
    <rpm.release.version>3</rpm.release.version>
    <rpm.binary.dir>target/rpm/${project.artifactId}/RPMS/noarch</rpm.binary.dir>
    <rpm.binary.name>${project.artifactId}-${project.version}-${rpm.release.version}.noarch.rpm</rpm.binary.name>
</properties>
```

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>rpm-maven-plugin</artifactId>
    <version>2.0.1</version>
    <executions>
        <execution>
            <phase>none</phase>
            <goals>
                <goal>rpm</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <copyright>2020, Uangel</copyright>
        <group>Service Dev</group>
        <description>Core ACS AMF Module</description>
        <release>${rpm.release.version}</release>
        <autoRequires>false</autoRequires>
        <targetOS>linux</targetOS>
        <defaultDirmode>755</defaultDirmode>
        <defaultFilemode>644</defaultFilemode>
        <defaultUsername>amf</defaultUsername>
        <defaultGroupname>acs</defaultGroupname>
        <mappings>
            <mapping>
                <directory>/home/amf/amf/lib/</directory>
                <sources>
                    <source>
                        <location>${project.basedir}/target/core-acs_amf-jar-with-dependencies.jar</location>
                    </source>
                </sources>
            </mapping>
            <mapping>
                <directory>/home/amf/amf/bin/</directory>
                <filemode>755</filemode>
                <sources>
                    <source>
                        <location>${project.basedir}/src/main/resources/bin/run.sh</location>
                    </source>
                </sources>
            </mapping>
            <mapping>
                <directory>/home/amf/amf/config/</directory>
                <sources>
                    <source>
                        <location>${project.basedir}/src/main/resources/config/.c-acs_amf_default.config</location>
                    </source>
                    <source>
                        <location>${project.basedir}/src/main/resources/config/c-acs_amf_oam.config</location>
                    </source>
                    <source>
                        <location>${project.basedir}/src/main/resources/config/c-acs_amf_rmi.config</location>
                    </source>
                    <source>
                        <location>${project.basedir}/src/main/resources/config/c-acs_amf_user.config</location>
                    </source>
                    <source>
                        <location>${project.basedir}/src/main/resources/config/logback.xml</location>
                    </source>
                </sources>
            </mapping>
        </mappings>
    </configuration>
</plugin>
```