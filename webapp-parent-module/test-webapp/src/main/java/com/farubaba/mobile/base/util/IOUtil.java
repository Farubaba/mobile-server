package com.farubaba.mobile.base.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author violet
 * @date 2018/7/1 12:58
 */

public class IOUtil {

    private static final int MIN_FREE_SDCARD_SPACE = 100;//100M
    private static final String TAG = "FileUtil";
    private static final String DOT = ".";
    private static final String FILE_PATH_SEPERATOR = File.separator;
    private static final String UTF_8 = "utf-8";
    private static File internalFileDir;
    private static File internalCacheDir;
    private static File externalCustomFileDir;
    private static File appCustomExternalDir;

    public static File createFile(String path){
    	File file = new File(path);
    	if(!file.exists()){
    		if(!file.getParentFile().exists()){
        		file.getParentFile().mkdirs();
        	}
    		try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	return file;
    }
    /**
     * delete file and child files
     *
     * @param file
     */
    public static void deleteFiles(File file) {
        try {
            grant(file.getAbsolutePath());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if(files != null){
                    for (int i = 0; i < files.length; i++) {
                        deleteFiles(files[i]);
                    }
                }
            } else {
                boolean hasDelete = file.delete();
                //LogManager.getInstance().d(TAG, "hasDeleted = "+ hasDelete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void grant(String filePath) throws IOException{
        String command = "chmod 777 " + filePath;
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command);
    }

    public static String getFileNameFromUrl(String url){
        if(url ==null || !url.contains("/")){
            return "";
        }else{
            return url.substring(url.lastIndexOf("/"));
        }
    }

    public static boolean isExists(String filePath){
        File file = new File(filePath);
        return isExists(file);
    }

    public static boolean isExists(File file){
        if(file != null && file.exists()){
            return true;
        }
        return false;
    }

    public static boolean deleteFilesByName(final File directory, final String prefix){
        if(isExists(directory) && directory.isDirectory()){
            File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    if(directory.equals(dir) && filename.startsWith(prefix)){
                        return true;
                    }
                    return false;
                }
            });
            if(files != null){
                for(File file : files){
                    return file.delete();
                }

            }
        }
        return true;
    }


    /**
     * 使用大小为{@link C.SizeUnits#FILE_DOWNLOAD_BUFFER_SIZE}的buffer来写入数据，适用于大文件写入。
     * 默认不使用追加模式append = false；
     *
     * @param inputStream
     * @param targetFile
     * @return
     * @throws IOException
     */
    public static File writeToBigSizeFile(InputStream inputStream, File targetFile) throws IOException {
        return writeToFile(inputStream, targetFile, false, 64 * 1024);
    }

    /**
     * 使用大小为{@link C.SizeUnits#DEFAULT_BUFFER_SIZE}的buffer来写入数据，适用于图片或者小文件写入。
     * 默认不使用追加模式append = false；
     * @param inputStream
     * @param targetFile
     * @return
     * @throws IOException
     */
    public static File writeToImageSizeFile(InputStream inputStream, File targetFile) throws IOException {
        return writeToFile(inputStream, targetFile, false, 16 * 1024);
    }


    /**
     * 使用大小为{@link C.SizeUnits#MIN_BUFFER_SIZE}的buffer来写入数据，适用于JSON数据等小文本写入。
     * 默认不使用追加模式append = false；
     * @param inputStream
     * @param targetFile
     * @return
     * @throws IOException
     */
    public static File writeToJsonSizeFile(InputStream inputStream, File targetFile) throws IOException {
        return writeToFile(inputStream, targetFile, false, 8 * 1024);
    }

    /**
     * 文件写入工具方法
     * @param inputStream
     * @param targetFile
     * @param append
     * @param bufferSize
     * @return 返回更改后的文件。{@link File#length()}可以获得更改后的文件字节（bytes）长度。
     * @throws IOException
     */
    public static File writeToFile(InputStream inputStream,  File targetFile, boolean append, int bufferSize) throws IOException {
        AtomicInteger counter = new AtomicInteger(0);
        boolean fileExists;
        FileOutputStream fileOutputStream = null;
        int perReadLen = 0;//每次读取字节数
        long totalReadLen = 0L;//总共读取字节数,long类型，对应file.length()方法返回值。

        if(inputStream != null && targetFile != null){
            if(!targetFile.exists()){
                fileExists = targetFile.createNewFile();
            }else{
                fileExists = true;
            }

            if(fileExists){
                fileOutputStream = new FileOutputStream(targetFile, append);
                checkMinBufferSize(bufferSize);
                byte[] buffer = new byte[bufferSize];

                while(hasMoreContent(perReadLen = inputStream.read(buffer))){
                    //LogManager.getInstance().d(counter.incrementAndGet());
                    fileOutputStream.write(buffer, 0 , perReadLen);
                    totalReadLen += perReadLen;
                }
                fileOutputStream.flush(); 
                counter.set(0);
            }else{
                //LogManager.getInstance().d("file not exists and createNewFile failed without exception. do nothing!!!");
            }
        }
        return targetFile;
    }


    public static File writeToFileWithStatus(InputStream inputStream, long totalFileLength, File targetFile, boolean append, int bufferSize) throws IOException {
        AtomicInteger counter = new AtomicInteger(0);
        boolean fileExists;
        FileOutputStream fileOutputStream = null;
        int perReadLen = 0;//每次读取字节数
        long totalReadLen = 0;//总共读取字节数,long类型，对应file.length()方法返回值。


        if(inputStream != null && targetFile != null){
            if(!targetFile.exists()){
                fileExists = targetFile.createNewFile();
            }else{
                fileExists = true;
            }

            if(fileExists){
                fileOutputStream = new FileOutputStream(targetFile, append);
                checkMinBufferSize(bufferSize);
                byte[] buffer = new byte[bufferSize];

                while(hasMoreContent(perReadLen = inputStream.read(buffer))){
                    //LogManager.getInstance().d(counter.incrementAndGet());
                    fileOutputStream.write(buffer, 0 , perReadLen);
                    totalReadLen += perReadLen;
                    //fileOutputStream.flush(); //
                }
                counter.set(0);
            }else{
                //LogManager.getInstance().d("file not exists and createNewFile failed without exception. do nothing!!!");
            }
        }
        return targetFile;
    }

    /**
     * config buffer size, buffer size should not less than 8KB. default is 16KB.
     * @param bufferSize
     * @return
     */
    public static int checkMinBufferSize(int bufferSize){
        if(bufferSize < 8 * 1024 ){
            bufferSize = 8 * 1024;
        }
        return bufferSize;
    }

    /**
     * 判断文件是否已经读取完成。
     * @param readLength
     * @return
     */
    public static boolean hasMoreContent(int readLength){
        if(readLength != -1){
            return true;
        }
        return false;
    }
    
    /**
     * 读取指定 totalLengthToRead 长度的数据，放入内存中，最后一次性返回成字符串。
     * 该方法不适合读取大量数据，会造成内存开销增大的问题
     * @param in 
     * @param totalLengthToRead 传入大于0的值，如果小于或者等于0，则读取全部
     * @return
     * @throws IOException
     */
    public static String readIntoMemery(InputStream in,int totalLengthToRead){
    	BufferedInputStream bis = new BufferedInputStream(in);
    	//所有数据都写到内存中
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	String returnString = "";
    	int byteSize = 8 * 1024;
    	byte[] b = new byte[byteSize];
    	int totalReadLen = 0;
    	int perLen=0;
    	try{
    		if(totalLengthToRead <= 0){//读取全部数据
    			readAll(bis, b, bos);
    			returnString = new String(bos.toByteArray(),UTF_8);
    		}else{
    			//读取totalLengthToRead长度数据
        		if(totalLengthToRead <= byteSize){
            		b = new byte[totalLengthToRead];
            		perLen = bis.read(b);
            		returnString = new String(b, UTF_8);
            	}else{
            		//b = new byte[byteSize];//默认值
            		while((perLen = bis.read(b)) != -1){
                		totalReadLen += perLen;
                		int nextReadLength = needReadLength(totalReadLen, totalLengthToRead, byteSize);
                		//处理本轮读取到的数据
                		bos.write(b,0,perLen);
                		//读取完毕，退出
                		if(nextReadLength <= 0){
                			break;
                		}else{
                			//剩下需要读取的长度，小于或者等于byteSize，都将长度设置成nextReadLength
                    		if(nextReadLength < byteSize){
                    			b = new byte[nextReadLength];
                    		}
                		}
            		}
            		returnString = new String(bos.toByteArray(),UTF_8);
            	}
        	}
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.closeIO(bos);
			CloseUtil.closeIO(bis);
			CloseUtil.closeIO(in);
		}
    	System.out.println("读取到内中的数据："+returnString);
    	return returnString;
    }
    
    public static ByteArrayOutputStream readAll(InputStream in, byte[] b, ByteArrayOutputStream bos) throws IOException{
    	int len = 0;
    	while((len = in.read(b)) != -1){
    		bos.write(b,0,len);
    	}
    	return bos;
    }
    
    private static int needReadLength(int totalReadLen, int totalLengthToRead, int byteSize){
    	int remainLen = totalLengthToRead - totalReadLen;
    	if(remainLen <= 0){
    		return 0;
    	}else{
    		if(remainLen >=byteSize){
        		return byteSize;
        	}else{
        		return remainLen;
        	}	
    	}
    }
   
}
