package com.github.kyleryxn.photon.crawler.util;

import java.util.Set;

public interface URLComparator {

    boolean isDuplicate(Set<String> urls, String newUrl);

}
