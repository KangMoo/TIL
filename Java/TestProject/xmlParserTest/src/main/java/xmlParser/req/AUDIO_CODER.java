package xmlParser.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AUDIO_CODER {
    private String codec;
    private int duration;
    private String packing;
    private String rate;

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "VIDEO_CODER{" +
                "codec='" + codec + '\'' +
                ", duration=" + duration +
                ", packing='" + packing + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
