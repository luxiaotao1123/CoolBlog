package cn.blog.bean;

public class Category {
    private Integer labelid;

    private String label;

    private Integer blogcount;

    public Category() {
    }

    public Category(Integer labelid, String label, Integer blogcount) {
        this.labelid = labelid;
        this.label = label;
        this.blogcount = blogcount;
    }

    public Integer getBlogcount() {
        return blogcount;
    }

    public void setBlogcount(Integer blogcount) {
        this.blogcount = blogcount;
    }

    public Integer getLabelid() {
        return labelid;
    }

    public void setLabelid(Integer labelid) {
        this.labelid = labelid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}