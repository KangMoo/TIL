package xmlParser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import xmlParser.req.Media_create_req;

import java.io.*;

public class Tester {
    public void xmlToObject(String xmlStr){
        XmlMapper xmlMapper = new XmlMapper();
        Media_create_req tester = new Media_create_req();
        try{
            tester = xmlMapper.readValue(xmlStr, Media_create_req.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        tester.toString();
    }
    public void xmlToObjectFromFile(String path){
        File file = new File(path);
        XmlMapper xmlMapper = new XmlMapper();
        Media_create_req tester = new Media_create_req();
        try{
            String xml = inputStreamToString(new FileInputStream(file));
            System.out.println("xml = " + xml);
            tester = xmlMapper.readValue(xml, Media_create_req.class);
            System.out.println(tester.toString());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
