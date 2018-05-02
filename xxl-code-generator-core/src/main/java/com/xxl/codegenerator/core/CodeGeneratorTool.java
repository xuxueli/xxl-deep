package com.xxl.codegenerator.core;

import com.xxl.codegenerator.core.model.ClassInfo;
import com.xxl.codegenerator.core.util.TableParseUtil;

import java.io.IOException;

/**
 * code generate tool
 *
 * @author xuxueli 2018-04-25 16:29:58
 */
public class CodeGeneratorTool {

	/**
	 * process Table Into ClassInfo
	 *
	 * @param tableSql
	 * @return
	 */
	public static ClassInfo processTableIntoClassInfo(String tableSql) throws IOException {
		return TableParseUtil.processTableIntoClassInfo(tableSql);
	}

}