package com.zimu.study.common.utils;//package com.wjh.study.common.utils;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.Enumeration;
//import java.util.zip.ZipException;
//
//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipFile;
//import org.apache.tools.zip.ZipOutputStream;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 打包工具类
// * 版权所有：易汇金信息服务有限公司
// * @ClassName: ZipUtils
// * @Package:com.ehking.commons.utils.zip
// * @author majinchao
// * @date 2015年7月14日 下午2:04:52
// */
//public class ZipUtils {
//
//	 static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);
//
//	private static final int BUFF_SIZE = 1024; // 1M Byte
//
//	private static final String DEFAULT_CHARTSET = "UTF-8";
//
//	/**
//	 * 批量压缩文件（夹）
//	 *
//	 * @param files
//	 *            要压缩的文件（夹）列表
//	 * @param zipFile
//	 *            生成的压缩文件
//	 * @throws IOException
//	 *             当压缩过程出错时抛出
//	 */
//	public static void zipFiles(File[] files, File zipFile) throws IOException {
//		zipFiles(files, zipFile, DEFAULT_CHARTSET);
//	}
//
//	/**
//	 * 批量压缩文件（夹）
//	 * @Title: zipFiles
//	 * @param files
//	 * @param zipFile
//	 * @param charset
//	 * @throws IOException
//	 * @return void
//	 * @author majinchao
//	 * @date 2017年7月5日 下午2:19:06
//	 */
//	public static void zipFiles(File[] files, File zipFile, String charSet)
//			throws IOException {
//		try {
//			ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(
//					new FileOutputStream(zipFile), BUFF_SIZE));
//			zipOut.setEncoding(charSet);
//			for (File f : files) {
//				handlerFile(zipFile.getAbsolutePath(), zipOut, f, "");
//			}
//			zipOut.close();
//		} catch (FileNotFoundException e) {
//			LOGGER.error("",e);
//			throw e;
//		} catch (IOException e) {
//			LOGGER.error("",e);
//			throw e;
//		}
//	}
//
//
//	private static void handlerFile(String zip, ZipOutputStream zipOut,
//			File srcFile, String path) throws IOException {
//		LOGGER.info(" begin to compression file[" + srcFile.getName()
//				+ "]");
//		if (!"".equals(path) && !path.endsWith(File.separator)) {
//			path += File.separator;
//		}
//		if (!srcFile.getPath().equals(zip)) {
//			if (srcFile.isDirectory()) {
//				File[] _files = srcFile.listFiles();
//				if (_files.length == 0) {
//					zipOut.putNextEntry(new ZipEntry(path + srcFile.getName()
//							+ File.separator));
//					zipOut.closeEntry();
//				} else {
//					for (File _f : _files) {
//						handlerFile(zip, zipOut, _f, path + srcFile.getName());
//					}
//				}
//			} else {
//				byte[] _byte = new byte[BUFF_SIZE];
//				InputStream _in = new FileInputStream(srcFile);
//				zipOut.putNextEntry(new ZipEntry(path + srcFile.getName()));
//				int len = 0;
//				while ((len = _in.read(_byte)) > 0) {
//					zipOut.write(_byte, 0, len);
//				}
//				_in.close();
//				zipOut.closeEntry();
//			}
//		}
//	}
//
//	/**
//	 * zip压缩功能测试. 将指定文件压缩后存到一压缩文件中
//	 * @Title: createFileToZip
//	 * @param sourceFileName
//	 * 			 所要压缩的文件名
//	 * @param zipFilename
//	 * 			 压缩后的文件名
//	 * @throws Exception
//	 * @return long
//	 * @author majinchao
//	 * @date 2017年7月6日 下午5:40:12
//	 */
//	public static long createFileToZip(String sourceFileName, String zipFilename) throws Exception {
//		return createFileToZip(sourceFileName, zipFilename, DEFAULT_CHARTSET);
//	}
//
//	/**
//	 * zip压缩功能测试. 将指定文件压缩后存到一压缩文件中
//	 * @Title: createFileToZip
//	 * @param sourceFileName
//	 * @param zipFilename
//	 * @param charSet
//	 * @throws Exception
//	 * @return long
//	 * @author majinchao
//	 * @date 2017年7月6日 下午6:07:42
//	 */
//	public static long createFileToZip(String sourceFileName, String zipFilename, String charSet)
//			throws Exception {
//
//		File sourceFile = new File(sourceFileName);
//		// 压缩文件名
//		File objFile = new File(zipFilename);
//		ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(objFile), BUFF_SIZE));
//		zos.setEncoding(charSet);
//		ZipEntry ze = null;
//
//		// 创建一个ZipEntry，并设置Name和其它的一些属性
//		ze = new ZipEntry(sourceFile.getName());
//		ze.setSize(sourceFile.length());
//		ze.setTime(sourceFile.lastModified());
//
//		// 将ZipEntry加到zos中，再写入实际的文件内容
//		zos.putNextEntry(ze);
//		zos.write(sourceFileName.getBytes(charSet));
//		zos.closeEntry();
//		zos.flush();
//		zos.close();
//		return objFile.length();
//	}
//
//	/* 删除文件 */
//	public static void delete(File file) {
//		if (file.exists() && file.isFile()) {
//			file.delete();
//			return;
//		}
//		if (file.isDirectory()) {
//			File[] childFiles = file.listFiles();
//			if (childFiles == null || childFiles.length == 0) {
//				// file.delete();
//				return;
//			}
//			for (int i = 0; i < childFiles.length; i++) {
//				delete(childFiles[i]);
//			}
//			// file.delete();
//		}
//	}
//
//	/**
//	 *
//	 * @Title: deleteD
//	 * @param sPath
//	 * @return boolean
//	 * @author majinchao
//	 * @date 2015年7月15日 下午6:58:04
//	 */
//	public static boolean deleteD(String sPath) {
//		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
//		if (!sPath.endsWith(File.separator)) {
//			sPath = sPath + File.separator;
//		}
//		File dirFile = new File(sPath);
//		// 如果dir对应的文件不存在，或者不是一个目录，则退出
//		if (!dirFile.exists() || !dirFile.isDirectory()) {
//			return false;
//		}
//		boolean flag = true;
//		// 删除文件夹下的所有文件(包括子目录)
//		File[] files = dirFile.listFiles();
//		for (int i = 0; i < files.length; i++) {
//			// 删除子文件
//			if (files[i].isFile()) {
//				flag = deleteFile(files[i].getAbsolutePath());
//				if (!flag)
//					break;
//			} // 删除子目录
//			else {
//				flag = deleteD(files[i].getAbsolutePath());
//				if (!flag)
//					break;
//			}
//		}
//		if (!flag)
//			return false;
//		// 删除当前目录
//		return dirFile.delete();
//
//	}
//
//	/**
//	 * 删除文件
//	 * @Title: deleteFile
//	 * @param sPath
//	 * @return boolean
//	 * @author majinchao
//	 * @date 2015年7月15日 下午6:55:34
//	 */
//	public static boolean deleteFile(String sPath) {
//		boolean flag = false;
//		File file = new File(sPath);
//		// 路径为文件且不为空则进行删除
//		if (file.isFile() && file.exists()) {
//			file.delete();
//			flag = true;
//		}
//		return flag;
//	}
//
//	/**
//	 * 解压缩zip包
//	 *
//	 * @param zipFilePath zip文件路径
//	 * @param targetPath 解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
//	 * @throws IOException
//	 */
//	public static void unzip(String zipFilePath, String targetPath) throws IOException {
//		OutputStream os = null;
//		InputStream is = null;
//		ZipFile zipFile = null;
//		try {
//			zipFile = new ZipFile(zipFilePath, DEFAULT_CHARTSET);
//			String directoryPath = "";
//			if (null == targetPath || "".equals(targetPath)) {
//				directoryPath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
//			} else {
//				directoryPath = targetPath;
//			}
//			//去掉directoryPath结尾的分隔符
//			if(directoryPath.substring(directoryPath.length()-1, directoryPath.length()).equals(File.separator)){
//			    directoryPath = directoryPath.substring(0, directoryPath.length()-1);
//			}
//
//			@SuppressWarnings("rawtypes")
//			Enumeration entryEnum = zipFile.getEntries();
//			if (null != entryEnum) {
//				ZipEntry zipEntry = null;
//				while (entryEnum.hasMoreElements()) {
//					zipEntry = (ZipEntry) entryEnum.nextElement();
////					if (zipEntry.isDirectory()) {
////						directoryPath = directoryPath + File.separator + zipEntry.getName();
////						LOGGER.info(directoryPath);
//////						continue;
////					}
//					if (zipEntry.getSize() > 0) {
//						// 文件
//						File targetFile = buildFile(directoryPath + File.separator + zipEntry.getName(), false);
//						os = new BufferedOutputStream(new FileOutputStream(targetFile));
//						is = zipFile.getInputStream(zipEntry);
//						byte[] buffer = new byte[4096];
//						int readLen = 0;
//						while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
//							os.write(buffer, 0, readLen);
//						}
//
//						os.flush();
//						os.close();
//					} else {
//						// 空目录
//						buildFile(directoryPath + File.separator + zipEntry.getName(), true);
//					}
//				}
//			}
//		} catch (IOException ex) {
//			throw ex;
//		} finally {
//			if (null != zipFile) {
//				zipFile = null;
//			}
//			if (null != is) {
//				is.close();
//			}
//			if (null != os) {
//				os.close();
//			}
//		}
//	}
//
//	/**
//	 *
//	 * 生产文件 如果文件所在路径不存在则生成路径
//	 *
//	 *
//	 *
//	 * @param fileName 文件名 带路径
//	 *
//	 * @param isDirectory 是否为路径
//	 *
//	 * @return
//	 *
//	 * @author yayagepei
//	 *
//	 * @date 2008-8-27
//	 */
//
//	public static File buildFile(String fileName, boolean isDirectory) {
//
//		File target = new File(fileName);
//
//		if (isDirectory) {
//
//			target.mkdirs();
//
//		} else {
//
//			if (!target.getParentFile().exists()) {
//
//				target.getParentFile().mkdirs();
//
//				target = new File(target.getAbsolutePath());
//
//			}
//
//		}
//
//		return target;
//
//	}
//
//	/**
//	 * 解压缩功能. 将zipFile文件解压到folderPath目录下.
//	 *
//	 * @throws Exception
//	 */
//	public synchronized static int upZipFile(File zipFile, String folderPath) throws ZipException, IOException {
//	    unzip(zipFile.getAbsolutePath(), folderPath);
//	    return 0;
////		return upZipFile(zipFile, folderPath, DEFAULT_CHARTSET_NAME_GETBYTE, DEFAULT_CHARTSET);
//	}
//
//	/**
//	 * 解压缩功能. 将zipFile文件解压到folderPath目录下.
//	 *
//	 * @throws Exception
//	 */
////	public synchronized static int upZipFile(File zipFile, String folderPath, String chartset)
////			throws ZipException, IOException {
////		return upZipFile(zipFile, folderPath, chartset, chartset);
////	}
//
//
////	private synchronized static int upZipFile(File zipFile, String folderPath, String chartset1, String chartset2)
////			throws ZipException, IOException {
////		// public static void upZipFile() throws Exception{
////		ZipFile zfile = new ZipFile(zipFile);
////		@SuppressWarnings("rawtypes")
////		Enumeration zList = zfile.getEntries();
////		ZipEntry ze = null;
////		byte[] buf = new byte[1024];
////		while (zList.hasMoreElements()) {
////			ze = (ZipEntry) zList.nextElement();
////			if (ze.isDirectory()) {
////				LOGGER.info("upZipFile", "ze.getName() = " + ze.getName());
////				String dirstr = folderPath + File.separator + ze.getName();
////				// dirstr.trim();
////				dirstr = new String(dirstr.getBytes(chartset1), chartset2);
////				LOGGER.info("upZipFile", "str = " + dirstr);
////				File f = new File(dirstr);
////				f.mkdir();
////				continue;
////			}
////			LOGGER.info("upZipFile", "ze.getName() = " + ze.getName());
////			OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
////			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
////			int readLen = 0;
////			while ((readLen = is.read(buf, 0, 1024)) != -1) {
////				os.write(buf, 0, readLen);
////			}
////			is.close();
////			os.close();
////		}
////		zfile.close();
////		return 0;
////	}
//
//	/**
//	 * 给定根目录，返回一个相对路径所对应的实际文件名.
//	 *
//	 * @param baseDir
//	 *            指定根目录
//	 * @param absFileName
//	 *            相对路径名，来自于ZipEntry中的name
//	 * @return java.io.File 实际的文件
//	 */
////	public static File getRealFileName(String baseDir, String absFileName) {
////		return getRealFileName(baseDir, absFileName, DEFAULT_CHARTSET_NAME_GETBYTE, DEFAULT_CHARTSET_NAME);
////	}
//
//	/**
//	 * 给定根目录，返回一个相对路径所对应的实际文件名.
//	 *
//	 * @param baseDir
//	 *            指定根目录
//	 * @param absFileName
//	 *            相对路径名，来自于ZipEntry中的name
//	 * @return java.io.File 实际的文件
//	 */
//	public static File getRealFileName(String baseDir, String absFileName, String chartset) {
//		return getRealFileName(baseDir, absFileName, chartset, chartset);
//	}
//
//	private static File getRealFileName(String baseDir, String absFileName, String chartset1, String chartset2) {
//		String[] dirs = absFileName.split("/");
//		File ret = new File(baseDir);
//		String substr = null;
//		if (dirs.length > 1) {
//			for (int i = 0; i < dirs.length - 1; i++) {
//				substr = dirs[i];
//				try {
//					// substr.trim();
//					substr = new String(substr.getBytes(chartset1), chartset2);
//
//				} catch (UnsupportedEncodingException e) {
//					LOGGER.error("",e);
//				}
//				ret = new File(ret, substr);
//
//			}
//			LOGGER.info("upZipFile", "1ret = " + ret);
//			if (!ret.exists())
//				ret.mkdirs();
//			substr = dirs[dirs.length - 1];
//			try {
//				// substr.trim();
//				substr = new String(substr.getBytes(chartset1), chartset2);
//				LOGGER.info("upZipFile", "substr = " + substr);
//			} catch (UnsupportedEncodingException e) {
//				LOGGER.error("",e);
//			}
//
//			ret = new File(ret, substr);
//			LOGGER.info("upZipFile", "2ret = " + ret);
//			return ret;
//		}
//		return ret;
//	}
//}
