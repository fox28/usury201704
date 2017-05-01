package cn.kkk.usury.model.bean;

/**
 * Created by yao on 2015/11/10.
 */
public class AppBean {
    private String name;
    private String version;
    private int photoId;
    private int fileSize;
    private String intro;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public AppBean() {
    }

    public AppBean(String name, String version, int photoId, int fileSize, String intro) {
        this.name = name;
        this.version = version;
        this.photoId = photoId;
        this.fileSize = fileSize;
        this.intro = intro;
    }

    public AppBean(int photoId) {
        this.photoId = photoId;
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", photoId=" + photoId +
                ", fileSize=" + fileSize +
                ", intro='" + intro + '\'' +
                '}';
    }
}
