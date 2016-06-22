package cn.scau.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtil {

	public static File createFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
				return file;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// 删除文件
	public static boolean delFile(String filePath) {

		File file = new File(filePath);
		// 判断file是否存在
		if (!file.exists())
			return true;

		return file.delete();
	}

	// 判断文件是否存在
	public static boolean fileExists(String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			return false;
		return true;
	}

	// 批量删除图片使用线程
	public static void delFiles(final List<String> filePaths) {
		if (!ObjectUtil.isEmptyList(filePaths))
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < filePaths.size(); i++) {
						File file = new File(filePaths.get(i));
						if (file.exists())
							file.delete();
					}
				}
			}).start();
	}
}
