    package org.FZU.Utils;

    import org.FZU.pojo.HttpResponse;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.net.HttpURLConnection;
    import java.net.URL;

    public class HttpUtil {
        public HttpResponse getUrl(String baseUrl, String method) {
            HttpResponse response = new HttpResponse();
            HttpURLConnection connection = null;
            try {
                URL url = new URL(baseUrl);
                connection = (HttpURLConnection) url.openConnection();
//                请求方法
                connection.setRequestMethod(method);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                response.setStatusCode(connection.getResponseCode());
                response.setHeaders(connection.getHeaderFields());

                StringBuilder content = new StringBuilder();
                InputStream inputStream;
                //获取返回信息
                if (response.getStatusCode() >= 200 && response.getStatusCode() < 400) {
                    inputStream = connection.getInputStream();
                } else {
                    inputStream = connection.getErrorStream();
                }
                //组合返回内容
                if (inputStream != null) {
                    BufferedReader reader =new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    response.setContent(content.toString());
                }

            } catch (IOException e) {
                System.out.println("获取url失败");
                response.setContent("");
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return response;
        }
    }
