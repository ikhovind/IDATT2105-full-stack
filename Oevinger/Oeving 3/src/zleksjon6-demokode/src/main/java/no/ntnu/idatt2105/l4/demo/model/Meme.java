package no.ntnu.idatt2105.l4.demo.model;

public class Meme {

    private String memeText = "All the memes";
    private String pic = "Spongebob";

    public Meme() {
    }

    public Meme(String memeText, String pic) {
        this.memeText = memeText;
        this.pic = pic;
    }

    public String getMemeText() {
        return memeText;
    }

    public String getPic() {
        return pic;
    }
}
