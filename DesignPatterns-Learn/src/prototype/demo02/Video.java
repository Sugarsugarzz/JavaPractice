package prototype.demo02;

import java.util.Date;

// 视频原型
public class Video implements Cloneable {

    private String name;

    private Date createTime;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 改造clone方法，实现深拷贝
        Object obj = super.clone();

        // 实现深克隆的操作。（or 序列化、反序列化）
        Video v = (Video) obj;
        v.createTime = (Date) this.createTime.clone(); // 将这个对象的属性进行克隆

        return obj;
    }

    public Video() {
    }

    public Video(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
