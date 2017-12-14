package cn.blog.bean;

import java.util.Date;

/**
 * Created by liuwen on 2017/12/14.
 */
public class Archive {
    private Integer blogid;

    private String title;

    private Date updatetime;

    public Archive() {
    }

    public Archive(Integer blogid, String title, Date updatetime) {
        this.blogid = blogid;
        this.title = title;
        this.updatetime = updatetime;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
