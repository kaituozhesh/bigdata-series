package com.ktz.sh.bigdata.service.impl;

import com.ktz.sh.bigdata.service.MeetElasticsearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Iterator;

/**
 * @ClassName : MeetElasticsearchServiceImpl
 * @Description :
 * @Author : kaituozhesh
 * @Date: 2020-04-19 19:19
 * @Version: 1.0.0
 */
@Slf4j
@Service
public class MeetElasticsearchServiceImpl implements MeetElasticsearchService {

    private RestClient restClient;

    @Override
    @PostConstruct
    public void initEs() {
        // RestClient在初始化时，唯一需要的参数就是客户端将与之通信的一个或多个主机作为HttpHost实例提供
        // 对于RestClient类而言，是线程安全的。在理想情况下，它与使用它的应用程序具有相同的生命周期。因此，当不再需要时，应该关闭它，以便释放它使用的所有资源及底层HTTP客户机实例及线程
        RestClientBuilder builder = RestClient.builder(new HttpHost("192.168.159.129", 9200, "http"));

        // 设置每个请求需要发送的默认请求头，就不需要在每个请求中指定它们
        Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
        builder.setDefaultHeaders(defaultHeaders);

        // 设置一个监听器，该监听器在每次节点失败时都会收到通知
        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {

            }
        });

        // 设置节点选择器
        builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);

        // 设置超时时间
        builder.setRequestConfigCallback(builder1 -> builder1.setSocketTimeout(10000));

        restClient = builder.build();
        log.info("Elasticsearch init in service.");
    }

    @Override
    public void closeEs() {
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String executeRequest() {
        Request request = new Request("GET", "/");
        try {
            Response response = restClient.performRequest(request);
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeEs();
        return "Get result failed!";
    }

    @Override
    public String executeRequestAsync() {
        Request request = new Request("GET", "/");
        restClient.performRequestAsync(request, new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("fail");
            }
        });
        closeEs();
        return "Get result failed!";
    }

    @Override
    public void parseElasticsearchResponse() {
        try {
            Response response = restClient.performRequest(new Request("GET", "/"));
            // 已执行请求的信息
            RequestLine requestLine = response.getRequestLine();
            // Host返回的信息
            HttpHost host = response.getHost();
            // 响应状态行，从中可以解析状态代码
            int statusCode = response.getStatusLine().getStatusCode();
            // 响应头，也可以通过getheader（string）按名称获取
            Header[] headers = response.getHeaders();
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("parse Elasticsearch Response, responseBody is : " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initEsWithTimeout() {
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        builder.setRequestConfigCallback(builder1 -> builder1.setSocketTimeout(10000).setSocketTimeout(60000));
    }

    @Override
    public void setNodeSelector() {
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        builder.setNodeSelector(new NodeSelector() {
            @Override
            public void select(Iterable<Node> nodes) {
                boolean foundOne = false;
                for (Node node : nodes) {
                    String rackId = node.getAttributes().get("rack_id").get(0);
                    if ("targetId".equals(rackId)) {
                        foundOne = true;
                        break;
                    }
                }
                if (foundOne) {
                    Iterator<Node> iterator = nodes.iterator();
                    while (iterator.hasNext()) {
                        Node node = iterator.next();
                        String rackId = node.getAttributes().get("rack_id").get(0);
                        if ("targetId".equals(rackId) == false) {
                            iterator.remove();
                        }
                    }
                }
            }
        });
    }


}
