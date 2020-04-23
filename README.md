# URL Shortening

    Shortening of original URL by using provided SEO keyword
    
    Shortening of original URL, without provided keyword. Randomly generate 5 alpha-numeric keyword
    
    Retrieve original URL by shorten URL

## Build:
    mvn install

## How to use:

    java -jar target/url-shortener.jar [v2]
    
    where "v2" option use URLStorageImplV2 implementation class 
    
## Additional

    The URLStorageImplV2 class, instead of randomly generating 5 alpha-numeric keyword, uses an incremental alpha-numeric counter. This allows you to store web links in an array, adding and searching by the index of the array. 
    This approach reduces memory consumption for storing web links and simplifies the use of external storage sources.