package com.magooup.learn.net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by zhiyong.ma on 2016/3/17.
 */
public class LearnURI {

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        URL url = new URL("http://192.168.100.23:8080/resource/resource/video@local?keyword=&sf=&so=&ds=&de=&site=&pn=1&ps=&fresh=0");
        URI uri = url.toURI();

        printURIFields(uri);
    }

    static void printURIFields(URI uri) {
        System.out.println(uri);

        System.out.println(String.format("getScheme(): %s",uri.getScheme()));
        System.out.println(String.format("getAuthority(): %s",uri.getAuthority()));
        System.out.println(String.format("getFragment(): %s",uri.getFragment()));
        System.out.println(String.format("getHost(): %s",uri.getHost()));
        System.out.println(String.format("getPath(): %s",uri.getPath()));
        System.out.println(String.format("getPort(): %s",uri.getPort()));
        System.out.println(String.format("getRawAuthority(): %s",uri.getRawAuthority()));
        System.out.println(String.format("getRawFragment(): %s",uri.getRawFragment()));
        System.out.println(String.format("getQuery(): %s",uri.getQuery()));
        System.out.println(String.format("getRawPath(): %s",uri.getRawPath()));
        System.out.println(String.format("getRawQuery(): %s",uri.getRawQuery()));
        System.out.println(String.format("getRawSchemeSpecificPart(): %s",uri.getRawSchemeSpecificPart()));
        System.out.println(String.format("getRawUserInfo(): %s",uri.getRawUserInfo()));
        System.out.println(String.format("getSchemeSpecificPart(): %s",uri.getSchemeSpecificPart()));

    }

}
