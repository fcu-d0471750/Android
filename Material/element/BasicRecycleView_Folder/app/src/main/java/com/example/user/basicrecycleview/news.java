/*
*View元件
* */
package com.example.user.basicrecycleview;

public class news {

    //Title
    private String title;
    //圖片ID
    private int pic;

    public news(String title, int pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
