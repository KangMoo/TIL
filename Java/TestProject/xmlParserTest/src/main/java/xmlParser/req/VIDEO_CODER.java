package xmlParser.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VIDEO_CODER {
    private String codec;
    private int fps;
    private int quality;
    private int profile_level;
    private int packetization;
    private int bps;
    private int fs;
    private int cpb;
    private int br;
    private int nalu_size;
    private int smbps;

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getProfile_level() {
        return profile_level;
    }

    public void setProfile_level(int profile_level) {
        this.profile_level = profile_level;
    }

    public int getPacketization() {
        return packetization;
    }

    public void setPacketization(int packetization) {
        this.packetization = packetization;
    }

    public int getBps() {
        return bps;
    }

    public void setBps(int bps) {
        this.bps = bps;
    }

    public int getFs() {
        return fs;
    }

    public void setFs(int fs) {
        this.fs = fs;
    }

    public int getCpb() {
        return cpb;
    }

    public void setCpb(int cpb) {
        this.cpb = cpb;
    }

    public int getBr() {
        return br;
    }

    public void setBr(int br) {
        this.br = br;
    }

    public int getNalu_size() {
        return nalu_size;
    }

    public void setNalu_size(int nalu_size) {
        this.nalu_size = nalu_size;
    }

    public int getSmbps() {
        return smbps;
    }

    public void setSmbps(int smbps) {
        this.smbps = smbps;
    }

    @Override
    public String toString() {
        return "VIDEO_CODER{" +
                "codec='" + codec + '\'' +
                ", fps=" + fps +
                ", quality=" + quality +
                ", profile_level=" + profile_level +
                ", packetization=" + packetization +
                ", bps=" + bps +
                ", fs=" + fs +
                ", cpb=" + cpb +
                ", br=" + br +
                ", nalu_size=" + nalu_size +
                ", smbps=" + smbps +
                '}';
    }
}
