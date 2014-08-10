package com.hhhy.web.client.thrift;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.hhhy.web.service.thrift.gencode.HhhyService;
/**
 * Created by lenovo on 8/10/2014.
 */
public class ThriftClient implements HhhyService.Iface{
//    private static final Logger logger = Logger.getLogger(ThriftClient.class);

    private static ThriftClient instance;
    private HhhyService.Client client;

    public static String serverHost;
    public static int serverPort;
    public TTransport transport;

    private static final int maxRetryTime = 3;

    public synchronized static ThriftClient getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("You must init BowClient first.");
        }
        return instance;
    }

    public synchronized static void init(String host, int port) throws TTransportException {
        if (instance != null) {
            throw new UnsupportedOperationException("Client has been init before. You can try reset(host,port) the client.");
        }
        reset(host, port);
    }

    public synchronized static void reset(String host, int port) throws TTransportException {
        if (instance != null) {
            if (instance.transport != null) {
                instance.transport.close();
            }
            instance.client = null;
            instance.transport = null;
        } else {
            instance = new ThriftClient();
        }
        serverHost = host;
        serverPort = port;
//        logger.info("client reset to " + host + ":" + port);
        instance.transport = new TSocket(host, port);
        instance.transport.open();
        TProtocol protocol = new TBinaryProtocol(instance.transport);
        instance.client = new HhhyService.Client(protocol);
    }

    public synchronized static void reset() throws TTransportException {
        if (instance == null || serverHost == null || serverPort == 0) {
            throw new UnsupportedOperationException("You must init BowClient first.");
        }
        reset(serverHost, serverPort);
    }

    private synchronized void handleTException(int tryTime, TException e) throws TException {
        if (tryTime > maxRetryTime) {
            throw e;
        }
        reset();
        try {
            this.wait(300);
        } catch (InterruptedException e1) {
//            logger.warn(e1.getMessage());
        }
    }

    private void checkConnect() throws TTransportException {
        if (!transport.isOpen()) {
//            logger.warn("transport is close, reset client.");
            reset();
        }
    }

    @Override
    public void addArticle(String jsonString) throws TException {
        checkConnect();
        int tryTime = 0;
        while (true){
            try{
//                System.out.println("clinet:addPost");
                client.addArticle(jsonString);
                break;
            }catch (TException e){
                tryTime++;
//                logger.warn("add new item failed, " + e.getMessage());
                handleTException(tryTime, e);
            }
        }
    }

    @Override
    public String getKeywords() throws TException {
        checkConnect();
        int tryTime = 0;
        while (true) {
            try {
//                System.out.println("client:get");
                return client.getKeywords();
            }catch (TException e){
                tryTime++;
//                logger.warn("getkeywords, " + e.getMessage());
                handleTException(tryTime, e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            ThriftClient.init("localhost",12306);
            ThriftClient client = ThriftClient.getInstance();
            System.out.println("test start");
            client.addArticle("test");
            System.out.println(client.getKeywords());
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }


}
