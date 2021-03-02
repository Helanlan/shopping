package com.gg.util;

import com.gg.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * fastdfs文件管理：（文件上传，删除，下载，信息文件获取，storage信息获取，tracker信息获取）
 */
public class FastDFSUtil {
    /**
     * 加载tracker连接信息
     */
    static {
        try {
            String path = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * 1、创建tracker访问的客户端对象trackerclient
     * 2、通过trackerclient访问trackerserver服务，获取连接信息
     * 3、通过trackerserver的连接信息可以获取storage的连接信息，创建storageclient对象存储storage的连接信息
     * 4、通过storageclient访问storage，实现文件上传，并且获取文件上传后的存储信息
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());

        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient = new TrackerClient();

        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();


        StorageServer storageServer = null;
        //3、通过trackerserver的连接信息可以获取storage的连接信息，创建storageclient对象存储storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);

        //4、通过storageclient访问storage，实现文件上传，并且获取文件上传后的存储信息
        //参数说明：1、文件上传的字节数组，2、文件扩展名，附加参数
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);

        return uploads;
    }


    /**
     * 获取文件信息
     * @param groupName 文件的组名  group1
     * @param remoteFileName  文件的存储路劲名字  M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg
     * 1、创建tracker访问的客户端对象trackerclient
     * 2、通过trackerclient访问trackerserver服务，获取连接信息
     * 3、通过trackerserver的连接信息可以获取storage的连接信息，创建storageclient对象存储storage的连接信息
     */
    public static FileInfo getFile(String groupName,String remoteFileName) throws IOException, MyException {
        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient=new TrackerClient();
        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //3、通过trackerserver的连接信息可以获取storage的连接信息，创建storageclient对象存储storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
        return fileInfo;
    }


    /**
     * 文件下载
     * @param groupName 文件的组名  group1
     * @param remoteFileName 文件的存储路劲名字  M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg
     * @throws IOException
     * @throws MyException
     */
    public static InputStream downloadFile(String groupName,String remoteFileName) throws IOException, MyException {
        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient=new TrackerClient();
        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //3、通过trackerserver的连接信息可以获取storage的连接信息，创建storageclient对象存储storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(bytes);
    }


    /**
     * 文件删除
     * @param groupName 文件的组名  group1
     * @param remoteFileName 文件的存储路劲名字  M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg
     * @throws IOException
     * @throws MyException
     */
    public static void deleteFile(String groupName,String remoteFileName) throws IOException, MyException {
        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient=new TrackerClient();
        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //3、通过trackerserver的连接信息可以获取storage的连接信息，创建storageclient对象存储storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        storageClient.delete_file(groupName, remoteFileName);
    }

    /**
     * 获取storage的信息
     * @return
     * @throws IOException
     */
    public static StorageServer getStorage() throws IOException {
        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient=new TrackerClient();
        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        System.out.println(storeStorage.getInetSocketAddress().getHostName());
        System.out.println(storeStorage.getInetSocketAddress().getAddress());
        System.out.println(storeStorage.getInetSocketAddress().getHostString());
        System.out.println(storeStorage.getInetSocketAddress().getPort());
        System.out.println(storeStorage.getStorePathIndex());
        return storeStorage;
    }

    /**
     * 获取storeage的ip和端口信息
     * @param groupName 文件的组名  group1
     * @param remoteFileName 文件的存储路劲名字  M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg
     * @return
     * @throws IOException
     */
    public static StorageServer getServerInfo(String groupName, String remoteFileName) throws IOException {
        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient=new TrackerClient();
        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer server = trackerClient.getFetchStorage(trackerServer, groupName, remoteFileName);
        System.out.println(server.getInetSocketAddress());
        System.out.println(server.getStorePathIndex());
        return server;
    }

    /**
     * 获取storeage的ip和端口信息
     * @param groupName 文件的组名  group1
     * @param remoteFileName 文件的存储路劲名字  M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getServerInfo2(String groupName, String remoteFileName) throws IOException {
        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient=new TrackerClient();
        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        ServerInfo[] groups = trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
        return groups;
    }

    public static String getTrackerInfo() throws IOException {
        //1、创建tracker访问的客户端对象trackerclient
        TrackerClient trackerClient=new TrackerClient();
        //2、通过trackerclient访问trackerserver服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int port = ClientGlobal.getG_tracker_http_port();
        return "http://"+ip+":"+port;
    }









    public static void main(String[] args) throws IOException, MyException {
        FileInfo fileInfo =getFile("group1","M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg");
        System.out.println(fileInfo.getSourceIpAddr()+","+fileInfo.getFileSize());

        /*//文件下载
        InputStream inputStream = downloadFile("group1", "M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg");
        FileOutputStream outputStream =new FileOutputStream("C:/Users/ll/Desktop/1.jpg");
        byte[] bytes =new byte[1024];
        while (inputStream.read(bytes)!=-1){
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();*/


        //文件删除
        //deleteFile("group1","M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg");


        //获取storage的信息
        getStorage();

        System.out.println("=====================");

        //取storeage的ip和端口信息
        getServerInfo("group1","M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg");
        System.out.println("=====================");
        ServerInfo[] groups = getServerInfo2("group1", "M00/00/00/wKgXhGAmgvmAIyiKAAFXyPK5Evs112.jpg");
        for (ServerInfo group:groups){
            System.out.println(group.getIpAddr());
            System.out.println(group.getPort());
        }

        System.out.println("-----------------");
        String trackerInfo = getTrackerInfo();
        System.out.println(trackerInfo);
    }
}
