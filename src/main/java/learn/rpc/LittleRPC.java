package learn.rpc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhiyong.ma on 2016/3/10.
 */
public class LittleRPC {

    static Client client = new Client();

    public static void main(String[] args) throws Throwable {

        //receive();
        send();


    }

    static void receive() throws IOException {
        Server.start();
    }

    static void send() throws IOException {
        Local1 proxy = RPC.getProxy(Local1.class);

        String str1 = proxy.method1();
        System.out.println(str1);
        String str2 = proxy.method2();
        System.out.println(str2);
    }

    static interface Local1 {
        String method1();

        String method2();

    }

    static class Impl1 implements Local1 {
        @Override
        public String method1() {
            return "I am method1 in server";
        }

        @Override
        public String method2() {
            return "I am method2 in server";
        }
    }

    static class RPC {

        public static <T> T getProxy(final Class<T> origin) {

            return (T) Proxy.newProxyInstance(RPC.class.getClassLoader(), new Class[]{origin}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return client.invoke(origin, method, args);
                }
            });
        }
    }

    static class Client {

        public <T> Object invoke(Class<T> origin, Method method, Object[] args) throws IOException {
            Socket socket = new Socket("127.0.0.1", 8888);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(origin.getCanonicalName());
            dos.writeUTF(method.getName());
            dos.flush();

            //socket.shutdownOutput();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String result = dis.readUTF();

            socket.close();
            return result;
        }
    }

    static class Server {
        static Map<String, Object> impls = new HashMap<String, Object>();

        static {
            impls.put(Local1.class.getCanonicalName(), new Impl1());
        }

        static void start() throws IOException {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 8888);

            ServerSocket server = new ServerSocket();
            server.bind(address);
            System.out.println("Monitoring in 127.0.0.1:8888 ....");

            while (true) {
                try {
                    Socket socket = server.accept();

                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    String className = dis.readUTF();
                    String methodName = dis.readUTF();

                    Object impl = impls.get(className);

                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    if (null == impl) {
                        dos.writeUTF("No impl set for " + className);
                    } else {
                        try {
                            String result = (String) impl.getClass().getMethod(methodName).invoke(impl);

                            dos.writeUTF(result);

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        static <T> T getImpl(String origin) {
            return null;
        }
    }

}
