package org.example.lib;

import java.io.IOException;
import java.util.ArrayList;

public interface JSONFetch {

    String getJson(String url) throws IOException, InterruptedException;

    <T> T fetch(String url, Class<T> tClass) throws IOException, InterruptedException;

    <T> ArrayList<T> fetchArrayList(String url, Class<T> tClass) throws IOException, InterruptedException;
}
