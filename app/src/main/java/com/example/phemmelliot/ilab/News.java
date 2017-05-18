package com.example.phemmelliot.ilab;

/**
 * Created by phemmelliot on 5/16/17.
 */

public class News
{
    private String Text, Title, source;
    private int img = NO_IMAGE;
    private static final int NO_IMAGE = -1;
    public News(String Title, String Text, String Source, int img)
    {
        this.Text = Text;
        this.Title = Title;
        this.source = Source;
        this.img = img;
    }

    public String getText() {
        return Text;
    }

    public String getTitle() {
        return Title;
    }

    public String getSource() {
        return source;
    }
    public boolean hasImage()
    {
        return img != NO_IMAGE;
    }

    public int getImg() {
        return img;
    }
}
