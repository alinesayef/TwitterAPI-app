package com.app.twitterapi;

import android.graphics.Bitmap;

public class Model {

    String Name,Content,Writer;
    Bitmap Image;
    int T1;


    public Model(int t1, String name, String content, String writer, Bitmap image) {
        Name = name;
        Content = content;
        Writer = writer;
        Image = image;
        T1 = t1;
    }

    public Model(String n, String c, String w, Bitmap bitmap) {
        Name = n;
        Content = c;
        Writer = w;
        Image = bitmap;
    }

    public Model() {
        Name = "No";
        Content = "No";

    }


    public int getT1() {
        return T1;
    }

    public void setT1(int t1) {
        T1 = t1;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }
}
