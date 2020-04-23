package org.interview.jobtask.shortener;

import org.interview.jobtask.shortener.storage.KeywordCollisionException;
import org.interview.jobtask.shortener.storage.URLStorageImpl;
import org.interview.jobtask.shortener.storage.URLStorageImplV2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.*;

public class Application {

    private final static Scanner scanner = new Scanner(in);

    public static final String SHORT_DOMAIN = "short.en";

    public static final String HTTPS = "https";

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void main(String[] args) {
        URLShortener shortener = createURLShortener(args);
        while (true) {
            out.println("---------------------------");
            out.println("input original url to shortening, or short url to retrieve original");
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
                    shortenUrl = shortener.shortening(originalUrl, keyword);
                    out.println("shorten URL: " + shortenUrl);
                } catch (KeywordCollisionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static URLShortener createURLShortener(String[] args) {
        String storage = args.length == 1
                            && "v2".equalsIgnoreCase(args[0]) ?
                        args[0] : "v1";
        out.println("start with storage " + storage);
        if ("v2".equalsIgnoreCase(storage)) {
            return new BaseURLShortenerImpl(URLStorageImplV2.instance());
        } else {
            return new BaseURLShortenerImpl(URLStorageImpl.instance());
        }

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
