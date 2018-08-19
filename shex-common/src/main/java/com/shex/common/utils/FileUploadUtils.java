/**
 * @(#)FileUtils.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.shex.common.utils;

//import org.csource.common.MyException;
//import org.csource.fastdfs.*;
//import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class FileUploadUtils {

    /*private static final Logger LOG              = LoggerFactory.getLogger(FileUploadUtils.class);
    private static final String DEFALUT_SUFFIX   = "temp";
    private static final String FILE_SEPARATOR   = "/";

    private static final String FASTDFS_ADDRESS = ConfigUtils.getString("upload_fastdfs_address");

    private static final String ACCESS_FASTDFS_ADDRESS = ConfigUtils.getString("access_fastdfs_address");

    public static String FASTDFS_PORT = ConfigUtils.getString("fastdfs_port");

    *//**
     * 上传文件的最大文件字节数(单位k)
     *//*
    private static final long   DEFAULT_MAX_SIZE = 2048;

    // 初始化
    static {
        initPro();
    }

    private static void initPro() {
        InetSocketAddress[] trackerServers = new InetSocketAddress[1];
        trackerServers[0] = new InetSocketAddress(FASTDFS_ADDRESS,
            Integer.valueOf(FASTDFS_PORT).intValue());
        ClientGlobal.setG_tracker_group(new TrackerGroup(trackerServers));

        ClientGlobal.setG_connect_timeout(2000);
        ClientGlobal.setG_network_timeout(30000);
        ClientGlobal.setG_anti_steal_token(false);
        ClientGlobal.setG_charset("UTF-8");
        ClientGlobal.setG_secret_key(null);
    }

    *//**
     * 获取 StorageClient1
     *//*
    public static StorageClient1 getStorageClient() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        storageServer = trackerClient.getStoreStorage(trackerServer);

        StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);

        return client1;
    }

    public static UploadResult uploadFile(MultipartFile multipartFile,
                                          List<String> allowExtends) throws IOException {
        if (multipartFile == null || multipartFile.getSize() == 0) {
            throw new IOException("上传文件不存在");
        }

        // 原始文件名
        String fileName = multipartFile.getOriginalFilename();
        if (StringUtils.isNullOrEmpty(fileName)) {
            throw new IOException("非法文件");
        }

        // 文件大小
        long fileSize = multipartFile.getSize();
        if (multipartFile.getSize() > Integer.parseInt(ConfigUtils.getString("upload.file.maxsize")) * 1024) {
            throw new IOException("上传文件不能超过  " + ConfigUtils.getString("upload.file.maxsize") + " K");
        }

        String fileExt = "";
        int suffixPos = fileName.lastIndexOf(".");
        if (suffixPos > 0) {
            fileExt = fileName.substring(suffixPos + 1);
        }
        if (!allowExtends.contains(fileExt.toLowerCase())) {
            throw new IOException("非法文件格式" + fileExt.toLowerCase());
        }

        // dfs 文件路径
        String dfsFilePath = getDfsPath(multipartFile.getInputStream(), fileExt);

        // 网络访问路径
        String fullRelative = fullRelative = ACCESS_FASTDFS_ADDRESS + "/" + dfsFilePath;;

        UploadResult ret = new UploadResult();
        ret.setDfsFilePath(dfsFilePath);
        ret.setFileName(fileName);
        ret.setFullRelative(fullRelative);
        ret.setFileSize(fileSize);

        LOG.error("upload file to server path success: " + fullRelative);

        return ret;
    }

    private static String getDfsPath(InputStream inputStream, String fileExt) throws IOException{
        StorageClient1 client1 = getStorageClient();

        // dfs 文件路径
        String dfsFilePath = "";

        try {
            byte[] file_buff = null;
            if (inputStream != null) {
                int len = inputStream.available();
                file_buff = new byte[len];
                inputStream.read(file_buff);
            }

            dfsFilePath = client1.upload_file1(file_buff, fileExt, null);


            inputStream.close();
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  dfsFilePath;
    }

    *//**
     * 上传图片
     * 
     * @return
     * @throws IOException
     *//*
    public static UploadResult uploadImage(MultipartFile multipartFile) throws IOException {

        if (multipartFile == null || multipartFile.getSize() == 0) {
            throw new IOException("上传文件不存在");
        }

        // 原始文件名
        String fileName = multipartFile.getOriginalFilename();
        if (StringUtils.isNullOrEmpty(fileName)) {
            throw new IOException("非法文件");
        }

        // 文件大小
        long fileSize = multipartFile.getSize();
        if (multipartFile.getSize() > Integer.parseInt(ConfigUtils.getString("upload.file.maxsize")) * 1024) {
            throw new IOException("上传文件不能超过  " + ConfigUtils.getString("upload.file.maxsize") + " K");
        }

        String fileExt = "";
        int suffixPos = fileName.lastIndexOf(".");
        if (suffixPos > 0) {
            fileExt = fileName.substring(suffixPos + 1);
        }

        // 格式判断
        if (!ConfigUtils.getString("upload.img.file_extension").contains(fileExt.toLowerCase())) {
            throw new IOException("非法文件格式" + fileExt.toLowerCase());
        }

        // dfs 文件路径
        String dfsFilePath = getDfsPath(multipartFile.getInputStream(), fileExt);

        // 网络访问路径
        String fullRelative = "http://"+ACCESS_FASTDFS_ADDRESS + "/" + dfsFilePath;

        UploadResult ret = new UploadResult();
        ret.setDfsFilePath(dfsFilePath);
        ret.setFileName(fileName);
        ret.setFullRelative(fullRelative);
        ret.setFileSize(fileSize);

        LOG.error("upload file to server path success: " + fullRelative);

        return ret;
    }

    *//**
     * 批量上传图片
     * 
     * @return
     * @throws IOException
     *//*
    public static List<UploadResult> uploadMultImages(MultipartFile[] multipartFiles) throws IOException {

        List<UploadResult> listResult = new ArrayList<UploadResult>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile == null || multipartFile.getSize() == 0) {
                throw new IOException("上传文件不存在");
            }

            // 原始文件名
            String fileName = multipartFile.getOriginalFilename();
            if (StringUtils.isNullOrEmpty(fileName)) {
                throw new IOException("非法文件");
            }

            // 文件大小
            long fileSize = multipartFile.getSize();
            if (multipartFile.getSize() > Integer.parseInt(ConfigUtils.getString("upload.file.maxsize")) * 1024) {
                throw new IOException("上传文件不能超过  " + ConfigUtils.getString("upload.file.maxsize") + " K");
            }

            String fileExt = "";
            int suffixPos = fileName.lastIndexOf(".");
            if (suffixPos > 0) {
                fileExt = fileName.substring(suffixPos + 1);
            }

            // 格式判断
            if (!ConfigUtils.getString("upload.img.file_extension").contains(fileExt.toLowerCase())) {
                throw new IOException("非法文件格式" + fileExt.toLowerCase());
            }

            // dfs 文件路径
            String dfsFilePath = getDfsPath(multipartFile.getInputStream(), fileExt);

            // 网络访问路径
            String fullRelative = fullRelative = ACCESS_FASTDFS_ADDRESS + "/" + dfsFilePath;;

            UploadResult ret = new UploadResult();
            ret.setDfsFilePath(dfsFilePath);
            ret.setFileName(fileName);
            ret.setFullRelative(fullRelative);
            ret.setFileSize(fileSize);
            listResult.add(ret);
        }

        return listResult;
    }

    *//**
     *
     * @param storagePath  文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败,0成功
     * @throws IOException
     * @throws Exception
     *//*
    public static Integer delete_file(String storagePath) throws IOException{
        int result=-1;
        StorageClient1 client1 = getStorageClient();
        try {
            result = client1.delete_file1(storagePath);
        } catch (MyException e) {
            e.printStackTrace();
        }

        return  result;
    }
*/

    public static class UploadResult {

        // dfs 文件路径
        private String dfsFilePath;

        // 网络访问路径
        private String fullRelative;

        // 原始文件名
        private String fileName;

        // 文件大小
        private long   fileSize;

        public String getDfsFilePath() {
            return dfsFilePath;
        }

        public void setDfsFilePath(String dfsFilePath) {
            this.dfsFilePath = dfsFilePath;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public String getFullRelative() {
            return fullRelative;
        }

        public void setFullRelative(String fullRelative) {
            this.fullRelative = fullRelative;
        }

    }

}
