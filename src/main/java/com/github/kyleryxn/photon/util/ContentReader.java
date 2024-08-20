package com.github.kyleryxn.photon.util;

import com.github.kyleryxn.photon.crawler.content.PageContent;

public interface ContentReader {

    PageContent readContent(String path);

}
