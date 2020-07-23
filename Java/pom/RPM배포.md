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
        <defaultDirmode>[기본 경로 권한 ex)755]</defaultDirmode>
        <defaultFilemode>[기본 파일 권한 ex)644]</defaultFilemode>
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

- <directory> : 파일 복사 위치. RPM 설치 과정에서 생성한다.
- <filemode> : 파일의 권한 모드
- <sources> : 해당 복사 위치에 넣을 대상

예를 들어, /usr/local/myapp 폴더가 존재하지 않으면 이 폴더를 생성하고, myapp-버전.war 파일을 이 폴더에 복사한다. 또한, 같은 위치에 myapp-run.sh 파일을 755 모드로 복사한다. 별도로 모드를 지정하지 않은 경우 <defaultDirmode>와 <defaultFilemode>에서 지정한 모드를 사용한다. 비슷하게 소유 계정과 그룹을 지정하지 지정하지 않으면 <defaultUsername>와 <defaultGroupname>에서 지정한 계정과 그룹을 사용한다.

설치 전에 필요한 계정을 생성하거나 기존에 이미 설치된 파일의 경로를 변경하는 등의 작업이 필요할 경우 <preinstallScriptlet>을 이용해서 해당 작업을 실행할 스크립트를 지정한다.

**메이븐 명령어**

이제 RPM 생성을 위한 메이븐 명령어를 실행할 차례다. `rpm:rpm` 골을 이용해서 메이븐을 실행하면 된다.

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>rpm-maven-plugin</artifactId>
  <version>2.0.1</version>
  <configuration>
    <copyright>[저작권자]</copyright>
    <group>[그룹명]</group>
    <release>[릴리즈 버전]</release>
    <defaultDirmode>[기본 경로 권한 ex)755]</defaultDirmode>
    <defaultFilemode>[기본 파일 권한 ex)644]</defaultFilemode>
    <defaultUsername>[계정 명]</defaultUsername>
    <defaultGroupname>[그룹 명]</defaultGroupname>
    <mappings>
      <mapping>
        <directory>[파일 복사 위치]/</directory>
        <sources>
          <source>
            <location>[복사할 대상]</location>
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