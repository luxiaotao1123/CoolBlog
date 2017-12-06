package cn.blog.test.NioTest;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class MyNioClient {
    private Selector selector;          //创建一个选择器
    private final static int port = 8686;
    private final static int BUF_SIZE = 1024;

    private void  initClient() throws IOException {
        this.selector = Selector.open();
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress(port));

        clientChannel.finishConnect();
    }

    public static void main(String[] args) throws IOException {
        MyNioClient myNioClient = new MyNioClient();
        myNioClient.initClient();
    }
}
