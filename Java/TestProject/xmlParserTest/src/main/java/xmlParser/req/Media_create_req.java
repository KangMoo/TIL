package xmlParser.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media_create_req {
    private String type;
    private String cmd;
    private String appId;
    private int id;
    private List<Integer> dstIds;
    private String from;
    private String to;
    private String remote_ip;
    private int remote_port;
    private int local_port;
    private AUDIO_CODER decoder;
    private AUDIO_CODER encoder;
    private boolean dtmf;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getDstIds() {
        return dstIds;
    }

    public void setDstIds(List<Integer> dstIds) {
        this.dstIds = dstIds;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getRemote_ip() {
        return remote_ip;
    }

    public void setRemote_ip(String remote_ip) {
        this.remote_ip = remote_ip;
    }

    public int getRemote_port() {
        return remote_port;
    }

    public void setRemote_port(int remote_port) {
        this.remote_port = remote_port;
    }

    public int getLocal_port() {
        return local_port;
    }

    public void setLocal_port(int local_port) {
        this.local_port = local_port;
    }

    public AUDIO_CODER getDecoder() {
        return decoder;
    }

    public void setDecoder(AUDIO_CODER decoder) {
        this.decoder = decoder;
    }

    public AUDIO_CODER getEncoderk() {
        return encoder;
    }

    public void setEncoderk(AUDIO_CODER encoder) {
        this.encoder = encoder;
    }

    public boolean isDtmf() {
        return dtmf;
    }

    public void setDtmf(boolean dtmf) {
        this.dtmf = dtmf;
    }

    @Override
    public String toString() {
        return "media_create_req{" +
                "type='" + type + '\'' +
                ", cmd='" + cmd + '\'' +
                ", appId='" + appId + '\'' +
                ", id=" + id +
                ", dstIds=" + dstIds +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", remote_ip='" + remote_ip + '\'' +
                ", remote_port=" + remote_port +
                ", local_port=" + local_port +
                ", decoder=" + decoder +
                ", encoder=" + encoder +
                ", dtmf=" + dtmf +
                '}';
    }
}
