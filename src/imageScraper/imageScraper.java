package imageScraper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.helper.Validate;

public class imageScraper {
	
	public static void main(String[] args) throws IOException {
		
		
        Validate.isTrue(args.length == 1, "Enter an URL to download images. Eg. https://www.URL.com/");
        String url = args[0];

        System.out.println("Downloading from ..." + url);
        
        Document doc = Jsoup.connect(url).get();
        Elements media = doc.select("[src]");
        BufferedImage image;
        int numOfImages = 0;
        
        for (Element src : media) {
            if (src.tagName().equals("img"))
            	try {
            		URL imgUrl = new URL(src.attr("abs:src"));
            		image = ImageIO.read(imgUrl);
            		numOfImages++;
            		ImageIO.write(image, "jpg", new File("C:/images/"
            		+ numOfImages + ".jpg"));

            	}
            	catch (IOException e){
            		e.printStackTrace();
            	}
            }
        System.out.println("Downloaded " + numOfImages + " images");
    }	
}
