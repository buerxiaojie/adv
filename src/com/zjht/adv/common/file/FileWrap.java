package com.zjht.adv.common.file;

import java.io.File;
import java.io.FileFilter;
import java.sql.Timestamp;
import java.util.*;

import org.apache.commons.io.FilenameUtils;

import com.zjht.adv.common.images.ImageUtils;


/**
 * 文件包装类
 * 
 * 用于前台显示文件信息
 * 
 * @author liufang
 * 
 */
public class FileWrap{
	/**
	 * 可编辑的后缀名
	 */
    public static final String EDITABLE_EXT[] = {"html", "htm",
    	      "css", "js", "txt"};
    
    private File file;
    private String rootPath;
    private FileFilter filter;
	private List<FileWrap> child;
    private String name;
    
	/**
	 * 构造器
	 * 
	 * @param file
	 *            待包装的文件
	 */
    public FileWrap(File file){
        this(file, null);
    }
    
	/**
	 * 构造器
	 * 
	 * @param file
	 *            待包装的文件
	 * @param rootPath
	 *            根路径，用于计算相对路径
	 */
    public FileWrap(File file, String rootPath){
        this(file, rootPath, null);
    }
    
	/**
	 * 构造器
	 * 
	 * @param file
	 *            待包装的文件
	 * @param rootPath
	 *            根路径，用于计算相对路径
	 * @param filter
	 *            文件过滤器，应用于所有子文件
	 */
    public FileWrap(File file, String rootPath, FileFilter filter){
        this.file = file;
        this.rootPath = rootPath;
        this.filter = filter;
    }

    public static boolean allowEdit(String s){
        s = s.toLowerCase();
        String as[] = EDITABLE_EXT;
        int i = as.length;
        for(int j = 0; j < i; j++){
            String s1 = as[j];
            if(s1.equals(s))
                return true;
        }
        return false;
    }
    
	/**
	 * 扩展名
	 * 
	 * @return
	 */
	public String getExtension() {
		return FilenameUtils.getExtension(file.getName());
	}
	
	/**
	 * 最后修改时间。长整型(long)。
	 * 
	 * @return
	 */
    public Date getLastModified(){
        return new Timestamp(file.lastModified());
    }
    
	/**
	 * 是否图片
	 * 
	 * @return
	 */
    public boolean isImage(){
        if(isDirectory()){
            return false;
        } else{
            String ext = getExtension();
            return ImageUtils.isImage(ext);
        }
    }

	/**
	 * 是否可编辑
	 * 
	 * @return
	 */
	public boolean isEditable() {
		if (isDirectory()) {
			return false;
		}
		String ext = getExtension().toLowerCase();
		for (String s : EDITABLE_EXT) {
			if (s.equals(ext)) {
				return true;
			}
		}
		return false;
	}

    public String getName(){
        return name == null ? file.getName() : name;
    }

	/**
	 * 是否目录
	 * 
	 * @return
	 */
    public boolean isDirectory(){
        return file.isDirectory();
    }

	/**
	 * 是否文件
	 * 
	 * @return
	 */
    public boolean isFile(){
        return file.isFile();
    }

	/**
	 * 文件大小，单位KB。
	 * 
	 * @return
	 */
    public long getSize(){
        return file.length() / 1024L + 1L;
    }

    public String getRelPath(){
        String s = file.getAbsolutePath();
        String s1 = s.substring(rootPath.length());
        return s1.replace(File.separator, "/");
    }

	/**
	 * 获得文件的图标名称
	 * <ul>
	 * <li>directory = folder</li>
	 * <li>jpg,jpeg = jpg</li>
	 * <li>gif = gif</li>
	 * <li>html,htm = html</li>
	 * <li>swf = swf</li>
	 * <li>txt = txt</li>
	 * <li>其他 = unknow</li>
	 * </ul>
	 * 
	 * @return
	 */
    public String getIco(){
        if(file.isDirectory()){
            return "folder";
        }
        String ext = getExtension().toLowerCase();
        if(ext.equals("jpg") || ext.equals("jpeg")){
            return "jpg";
        }else if(ext.equals("gif")){
            return "gif";
        }else if(ext.equals("html") || ext.equals("htm")){
            return "html";
        }else if(ext.equals("swf")){
            return "swf";
        }else if(ext.equals("txt")){
            return "txt";
        }else{
            return "unknow";
        }
    }

	/**
	 * 获得下级目录
	 * 
	 * 如果没有指定下级目录，则返回文件夹自身的下级文件，并运用FileFilter。
	 * 
	 * @return
	 */
    public List<FileWrap>  getChild(){
        if(this.child != null){
            return child;
        }
        File[] files;
        if(filter == null){
        	files = getFile().listFiles();
        }else{
        	files = getFile().listFiles(filter);
        }
        List<FileWrap> list = new ArrayList<FileWrap>();
        if(files != null){
			Arrays.sort(files, new FileComparator());
			for (File f : files) {
				FileWrap fw = new FileWrap(f, rootPath, filter);
				list.add(fw);
			}
        }
        return list;
    }

	/**
	 * 获得被包装的文件
	 * 
	 * @return
	 */
    public File getFile(){
        return file;
    }

	/**
	 * 设置待包装的文件
	 * 
	 * @param file
	 */
    public void setFile(File file1){
        file = file1;
    }

	/**
	 * 指定名称
	 * 
	 * @param name
	 */
    public void setName(String name){
        this.name = name;
    }

	/**
	 * 指定下级目录
	 * 
	 * @param child
	 */
    public void setChild(List<FileWrap> child){
        this.child = child;
    }
    
	/**
	 * 文件比较器，文件夹靠前排。
	 * 
	 * @author liufang
	 * 
	 */
    public static class FileComparator implements Comparator<File>{
    	
    	 public FileComparator() { }

        @Override
		public int compare(File file1, File file2){
            if(file1.isDirectory() && !file2.isDirectory())
                return -1;
            if(!file1.isDirectory() && file2.isDirectory())
                return 1;
            else
                return file1.compareTo(file2);
        }

//        public volatile int compare(Object obj, Object obj1){
//            return compare((File)obj, (File)obj1);
//        }
    }

}
