package org.interview.jobtask.shortener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class URLStorageImplV2 implements URLStorage {

    // mininal 5 letter keyword
    public static final String MIN5LETTER_KEYWORD = "baaaa";

    private final Map<String, Integer> keywordDictionary;

    private final ArrayList<String> storage;

    private final IdentityCodec codec;

    // mininal 5 letter keyword index
    private int shiftIndex;

    private URLStorageImplV2(IdentityCodec codec) {
        this.codec = codec;
        this.storage = new ArrayList();
        this.keywordDictionary = new HashMap<>();
    }

    public static URLStorageImplV2 instance() {
        IdentityCodec codec = IdentityCodec.of(Application.ALPHABET);
        return new URLStorageImplV2(codec);
    }

    @Override
    public String store(String originalUrl, String keyword) throws KeywordCollisionException {
        if (keyword == null) {
            storage.add(originalUrl);
            return codec.encode(getMaxIndex());
        }
        if (keywordDictionary.containsKey(keyword)) throw new KeywordCollisionException("keyword " + keyword);
        storage.add(originalUrl);
        keywordDictionary.put(keyword, getMaxIndex());
        return keyword;
    }

    private int getMaxIndex() {
        return getShiftIndex() + storage.size() - 1;
    }

    @Override
    public String retrieve(String id) {
        Integer index = extractIndex(id);
        if (index == null
                || index < getShiftIndex()
                || index - getShiftIndex() >= storage.size()) {
            return null;
        }
        return storage.get(index - getShiftIndex());
    }

    private Integer extractIndex(String id) {
        if (!keywordDictionary.containsKey(id)) {
            return codec.decode(id);
        } else {
            return keywordDictionary.get(id);
        }
    }

    int getShiftIndex() {
        if (shiftIndex == 0) {
            shiftIndex = codec.decode(MIN5LETTER_KEYWORD);
        }
        return shiftIndex;
    }
}
