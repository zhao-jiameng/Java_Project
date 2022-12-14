import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;



public class HDFStest{
    public URI uri;
    public Configuration conf;
    public String user;
    public FileSystem fs;
    /**
     * @Before注解的方法会在所有@Test标注的方法前执行
     * 在.org.junit包里
     */
    @Before
    public void init() throws IOException, InterruptedException {
        //创建文件系统对象
        uri= URI.create("hdfs://hadoop101:8020");
        conf= new Configuration();
        conf.set("dfs.permissions","false");
        //conf.set("配置项名称","值");        添加配置
        String user="zjm";
        FileSystem fs= FileSystem.get(uri,conf,user);
    }

    @Test
    public void mkdir() throws IOException, InterruptedException {
        FileSystem fs= FileSystem.get(uri,conf,user);
        boolean mkdirs=fs.mkdirs(new Path("/zjm"));
    }
//    @Test
//    public void testUpload() throws IOException {
//        /**
//         * 上传文件
//         * @param delSrc：是否剪切
//         * @param overwrite：是否覆盖
//         * @param src 文件路径
//         * @param dst hadoop目录
//         * @throws IOException IO failure
//         */
//        fs.copyFromLocalFile(false,true,
//                new Path("G:\\hadoop从入门到入狱"),
//                new Path("/hadoop从入门到入狱"));
//    }


//    /**
//     *  文件下载
//     * @param delSrc
//     *          是否删除源文件
//     * @param src
//     *         下载对象
//     * @param dst
//     *          目标目录
//     * @param useRawLocalFileSystem
//     *         开启cvc校验（true不开启）
//     *
//     * @throws IOException for any IO error
//     */
//    @Test
//    public void copyToLocalFile(boolean delSrc, Path src, Path dst,
//                                boolean useRawLocalFileSystem) throws IOException{
//        fs.copyToLocalFile(false,new Path("下载对象"),new Path("目标目录"),true);
//    }
//
//    /**
//     * 改名和移动
//     * @param src   文件路径
//     * @param dst   目标路径||改名
//     * @throws IOException on failure
//     * @return boolean
//     */
//    public void rename(Path src, Path dst) throws IOException{      //改名和移动
//        boolean a= fs.rename(new Path("文件路径"),new Path("改名||路径"));
//    }
//
//    /**
//     * 查看文件目录详情
//     * @param f 查看路径
//     * @param recursive 是否递归
//     *
//     * @return 迭代器
//     *
//     * @throws FileNotFoundException when the path does not exist;
//     * @throws IOException see specific implementation
//     */
//    @Test
//    public void listFiles(final Path f, final boolean recursive)
//            throws FileNotFoundException, IOException {
//        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), false);
//        while (listFiles.hasNext()){
//            LocatedFileStatus filestatus=listFiles.next();
//            filestatus.getPath();       //获取文件路径
//            filestatus.getPermission(); //获取文件权限
//            filestatus.getOwner();      //属主
//            filestatus.getGroup();      //属组
//            filestatus.getLen();        //大小
//            filestatus.getReplication();//块大小
//            filestatus.getBlockLocations();//块位置（Arrys.toString输出）
//
//        }
//    }
//    /**
//     * 文件和文件夹判断
//     * @param f given path 文件或目录路径
//     * @return the statuses of the files/directories in the given patch
//     * @throws FileNotFoundException when the path does not exist
//     * @throws IOException see specific implementation
//     */
//    @Test
//    public void listStatus(Path f) throws FileNotFoundException, IOException{
//        FileStatus[] listStatus = fs.listStatus(new Path("路径"));
//        for (FileStatus status : listStatus) {
//            if (status.isDirectory()){
//                System.out.println("目录"+status.getPath());
//            }else {
//                System.out.println("文件"+status.getPath());
//            }
//        }
//    }

//
//    /** Delete a file.
//     * 删除文件或目录
//     * @param f 删除路径
//     * @param recursive 递归（删除目录开启）
//     * true, the directory is deleted else throws an exception. In
//     * case of a file the recursive can be set to either true or false.
//     * @return  boolean
//     * @throws IOException IO failure
//     */
//    @Test
//    public void delete(Path f, boolean recursive) throws IOException{
//        boolean a=fs.delete(new Path("路径"),"递归");
//    }
    @Test
    public void testTrach() throws IOException {
        //配置回收站
        conf.set("fs.trash.interval","1");
        conf.set("fs.trash.checkpoint.interval","1");
        //创建回收站对象
        Trash trash=new Trash(fs,conf);
        trash.moveToTrash(new Path("/README.txt"));
    }
    /**
     * @After注解的方法会在所有@Test标注的方法执行结束后执行
     */
    @After
    public void close() throws IOException, InterruptedException {
        FileSystem fs= FileSystem.get(uri,conf,user);
        fs.close();
    }

}