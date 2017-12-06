package cn.blog.test.NioTest;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class MyNioClient {
    private Selector selector;          //创建一个选择器
    private final static int port = 8686;
    private final static int BUF_SIZE = 1024;
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private void  initClient() throws IOException {
        this.selector = Selector.open();
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress(port));
        clientChannel.register(selector, SelectionKey.OP_CONNECT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isConnectable()){
                    doConnect(key);
                }
            }
        }
    }

    public void doConnect(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (clientChannel.isConnectionPending()){
            clientChannel.finishConnect();
        }
        clientChannel.configureBlocking(false);
        clientChannel.write(byteBuffer.wrap(new String("你好服务端！").getBytes()));
        clientChannel.register(key.selector(),SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException {
        MyNioClient myNioClient = new MyNioClient();
        myNioClient.initClient();
    }
}
