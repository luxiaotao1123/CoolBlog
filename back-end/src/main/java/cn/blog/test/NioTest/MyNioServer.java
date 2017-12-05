package cn.blog.test.NioTest;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyNioServer {
    private Selector selector;          //创建一个选择器
    private static ExecutorService executorService = Executors.newCachedThreadPool();       //创建一个线程池

    private void startServer() throws IOException {
        //创建通道管理器对象selector
        this.selector=Selector.open();

        //创建一个通道对象channel
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);       //将通道设置为非阻塞
        channel.socket().bind(new InetSocketAddress(8686));       //将通道绑定在8686端口

        //将上述的通道管理器和通道绑定，并为该通道注册OP_ACCEPT事件
        //注册事件后，当该事件到达时，selector.select()会返回（一个key），如果该事件没到达selector.select()会一直阻塞
        SelectionKey key = channel.register(selector,SelectionKey.OP_ACCEPT);

        while (true){       //轮询
            selector.select();          //这是一个阻塞方法，一直等待直到有数据可读，返回值是key的数量（可以有多个）
            Set keys = selector.selectedKeys();         //如果channel有数据了，将生成的key访入keys集合中
            Iterator iterator = keys.iterator();        //得到这个keys集合的迭代器
            while (iterator.hasNext()){             //使用迭代器遍历集合
                SelectionKey currentKey = (SelectionKey) iterator.next();       //得到集合中的一个key实例
                iterator.remove();          //拿到当前key实例之后记得在迭代器中将这个元素删除，非常重要，否则会出错

                if (currentKey.isAcceptable()){         //判断当前key所代表的channel是否在Acceptable状态，如果是就进行接收
                    //doAccept(currentKey);
                    System.out.println(currentKey);
                    ServerSocketChannel serverChannel = (ServerSocketChannel) currentKey.channel();
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));
                    clientChannel.register(selector,SelectionKey.OP_READ);
                }
            }
        }
    }

    /*public void listen() throws IOException {
        System.out.println("服务端启动成功");

    }

    private static void doAccept(SelectionKey selectionKey){
        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();       //代替一个服务端server,非阻塞
        SocketChannel clientChannel = null;             //代替一个客户端client，非阻塞
        try {
            clientChannel = serverChannel.accept();
            clientChannel.configureBlocking(false);

            //将生成的key绑定到selector上，并且告诉selector，对读感兴趣，当selector发现这个channel准备好的时候，就能给线程通知
            SelectionKey key = clientChannel.register(selector,SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            key.attach(echoClient);

            InetAddress address = clientChannel.socket().getInetAddress();
            System.out.println("已连接的客户端地址为："+address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doRead(SelectionKey selectionKey){
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
        int len;

        try {
            len = channel.read(byteBuffer);
        } catch (IOException e) {
            System.out.println("读取失败");
            e.printStackTrace();
        }
        byteBuffer.flip();      //重置缓存区
    }

    static class EchoClient{
        private LinkedList<ByteBuffer> buffers;
        EchoClient(){
            buffers = new LinkedList<ByteBuffer>();
        }
        public LinkedList<ByteBuffer> getOutputQueue(){
            return buffers;
        }
        public void setQueue(ByteBuffer byteBuffer){
            buffers.addFirst(byteBuffer);
        }
    }*/

    public static void main(String[] args) throws IOException {
        new MyNioServer().startServer();
    }
}
