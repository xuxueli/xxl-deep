package com.xxl.permission.service.helper;

import com.xxl.permission.core.constant.CommonDic;
import com.xxl.permission.core.model.XxlPermissionMenu;
import com.xxl.permission.core.util.JacksonUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限菜单，操作相关
 * @author xuxueli
 */
public class MenuModuleHelper {
	
	/**
	 * 权限编码ID (服务器拦截使用)
	 * @param menus
	 * @return
	 */
	public static String initMenePermissionNums(List<XxlPermissionMenu> menus){
		Set<Integer> set = new HashSet<Integer>();
		for (XxlPermissionMenu item : menus) {
			if (item.getPermessionId() > 0) {
				set.add(item.getPermessionId());
			}
		}
		StringBuffer sb = new StringBuffer();
		for (Integer item : set) {
			sb.append(item);
			sb.append(",");
		}
		return sb.toString();
	}
	
	/**
	 * 权限菜单,JSON tree
	 * @param menus
	 * @return
	 */
	public static String initMenuModuleJson(List<XxlPermissionMenu> menus){
		List<XxlPermissionMenu> menuModule = new ArrayList<XxlPermissionMenu>();
		for (XxlPermissionMenu module : menus) {
			if (module.getParentId() == CommonDic.BIZ_MENU_ID) {
				initSubMenus(module, menus);
				menuModule.add(module);
			}
		}
		return JacksonUtil.writeValueAsString(menuModule);
	}
	/** 获取子菜单  */
	private static void initSubMenus(XxlPermissionMenu father, List<XxlPermissionMenu> menus) {
		for (XxlPermissionMenu sub : menus) {
			if (father.getId() == sub.getParentId()) {
				father.getSubMenus().add(sub);
				initSubMenus(sub, menus );
			}
		}
	}

	/**
	 * 权限菜单,JSON tree (easyui tree格式修正)
	 * @param menus
	 * @return
	 */
	public static String initMenuModuleEasyJson(List<XxlPermissionMenu> menus){
		String str = initMenuModuleJson(menus);
		str = str.replace("menuId", "id");
		str = str.replace("name", "text");
		str = str.replace("subMenus", "children");
		return str;
		
	}

	public static void main(String[] args) {
		XxlPermissionMenu menu1 = new XxlPermissionMenu();
		menu1.setId(1);
		menu1.setParentId(0);
		menu1.setName("menu1");
		menu1.setPermessionUrl("menu1");
		menu1.setPermessionId(33);

		XxlPermissionMenu menu11 = new XxlPermissionMenu();
		menu11.setId(11);
		menu11.setParentId(1);
		menu11.setName("menu11");
		menu11.setPermessionUrl("menu11");

		XxlPermissionMenu menu12 = new XxlPermissionMenu();
		menu12.setId(12);
		menu12.setParentId(1);
		menu12.setName("menu12");
		menu12.setPermessionUrl("menu12");
		
		List<XxlPermissionMenu> list = new ArrayList<XxlPermissionMenu>();
		list.add(menu1);
		list.add(menu11);
		list.add(menu12);
		
		System.out.println(initMenePermissionNums(list));
		System.out.println(initMenuModuleJson(list));
	}
}
