package org.interview.jobtask.shortener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.*;

public class Application {

    private final static Scanner scanner = new Scanner(in);

    public static final String SHORT_DOMAIN = "short.en";

    public static final String HTTPS = "https";

    public static void main(String[] args) {
        URLShortener shortener = createURLShortener(args);
        while (true) {
            out.println("---------------------------");
            out.println("input original url for shortening, or short url for retrieve original");
            out.println("---------------------------");
            URL inputUrl = readURL();
            if (SHORT_DOMAIN.equalsIgnoreCase(inputUrl.getHost())) {
                String originalUrl = shortener.retrieveOriginal(inputUrl.getPath().substring(1));
                String message = originalUrl != null ? originalUrl : "not found";
                out.println("original URL " + message);
            } else {
                String keyword = readKeyword();
                String originalUrl = inputUrl.toString();
                String shortenUrl = null;
                try {
                    shortenUrl = keyword != null ?
                            shortener.shortening(originalUrl, keyword)
                            : shortener.shortening(originalUrl);
                    out.println("shorten URL: " + shortenUrl);
                } catch (KeywordCollisionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static URLShortener createURLShortener(String[] args) {
        return new BaseURLShortener(KeywordGenerator.instance(), URLStorage.instance());
    }

    private static String readKeyword() {
        while (true) {
            out.print("input keyword (optional): ");
            String keyword = getScanner().nextLine();
            if (keyword.isBlank()) {
                return null;
            } else if (keyword.length() > 20) {
                err.println("keyword should be at max 20 symbols. Try again");
                continue;
            }
            return keyword;
        }
    }

    private static URL readURL() {
        while (true) {
            out.print("input url: ");
            String urlString = getScanner().nextLine();
            URL url = buildURL(urlString);
            if (url != null) {
                return url;
            }
            err.println("wrong url. Try again");
        }
    }

    private static URL buildURL(String urlString) {
        try {
            URL url = new URL(urlString);
            if (url.getHost().isBlank()
                    || url.getProtocol().isBlank()
                    || !HTTPS.equalsIgnoreCase(url.getProtocol())
                    || url.getPath().isBlank()) {
                return null;
            }
            return url;
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private static Scanner getScanner() {
        return scanner;
    }
}
