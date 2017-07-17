package cn.kkk.usury.model.bean;

/**
 * Created by apple on 2017/5/17.
 */

public class SlideBean {
    private int id;
    private String url;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Slede{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
