package com.zuoer.netflix.bench.base.utils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.Assert;

import com.zuoer.netflix.bench.base.error.ErrorCode;
import com.zuoer.netflix.bench.base.exception.BenchRuntimeException;

/**
 * 有关 <code>Class</code> 处理的工具类。
 * 
 * @author chenbug
 *
 * @version $Id: ClassUtils.java, v 0.1 2018年10月25日 下午7:21:57 chenbug Exp $
 */
public class ClassUtils {

	public static final ClassUtils INSTANCE = new ClassUtils();

	/**
	 * 构造一个实例
	 * 
	 * @param clasz
	 * @return
	 */
	public static <T> T newInstance(Class<T> clasz) {
		try {
			return clasz.newInstance();
		} catch (Exception e) {
			throw new BenchRuntimeException(new ErrorCode("SYSTEM_ERROR", "系统错误"), "实例化类异常,class=" + clasz);
		}
	}

	public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterClasses, Object[] parameterValues) {
		Assert.notNull(clazz, "Class must not be null");
		try {
			Constructor<T> c = clazz.getConstructor(parameterClasses);
			return c == null ? null : c.newInstance(parameterValues);
		} catch (Exception ex) {
			return null;
		}
	}

	public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... paramTypes) {
		Assert.notNull(clazz, "Class must not be null");
		try {
			return clazz.getConstructor(paramTypes);
		} catch (NoSuchMethodException ex) {
			return null;
		}
	}

	/**
	 * 构造一个实例
	 * 
	 * @param clasz
	 * @return
	 */
	public static Object newInstance(String clasz) {
		try {
			return Class.forName(clasz).newInstance();
		} catch (Exception e) {
			throw new BenchRuntimeException(new ErrorCode("SYSTEM_ERROR", "系统错误"), "实例化类异常,class=" + clasz);
		}
	}
	/*
	 * ========================================================================= = ==
	 */
	/* 常量和singleton。 */
	/*
	 * ========================================================================= = ==
	 */

	/** 资源文件的分隔符： <code>'/'</code>。 */
	public static final char RESOURCE_SEPARATOR_CHAR = '/';

	/** Java类名的分隔符： <code>'.'</code>。 */
	public static final char PACKAGE_SEPARATOR_CHAR = '.';

	/** Java类名的分隔符： <code>"."</code>。 */
	public static final String PACKAGE_SEPARATOR = String.valueOf(PACKAGE_SEPARATOR_CHAR);

	/** 内联类的分隔符： <code>'$'</code>。 */
	public static final char INNER_CLASS_SEPARATOR_CHAR = '$';

	/** 内联类的分隔符： <code>"$"</code>。 */
	public static final String INNER_CLASS_SEPARATOR = String.valueOf(INNER_CLASS_SEPARATOR_CHAR);

	/** 所有类的信息表，包括父类, 接口, 数组的维数等信息。 */
	private static Map TYPE_MAP = Collections.synchronizedMap(new WeakHashMap());

	/*
	 * ========================================================================= = ==
	 */
	/* 取得类名和package名的方法。 */
	/*
	 * ========================================================================= = ==
	 */

	/**
	 * 取得对象所属的类的直观类名。
	 * 
	 * <p>
	 * 相当于 <code>object.getClass().getName()</code> ，但不同的是，该方法用更直观的方式显示数组类型。 例如：
	 * 
	 * <pre>
	 *  int[].class.getName() = &quot;[I&quot; ClassUtil.getClassName(int[].class) = &quot;int[]&quot;
	 * 
	 *  Integer[][].class.getName() = &quot;[[Ljava.lang.Integer;&quot; ClassUtil.getClassName(Integer[][].class) = &quot;java.lang.Integer[][]&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 对于非数组的类型，该方法等效于 <code>Class.getName()</code> 方法。
	 * </p>
	 * 
	 * <p>
	 * 注意，该方法所返回的数组类名只能用于显示给人看，不能用于 <code>Class.forName</code> 操作。
	 * </p>
	 * 
	 * @param object
	 *            要显示类名的对象
	 * 
	 * @return 用于显示的直观类名，如果原类名为空或非法，则返回 <code>null</code>
	 */
	public static String getClassNameForObject(Object object) {
		if (object == null) {
			return null;
		}

		return getClassName(object.getClass().getName(), true);
	}

	public static Method getMethodIfAvailable(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(methodName, "Method name must not be null");
		try {
			return clazz.getMethod(methodName, paramTypes);
		} catch (NoSuchMethodException ex) {
			return null;
		}
	}

	public static Iterable<Class<?>> getClassHierarchy(Class<?> clazz) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
			classes.add(c);
		}
		return classes;
	}

	/**
	 * 取得直观的类名。
	 * 
	 * <p>
	 * 相当于 <code>clazz.getName()</code> ，但不同的是，该方法用更直观的方式显示数组类型。 例如：
	 * 
	 * <pre>
	 *  int[].class.getName() = &quot;[I&quot; ClassUtil.getClassName(int[].class) = &quot;int[]&quot;
	 * 
	 *  Integer[][].class.getName() = &quot;[[Ljava.lang.Integer;&quot; ClassUtil.getClassName(Integer[][].class) = &quot;java.lang.Integer[][]&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 对于非数组的类型，该方法等效于 <code>Class.getName()</code> 方法。
	 * </p>
	 * 
	 * <p>
	 * 注意，该方法所返回的数组类名只能用于显示给人看，不能用于 <code>Class.forName</code> 操作。
	 * </p>
	 * 
	 * @param clazz
	 *            要显示类名的类
	 * 
	 * @return 用于显示的直观类名，如果原始类为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static String getClassName(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}

		return getClassName(clazz.getName(), true);
	}

	/**
	 * 取得直观的类名。
	 * 
	 * <p>
	 * <code>className</code> 必须是从 <code>clazz.getName()</code> 所返回的合法类名。该方法用更直观的方式显示数组类型。 例如：
	 * 
	 * <pre>
	 *  int[].class.getName() = &quot;[I&quot; ClassUtil.getClassName(int[].class) = &quot;int[]&quot;
	 * 
	 *  Integer[][].class.getName() = &quot;[[Ljava.lang.Integer;&quot; ClassUtil.getClassName(Integer[][].class) = &quot;java.lang.Integer[][]&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 对于非数组的类型，该方法等效于 <code>Class.getName()</code> 方法。
	 * </p>
	 * 
	 * <p>
	 * 注意，该方法所返回的数组类名只能用于显示给人看，不能用于 <code>Class.forName</code> 操作。
	 * </p>
	 * 
	 * @param className
	 *            要显示的类名
	 * 
	 * @return 用于显示的直观类名，如果原类名为 <code>null</code> ，则返回 <code>null</code> ，如果原类名是非法的，则返回原类名
	 */
	public static String getClassName(String className) {
		return getClassName(className, true);
	}

	/**
	 * 取得直观的类名。
	 * 
	 * @param className
	 *            类名
	 * @param processInnerClass
	 *            是否将内联类分隔符 <code>'$'</code> 转换成 <code>'.'</code>
	 * 
	 * @return 直观的类名，或 <code>null</code>
	 */
	private static String getClassName(String className, boolean processInnerClass) {
		if (StringUtils.isEmpty(className)) {
			return className;
		}

		if (processInnerClass) {
			className = className.replace(INNER_CLASS_SEPARATOR_CHAR, PACKAGE_SEPARATOR_CHAR);
		}

		int length = className.length();
		int dimension = 0;

		// 取得数组的维数，如果不是数组，维数为0
		for (int i = 0; i < length; i++, dimension++) {
			if (className.charAt(i) != '[') {
				break;
			}
		}

		// 如果不是数组，则直接返回
		if (dimension == 0) {
			return className;
		}

		// 确保类名合法
		if (length <= dimension) {
			return className; // 非法类名
		}

		// 处理数组
		StringBuffer componentTypeName = new StringBuffer();

		switch (className.charAt(dimension)) {
		case 'Z':
			componentTypeName.append("boolean");
			break;

		case 'B':
			componentTypeName.append("byte");
			break;

		case 'C':
			componentTypeName.append("char");
			break;

		case 'D':
			componentTypeName.append("double");
			break;

		case 'F':
			componentTypeName.append("float");
			break;

		case 'I':
			componentTypeName.append("int");
			break;

		case 'J':
			componentTypeName.append("long");
			break;

		case 'S':
			componentTypeName.append("short");
			break;

		case 'L':

			if ((className.charAt(length - 1) != ';') || (length <= (dimension + 2))) {
				return className; // 非法类名
			}

			componentTypeName.append(className.substring(dimension + 1, length - 1));
			break;

		default:
			return className; // 非法类名
		}

		for (int i = 0; i < dimension; i++) {
			componentTypeName.append("[]");
		}

		return componentTypeName.toString();
	}

	/**
	 * 取得指定对象所属的类的短类名，不包括package名。
	 * 
	 * <p>
	 * 此方法可以正确显示数组和内联类的名称。
	 * </p>
	 * 
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 *  ClassUtil.getShortClassNameForObject(Boolean.TRUE) = &quot;Boolean&quot; ClassUtil.getShortClassNameForObject(new Boolean[10]) = &quot;Boolean[]&quot; ClassUtil.getShortClassNameForObject(new int[1][2]) = &quot;int[][]&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param object
	 *            要查看的对象
	 * 
	 * @return 短类名，如果对象为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static String getShortClassNameForObject(Object object) {
		if (object == null) {
			return null;
		}

		return getShortClassName(object.getClass().getName());
	}

	/**
	 * 取得短类名，不包括package名。
	 * 
	 * <p>
	 * 此方法可以正确显示数组和内联类的名称。
	 * </p>
	 * 
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 *  ClassUtil.getShortClassName(Boolean.class) = &quot;Boolean&quot; ClassUtil.getShortClassName(Boolean[].class) = &quot;Boolean[]&quot; ClassUtil.getShortClassName(int[][].class) = &quot;int[][]&quot; ClassUtil.getShortClassName(Map.Entry.class) = &quot;Map.Entry&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param clazz
	 *            要查看的类
	 * 
	 * @return 短类名，如果类为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static String getShortClassName(Class clazz) {
		if (clazz == null) {
			return null;
		}

		return getShortClassName(clazz.getName());
	}

	/**
	 * 取得类名，不包括package名。
	 * 
	 * <p>
	 * 此方法可以正确显示数组和内联类的名称。
	 * </p>
	 * 
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 *  ClassUtil.getShortClassName(Boolean.class.getName()) = &quot;Boolean&quot; ClassUtil.getShortClassName(Boolean[].class.getName()) = &quot;Boolean[]&quot; ClassUtil.getShortClassName(int[][].class.getName()) = &quot;int[][]&quot; ClassUtil.getShortClassName(Map.Entry.class.getName()) = &quot;Map.Entry&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param className
	 *            要查看的类名
	 * 
	 * @return 短类名，如果类名为空，则返回 <code>null</code>
	 */
	public static String getShortClassName(String className) {
		if (StringUtils.isEmpty(className)) {
			return className;
		}

		// 转换成直观的类名
		className = getClassName(className, false);

		char[] chars = className.toCharArray();
		int lastDot = 0;

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == PACKAGE_SEPARATOR_CHAR) {
				lastDot = i + 1;
			} else if (chars[i] == INNER_CLASS_SEPARATOR_CHAR) {
				chars[i] = PACKAGE_SEPARATOR_CHAR;
			}
		}

		return new String(chars, lastDot, chars.length - lastDot);
	}

	/**
	 * 取得指定对象所属的类的package名。
	 * 
	 * <p>
	 * 对于数组，此方法返回的是数组元素类型的package名。
	 * </p>
	 * 
	 * @param object
	 *            要查看的对象
	 * 
	 * @return package名，如果对象为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static String getPackageNameForObject(Object object) {
		if (object == null) {
			return null;
		}

		return getPackageName(object.getClass().getName());
	}

	/**
	 * 取得指定类的package名。
	 * 
	 * <p>
	 * 对于数组，此方法返回的是数组元素类型的package名。
	 * </p>
	 * 
	 * @param clazz
	 *            要查看的类
	 * 
	 * @return package名，如果类为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static String getPackageName(Class clazz) {
		if (clazz == null) {
			return null;
		}

		return getPackageName(clazz.getName());
	}

	/**
	 * 取得指定类名的package名。
	 * 
	 * <p>
	 * 对于数组，此方法返回的是数组元素类型的package名。
	 * </p>
	 * 
	 * @param className
	 *            要查看的类名
	 * 
	 * @return package名，如果类名为空，则返回 <code>null</code>
	 */
	public static String getPackageName(String className) {
		if (StringUtils.isEmpty(className)) {
			return null;
		}

		// 转换成直观的类名
		className = getClassName(className, false);

		int i = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);

		if (i == -1) {
			return "";
		}

		return className.substring(0, i);
	}

	/*
	 * ========================================================================= = ==
	 */
	/* 取得类名和package名的resource名的方法。 */
	/*                                                                              */
	/* 和类名、package名不同的是，resource名符合文件名命名规范，例如： */
	/* java/lang/String.class */
	/* com/bench/commons/lang */
	/* etc. */
	/*
	 * ========================================================================= = ==
	 */

	/**
	 * 取得对象所属的类的资源名。
	 * 
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 * ClassUtil.getClassNameForObjectAsResource(&quot;This is a string&quot;) = &quot;java/lang/String.class&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param object
	 *            要显示类名的对象
	 * 
	 * @return 指定对象所属类的资源名，如果对象为空，则返回<code>null</code>
	 */
	public static String getClassNameForObjectAsResource(Object object) {
		if (object == null) {
			return null;
		}

		return object.getClass().getName().replace(PACKAGE_SEPARATOR_CHAR, RESOURCE_SEPARATOR_CHAR) + ".class";
	}

	/**
	 * 取得指定类的资源名。
	 * 
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 * ClassUtil.getClassNameAsResource(String.class) = &quot;java/lang/String.class&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param clazz
	 *            要显示类名的类
	 * 
	 * @return 指定类的资源名，如果指定类为空，则返回<code>null</code>
	 */
	public static String getClassNameAsResource(Class clazz) {
		if (clazz == null) {
			return null;
		}

		return clazz.getName().replace(PACKAGE_SEPARATOR_CHAR, RESOURCE_SEPARATOR_CHAR) + ".class";
	}

	/**
	 * 取得指定类的资源名。
	 * 
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 * ClassUtil.getClassNameAsResource(&quot;java.lang.String&quot;) = &quot;java/lang/String.class&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param className
	 *            要显示的类名
	 * 
	 * @return 指定类名对应的资源名，如果指定类名为空，则返回<code>null</code>
	 */
	public static String getClassNameAsResource(String className) {
		if (className == null) {
			return null;
		}

		return className.replace(PACKAGE_SEPARATOR_CHAR, RESOURCE_SEPARATOR_CHAR) + ".class";
	}

	/**
	 * 取得指定对象所属的类的package名的资源名。
	 * 
	 * <p>
	 * 对于数组，此方法返回的是数组元素类型的package名。
	 * </p>
	 * 
	 * @param object
	 *            要查看的对象
	 * 
	 * @return package名，如果对象为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static String getPackageNameForObjectAsResource(Object object) {
		if (object == null) {
			return null;
		}

		return getPackageNameForObject(object).replace(PACKAGE_SEPARATOR_CHAR, RESOURCE_SEPARATOR_CHAR);
	}

	/**
	 * 取得指定类的package名的资源名。
	 * 
	 * <p>
	 * 对于数组，此方法返回的是数组元素类型的package名。
	 * </p>
	 * 
	 * @param clazz
	 *            要查看的类
	 * 
	 * @return package名，如果类为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static String getPackageNameAsResource(Class clazz) {
		if (clazz == null) {
			return null;
		}

		return getPackageName(clazz).replace(PACKAGE_SEPARATOR_CHAR, RESOURCE_SEPARATOR_CHAR);
	}

	/**
	 * 取得指定类名的package名的资源名。
	 * 
	 * <p>
	 * 对于数组，此方法返回的是数组元素类型的package名。
	 * </p>
	 * 
	 * @param className
	 *            要查看的类名
	 * 
	 * @return package名，如果类名为空，则返回 <code>null</code>
	 */
	public static String getPackageNameAsResource(String className) {
		if (className == null) {
			return null;
		}

		return getPackageName(className).replace(PACKAGE_SEPARATOR_CHAR, RESOURCE_SEPARATOR_CHAR);
	}

	/*
	 * ========================================================================= = ==
	 */
	/* 取得类的信息，如父类, 接口, 数组的维数等。 */
	/*
	 * ========================================================================= = ==
	 */

	/**
	 * 取得指定维数的 <code>Array</code>类.
	 * 
	 * @param componentType
	 *            数组的基类
	 * @param dimension
	 *            维数，如果小于 <code>0</code> 则看作 <code>0</code>
	 * 
	 * @return 如果维数为0, 则返回基类本身, 否则返回数组类，如果数组的基类为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static Class getArrayClass(Class componentType, int dimension) {
		if (dimension <= 0) {
			return componentType;
		}

		if (componentType == null) {
			return null;
		}

		return Array.newInstance(componentType, new int[dimension]).getClass();
	}

	/**
	 * 取得数组元素的类型。
	 * 
	 * @param type
	 *            要查找的类
	 * 
	 * @return 如果是数组, 则返回数组元素的类型, 否则返回 <code>null</code>
	 */
	public static Class getArrayComponentType(Class type) {
		if (type == null) {
			return null;
		}

		return getTypeInfo(type).getArrayComponentType();
	}

	/**
	 * 取得数组的维数。
	 * 
	 * @param clazz
	 *            要查找的类
	 * 
	 * @return 数组的维数. 如果不是数组, 则返回 <code>0</code> ，如果数组为 <code>null</code> ，是返回 <code>-1</code>
	 */
	public static int getArrayDimension(Class clazz) {
		if (clazz == null) {
			return -1;
		}

		return getTypeInfo(clazz).getArrayDimension();
	}

	/**
	 * 取得指定类的所有父类。
	 * 
	 * <p>
	 * 对于一个 <code>Class</code> 实例，如果它不是接口，也不是数组，此方法依次列出从该类的父类开始直到 <code>Object</code> 的所有类。
	 * </p>
	 * 
	 * <p>
	 * 例如 <code>ClassUtil.getSuperclasses(java.util.ArrayList.class)</code> 返回以下列表：
	 * 
	 * <ol>
	 * <li><code>java.util.AbstractList</code></li>
	 * <li><code>java.util.AbstractCollection</code></li>
	 * <li><code>java.lang.Object</code></li>
	 * </ol>
	 * </p>
	 * 
	 * <p>
	 * 对于一个接口，此方法返回一个空列表。
	 * </p>
	 * 
	 * <p>
	 * 例如<code>ClassUtil.getSuperclasses(java.util.List.class)</code>将返回一个空列表。
	 * </p>
	 * 
	 * <p>
	 * 对于一个数组，此方法返回一个列表，列出所有component类型的父类的相同维数的数组类型。 例如： <code>ClassUtil.getSuperclasses(java.util.ArrayList[][].class)</code> 返回以下列表：
	 * 
	 * <ol>
	 * <li><code>java.util.AbstractList[][]</code></li>
	 * <li><code>java.util.AbstractCollection[][]</code></li>
	 * <li><code>java.lang.Object[][]</code></li>
	 * <li><code>java.lang.Object[]</code></li>
	 * <li><code>java.lang.Object</code></li>
	 * </ol>
	 * 
	 * 注意，原子类型及其数组，将被转换成相应的包装类来处理。 例如： <code>ClassUtil.getSuperclasses(int[][].class)</code> 返回以下列表：
	 * 
	 * <ol>
	 * <li><code>java.lang.Number[][]</code></li>
	 * <li><code>java.lang.Object[][]</code></li>
	 * <li><code>java.lang.Object[]</code></li>
	 * <li><code>java.lang.Object</code></li>
	 * </ol>
	 * </p>
	 * 
	 * @param clazz
	 *            要查找的类
	 * 
	 * @return 所有父类的列表，如果指定类为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static List<Class<?>> getSuperclasses(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}

		return getTypeInfo(clazz).getSuperclasses();
	}

	/**
	 * 取得指定类的所有接口。
	 * 
	 * <p>
	 * 对于一个 <code>Class</code> 实例，如果它不是接口，也不是数组，此方法依次列出从该类的父类开始直到 <code>Object</code> 的所有类。
	 * </p>
	 * 
	 * <p>
	 * 例如 <code>ClassUtil.getInterfaces(java.util.ArrayList.class)</code> 返回以下列表：
	 * 
	 * <ol>
	 * <li><code>java.util.List</code></li>
	 * <li><code>java.util.Collection</code></li>
	 * <li><code>java.util.RandomAccess</code></li>
	 * <li><code>java.lang.Cloneable</code></li>
	 * <li><code>java.io.Serializable</code></li>
	 * </ol>
	 * </p>
	 * 
	 * <p>
	 * 对于一个数组，此方法返回一个列表，列出所有component类型的接口的相同维数的数组类型。 例如： <code>ClassUtil.getInterfaces(java.util.ArrayList[][].class)</code> 返回以下列表：
	 * 
	 * <ol>
	 * <li><code>java.util.List[][]</code></li>
	 * <li><code>java.util.Collection[][]</code></li>
	 * <li><code>java.util.RandomAccess[][]</code></li>
	 * <li><code>java.lang.Cloneable[][]</code></li>
	 * <li><code>java.io.Serializable[][]</code></li>
	 * </ol>
	 * </p>
	 * 
	 * <p>
	 * 注意，原子类型及其数组，将被转换成相应的包装类来处理。 例如： <code>ClassUtil.getInterfaces(int[][].class)</code> 返回以下列表：
	 * 
	 * <ol>
	 * <li><code>java.lang.Comparable[][]</code></li>
	 * <li><code>java.io.Serializable[][]</code></li>
	 * </ol>
	 * </p>
	 * 
	 * @param clazz
	 *            要查找的类
	 * 
	 * @return 所有接口的列表，如果指定类为 <code>null</code> ，则返回 <code>null</code>
	 */
	public static List<Class<?>> getInterfaces(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}

		return new ArrayList<Class<?>>(getTypeInfo(clazz).getInterfaces());
	}

	/**
	 * 判断指定类是否为内联类。
	 * 
	 * @param clazz
	 *            要查找的类
	 * 
	 * @return 如果是，则返回 <code>true</code>
	 */
	public static boolean isInnerClass(Class clazz) {
		if (clazz == null) {
			return false;
		}

		return StringUtils.contains(clazz.getName(), INNER_CLASS_SEPARATOR_CHAR);
	}

	/**
	 * 检查一组指定类型 <code>fromClasses</code> 的对象是否可以赋值给另一组类型 <code>classes</code>。
	 * 
	 * <p>
	 * 此方法可以用来确定指定类型的参数 <code>object1, object2, ...</code> 是否可以用来调用确定参数类型为 <code>class1, class2,
	 * ...</code> 的方法。
	 * </p>
	 * 
	 * <p>
	 * 对于 <code>fromClasses</code> 的每个元素 <code>fromClass</code> 和 <code>classes</code> 的每个元素 <code>clazz</code>， 按照如下规则：
	 * 
	 * <ol>
	 * <li>如果目标类 <code>clazz</code> 为 <code>null</code> ，总是返回 <code>false</code>。</li>
	 * <li>如果参数类型 <code>fromClass</code> 为 <code>null</code> ，并且目标类型 <code>clazz</code> 为非原子类型，则返回 <code>true</code>。 因为 <code>null</code> 可以被赋给任何引用类型。</li>
	 * <li>调用 <code>Class.isAssignableFrom</code> 方法来确定目标类 <code>clazz</code> 是否和参数类 <code>fromClass</code> 相同或是其父类、接口，如果是，则返回 <code>true</code>。</li>
	 * <li>如果目标类型 <code>clazz</code> 为原子类型，那么根据 <a href="http://java.sun.com/docs/books/jls/">The Java Language Specification</a> ，sections 5.1.1, 5.1.2, 5.1.4定义的Widening
	 * Primitive Conversion规则，参数类型 <code>fromClass</code> 可以是任何能扩展成该目标类型的原子类型及其包装类。 例如， <code>clazz</code> 为 <code>long</code> ，那么参数类型可以是 <code>byte</code>、
	 * <code>short</code>、<code>int</code>、<code>long</code>、<code>char</code> 及其包装类 <code>java.lang.Byte</code>、<code>java.lang.Short</code>、
	 * <code>java.lang.Integer</code>、 <code>java.lang.Long</code> 和 <code>java.lang.Character</code> 。如果满足这个条件，则返回 <code>true</code>。</li>
	 * <li>不满足上述所有条件，则返回 <code>false</code>。</li>
	 * </ol>
	 * </p>
	 * 
	 * @param classes
	 *            目标类型列表，如果是 <code>null</code> 总是返回 <code>false</code>
	 * @param fromClasses
	 *            参数类型列表， <code>null</code> 表示可赋值给任意非原子类型
	 * 
	 * @return 如果可以被赋值，则返回 <code>true</code>
	 */
	public static boolean isAssignable(Class[] classes, Class[] fromClasses) {
		if (!ArrayUtils.isSameLength(fromClasses, classes)) {
			return false;
		}

		if (fromClasses == null) {
			fromClasses = ArrayUtils.EMPTY_CLASS_ARRAY;
		}

		if (classes == null) {
			classes = ArrayUtils.EMPTY_CLASS_ARRAY;
		}

		for (int i = 0; i < fromClasses.length; i++) {
			if (isAssignable(classes[i], fromClasses[i]) == false) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 检查指定类型 <code>fromClass</code> 的对象是否可以赋值给另一种类型 <code>clazz</code>。
	 * 
	 * <p>
	 * 此方法可以用来确定指定类型的参数 <code>object1, object2, ...</code> 是否可以用来调用确定参数类型 <code>class1, class2,
	 * ...</code> 的方法。
	 * </p>
	 * 
	 * <p>
	 * 按照如下规则：
	 * 
	 * <ol>
	 * <li>如果目标类 <code>clazz</code> 为 <code>null</code> ，总是返回 <code>false</code>。</li>
	 * <li>如果参数类型 <code>fromClass</code> 为 <code>null</code> ，并且目标类型 <code>clazz</code> 为非原子类型，则返回 <code>true</code>。 因为 <code>null</code> 可以被赋给任何引用类型。</li>
	 * <li>调用 <code>Class.isAssignableFrom</code> 方法来确定目标类 <code>clazz</code> 是否和参数类 <code>fromClass</code> 相同或是其父类、接口，如果是，则返回 <code>true</code>。</li>
	 * <li>如果目标类型 <code>clazz</code> 为原子类型，那么根据 <a href="http://java.sun.com/docs/books/jls/">The Java Language Specification</a> ，sections 5.1.1, 5.1.2, 5.1.4定义的Widening
	 * Primitive Conversion规则，参数类型 <code>fromClass</code> 可以是任何能扩展成该目标类型的原子类型及其包装类。 例如， <code>clazz</code> 为 <code>long</code> ，那么参数类型可以是 <code>byte</code>、
	 * <code>short</code>、<code>int</code>、<code>long</code>、<code>char</code> 及其包装类 <code>java.lang.Byte</code>、<code>java.lang.Short</code>、
	 * <code>java.lang.Integer</code>、 <code>java.lang.Long</code> 和 <code>java.lang.Character</code> 。如果满足这个条件，则返回 <code>true</code>。</li>
	 * <li>不满足上述所有条件，则返回 <code>false</code>。</li>
	 * </ol>
	 * </p>
	 * 
	 * @param clazz
	 *            目标类型，如果是 <code>null</code> 总是返回 <code>false</code>
	 * @param fromClass
	 *            参数类型， <code>null</code> 表示可赋值给任意非原子类型
	 * 
	 * @return 如果可以被赋值，则返回 <code>null</code>
	 */
	public static boolean isAssignable(Class clazz, Class fromClass) {
		if (clazz == null) {
			return false;
		}

		// 如果fromClass是null，只要clazz不是原子类型如int，就一定可以赋值
		if (fromClass == null) {
			return !clazz.isPrimitive();
		}

		// 如果类相同或有父子关系，当然可以赋值
		if (clazz.isAssignableFrom(fromClass)) {
			return true;
		}

		// 对于原子类型，根据JLS的规则进行扩展
		// 目标class为原子类型时，fromClass可以为原子类型和原子类型的包装类型。
		if (clazz.isPrimitive()) {
			// boolean可以接受：boolean
			if (Boolean.TYPE.equals(clazz)) {
				return Boolean.class.equals(fromClass);
			}

			// byte可以接受：byte
			if (Byte.TYPE.equals(clazz)) {
				return Byte.class.equals(fromClass);
			}

			// char可以接受：char
			if (Character.TYPE.equals(clazz)) {
				return Character.class.equals(fromClass);
			}

			// short可以接受：short, byte
			if (Short.TYPE.equals(clazz)) {
				return Short.class.equals(fromClass) || Byte.TYPE.equals(fromClass) || Byte.class.equals(fromClass);
			}

			// int可以接受：int、byte、short、char
			if (Integer.TYPE.equals(clazz)) {
				return Integer.class.equals(fromClass) || Byte.TYPE.equals(fromClass) || Byte.class.equals(fromClass) || Short.TYPE.equals(fromClass)
						|| Short.class.equals(fromClass) || Character.TYPE.equals(fromClass) || Character.class.equals((fromClass));
			}

			// long可以接受：long、int、byte、short、char
			if (Long.TYPE.equals(clazz)) {
				return Long.class.equals(fromClass) || Integer.TYPE.equals(fromClass) || Integer.class.equals(fromClass) || Byte.TYPE.equals(fromClass)
						|| Byte.class.equals(fromClass) || Short.TYPE.equals(fromClass) || Short.class.equals(fromClass) || Character.TYPE.equals(fromClass)
						|| Character.class.equals((fromClass));
			}

			// float可以接受：float, long, int, byte, short, char
			if (Float.TYPE.equals(clazz)) {
				return Float.class.equals(fromClass) || Long.TYPE.equals(fromClass) || Long.class.equals(fromClass) || Integer.TYPE.equals(fromClass)
						|| Integer.class.equals(fromClass) || Byte.TYPE.equals(fromClass) || Byte.class.equals(fromClass) || Short.TYPE.equals(fromClass)
						|| Short.class.equals(fromClass) || Character.TYPE.equals(fromClass) || Character.class.equals((fromClass));
			}

			// double可以接受：double, float, long, int, byte, short, char
			if (Double.TYPE.equals(clazz)) {
				return Double.class.equals(fromClass) || Float.TYPE.equals(fromClass) || Float.class.equals(fromClass) || Long.TYPE.equals(fromClass)
						|| Long.class.equals(fromClass) || Integer.TYPE.equals(fromClass) || Integer.class.equals(fromClass) || Byte.TYPE.equals(fromClass)
						|| Byte.class.equals(fromClass) || Short.TYPE.equals(fromClass) || Short.class.equals(fromClass) || Character.TYPE.equals(fromClass)
						|| Character.class.equals((fromClass));
			}
		}

		return false;
	}

	/**
	 * 取得指定类的 <code>TypeInfo</code>。
	 * 
	 * @param type
	 *            指定类或接口
	 * 
	 * @return <code>TypeInfo</code> 对象.
	 */
	protected static TypeInfo getTypeInfo(Class type) {
		if (type == null) {
			throw new IllegalArgumentException("Parameter clazz should not be null");
		}

		TypeInfo classInfo;

		synchronized (TYPE_MAP) {
			classInfo = (TypeInfo) TYPE_MAP.get(type);

			if (classInfo == null) {
				classInfo = new TypeInfo(type);
				TYPE_MAP.put(type, classInfo);
			}
		}

		return classInfo;
	}

	/**
	 * 得到类的泛型
	 * 
	 * @param clasz
	 * @return
	 */
	public static List<Class<?>> getGenericClass(Class<?> clasz) {
		List<Class<?>> returnList = new ArrayList<Class<?>>();
		Type genType = clasz.getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		for (Type param : params) {
			returnList.add((Class<?>) param);
		}
		return returnList;
	}

	/**
	 * 得到属性的ComponentType
	 * 
	 * @param clasz
	 * @return
	 */
	public static Class<?> getComponentType(Type type) {
		if (type instanceof Class) {
			return ((Class<?>) type).getComponentType();
		} else if (type instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) type;
			Type[] actTypes = pType.getActualTypeArguments();
			if (actTypes.length > 0) {
				if (actTypes[0] instanceof Class) {
					return (Class<?>) actTypes[0];
				}
				return null;
			}
		}
		return null;

	}

	/**
	 * 得到属性的ComponentType
	 * 
	 * @param clasz
	 * @return
	 */
	public static Class<?> getComponentType(Field field) {
		return getComponentType(field.getGenericType());
	}

	/**
	 * 代表一个类的信息, 包括父类, 接口, 数组的维数等.
	 */
	protected static class TypeInfo {
		private Class type;
		private Class componentType;
		private int dimension;
		private List superclasses = new ArrayList(2);
		private List interfaces = new ArrayList(2);

		/**
		 * 创建 <code>TypeInfo</code>。
		 * 
		 * @param type
		 *            创建指定类的 <code>TypeInfo</code>
		 */
		private TypeInfo(Class type) {
			this.type = type;

			// 如果是array, 设置componentType和dimension
			Class componentType = null;

			if (type.isArray()) {
				componentType = type;

				do {
					componentType = componentType.getComponentType();
					dimension++;
				} while (componentType.isArray());
			}

			this.componentType = componentType;

			// 取得所有superclass
			if (dimension > 0) {
				// 将primitive类型转换成对应的包装类
				componentType = getNonPrimitiveType(componentType);

				Class superComponentType = componentType.getSuperclass();

				// 如果是primitive, interface, 则设置其基类为Object.
				if ((superComponentType == null) && !Object.class.equals(componentType)) {
					superComponentType = Object.class;
				}

				if (superComponentType != null) {
					Class superclass = getArrayClass(superComponentType, dimension);

					superclasses.add(superclass);
					superclasses.addAll(getTypeInfo(superclass).superclasses);
				} else {
					for (int i = dimension - 1; i >= 0; i--) {
						superclasses.add(getArrayClass(Object.class, i));
					}
				}
			} else {
				// 将primitive类型转换成对应的包装类
				type = getNonPrimitiveType(type);

				Class superclass = type.getSuperclass();

				if (superclass != null) {
					superclasses.add(superclass);
					superclasses.addAll(getTypeInfo(superclass).superclasses);
				}
			}

			// 取得所有interface
			if (dimension == 0) {
				Class[] typeInterfaces = type.getInterfaces();
				List set = new ArrayList();

				for (int i = 0; i < typeInterfaces.length; i++) {
					Class typeInterface = typeInterfaces[i];

					set.add(typeInterface);
					set.addAll(getTypeInfo(typeInterface).interfaces);
				}

				for (Iterator i = superclasses.iterator(); i.hasNext();) {
					Class typeInterface = (Class) i.next();

					set.addAll(getTypeInfo(typeInterface).interfaces);
				}

				for (Iterator i = set.iterator(); i.hasNext();) {
					Class interfaceClass = (Class) i.next();

					if (!interfaces.contains(interfaceClass)) {
						interfaces.add(interfaceClass);
					}
				}
			} else {
				for (Iterator i = getTypeInfo(componentType).interfaces.iterator(); i.hasNext();) {
					Class componentInterface = (Class) i.next();

					interfaces.add(getArrayClass(componentInterface, dimension));
				}
			}
		}

		/**
		 * 将所有的原子类型转换成对应的包装类，其它类型不变。
		 * 
		 * @param type
		 *            要转换的类型
		 * 
		 * @return 非原子类型
		 */
		private Class getNonPrimitiveType(Class type) {
			if (type.isPrimitive()) {
				if (Integer.TYPE.equals(type)) {
					type = Integer.class;
				} else if (Long.TYPE.equals(type)) {
					type = Long.class;
				} else if (Short.TYPE.equals(type)) {
					type = Short.class;
				} else if (Byte.TYPE.equals(type)) {
					type = Byte.class;
				} else if (Float.TYPE.equals(type)) {
					type = Float.class;
				} else if (Double.TYPE.equals(type)) {
					type = Double.class;
				} else if (Boolean.TYPE.equals(type)) {
					type = Boolean.class;
				} else if (Character.TYPE.equals(type)) {
					type = Character.class;
				}
			}

			return type;
		}

		/**
		 * 取得 <code>TypeInfo</code> 所代表的java类。
		 * 
		 * @return <code>TypeInfo</code> 所代表的java类
		 */
		public Class getType() {
			return type;
		}

		/**
		 * 取得数组元素的类型。
		 * 
		 * @return 如果是数组, 则返回数组元素的类型, 否则返回 <code>null</code>
		 */
		public Class getArrayComponentType() {
			return componentType;
		}

		/**
		 * 取得数组的维数。
		 * 
		 * @return 数组的维数. 如果不是数组, 则返回 <code>0</code>
		 */
		public int getArrayDimension() {
			return dimension;
		}

		/**
		 * 取得所有的父类。
		 * 
		 * @return 所有的父类
		 */
		public List getSuperclasses() {
			return Collections.unmodifiableList(superclasses);
		}

		/**
		 * 取得所有的接口。
		 * 
		 * @return 所有的接口
		 */
		public List getInterfaces() {
			return Collections.unmodifiableList(interfaces);
		}
	}

	/*
	 * ========================================================================= = ==
	 */
	/* 有关primitive类型的方法。 */
	/*
	 * ========================================================================= = ==
	 */

	/**
	 * 返回指定类型所对应的primitive类型。
	 * 
	 * @param clazz
	 *            要检查的类型
	 * 
	 * @return 如果指定类型为<code>null</code>或不是primitive类型的包装类，则返回<code>null</code> ，否则返回相应的primitive类型。
	 */
	public static Class getPrimitiveType(Class clazz) {
		if (clazz == null) {
			return null;
		}

		if (clazz.isPrimitive()) {
			return clazz;
		}

		if (clazz.equals(Long.class)) {
			return long.class;
		}

		if (clazz.equals(Integer.class)) {
			return int.class;
		}

		if (clazz.equals(Short.class)) {
			return short.class;
		}

		if (clazz.equals(Byte.class)) {
			return byte.class;
		}

		if (clazz.equals(Double.class)) {
			return double.class;
		}

		if (clazz.equals(Float.class)) {
			return float.class;
		}

		if (clazz.equals(Boolean.class)) {
			return boolean.class;
		}

		if (clazz.equals(Character.class)) {
			return char.class;
		}

		return null;
	}

	/**
	 * 返回指定类型所对应的非primitive类型。
	 * 
	 * @param clazz
	 *            要检查的类型
	 * 
	 * @return 如果指定类型为<code>null</code>，则返回<code>null</code> ，如果是primitive类型，则返回相应的包装类，否则返回原始的类型。
	 */
	public static Class getNonPrimitiveType(Class clazz) {
		if (clazz == null) {
			return null;
		}

		if (!clazz.isPrimitive()) {
			return clazz;
		}

		if (clazz.equals(long.class)) {
			return Long.class;
		}

		if (clazz.equals(int.class)) {
			return Integer.class;
		}

		if (clazz.equals(short.class)) {
			return Short.class;
		}

		if (clazz.equals(byte.class)) {
			return Byte.class;
		}

		if (clazz.equals(double.class)) {
			return Double.class;
		}

		if (clazz.equals(float.class)) {
			return Float.class;
		}

		if (clazz.equals(boolean.class)) {
			return Boolean.class;
		}

		if (clazz.equals(char.class)) {
			return Character.class;
		}

		return null;
	}

	public static Class[] getClasses(String packageName) {
		return getClasses(packageName, ACCEPT_ALL_CLASS_FILTER);
	}

	/**
	 * 获取包下的所有类
	 * 
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Class[] getClasses(String packageName, ClassNameFilter classNameFilter) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = StringUtils.replace(packageName, ".", "/");
		Enumeration<URL> resources = null;
		try {
			resources = classLoader.getResources(path);
		} catch (Exception e) {
			return null;
		}
		List<File> dirs = new ArrayList<File>();
		ArrayList<Class> classes = new ArrayList<Class>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			String p = "";
			if (resource.getFile().indexOf("!") >= 0) {// 在其他的jar文件中
				p = resource.getFile().substring(0, resource.getFile().indexOf("!")).replaceAll("%20", "");
			} else {// 在classes目录中
				p = resource.getFile();
			}
			if (StringUtils.startsWith(p, "file:/"))
				p = p.substring(6);
			if (StringUtils.endsWith(p.toLowerCase(), ".jar")) {
				if (!SystemUtils.IS_OS_WINDOWS) {
					p = "/" + p;
				}
				JarFile jarFile = null;

				try {
					jarFile = new JarFile(p);
				} catch (Exception e) {
					continue;
				}
				Enumeration<JarEntry> enums = jarFile.entries();
				while (enums.hasMoreElements()) {
					JarEntry entry = (JarEntry) enums.nextElement();
					String n = entry.getName();
					if (n.endsWith(".class")) {
						n = StringUtils.replace(n, "/", ".").substring(0, n.length() - 6);
						if (StringUtils.startsWith(n, packageName) && classNameFilter.accept(n)) {
							try {
								classes.add(Class.forName(n));
							} catch (Exception e) {
								continue;
							} catch (NoClassDefFoundError e) {
								continue;
							}

						}
					}

				}
			} else {
				dirs.add(new File(p));
			}

		}

		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	public static final ClassNameFilter ACCEPT_ALL_CLASS_FILTER = new ClassNameFilter() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.bench.common.lang.ClassUtils.ClassFilter#accept(java.lang.String)
		 */
		@Override
		public boolean accept(String className) {
			// TODO Auto-generated method stub
			return true;
		}

	};

	public static List<Class> findClasses(File directory, String packageName) {
		return findClasses(directory, packageName, ACCEPT_ALL_CLASS_FILTER);
	}

	/**
	 * 查找一个文件夹下的文件
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	public static List<Class> findClasses(File directory, String packageName, ClassNameFilter classNameFilter) {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		String javaPackageName = StringUtils.endsWith(packageName, ".") ? packageName : packageName + ".";
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, javaPackageName + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				String className = javaPackageName + file.getName().substring(0, file.getName().length() - 6);
				try {
					if (classNameFilter.accept(className))
						classes.add(Class.forName(className));
				} catch (ClassNotFoundException e) {
					continue;
				} catch (NoClassDefFoundError e) {
					continue;
				}
			}
		}
		return classes;

	}

	/**
	 * attempts to load through the thread context class loader. Only if thread context class loader is inaccessible, or it can't find the class will it attempt to fall
	 * back to the class loader that loads the FreeMarker classes.
	 */
	public static Class forName(String className) throws ClassNotFoundException {
		try {
			return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
		} catch (ClassNotFoundException e) {
			// Intentionally ignored
		} catch (SecurityException e) {
			// Intentionally ignored
		}
		// Fall back to default class loader
		return Class.forName(className);
	}

	public static Class forNameSafe(String className) {
		try {
			return forName(className);
		} catch (Exception e) {
			;// Intentionally ignored
		}
		return null;
	}

	/**
	 * 接口和实现类是否是一套,即符合DefaultXXX或者XXXImpl
	 * 
	 * @param implementClass
	 * @param implementsInterface
	 * @return
	 */
	public static boolean isImplementSuit(Class<?> clasz, Class<?> interfaze) {
		return clasz.getSimpleName().equals(interfaze.getSimpleName() + "Impl") || clasz.getSimpleName().equals("Default" + interfaze.getSimpleName());
	}

	/**
	 * 根据实现类，返回符合套的接口,即符合DefaultXXX或者XXXImpl，返回XXXX
	 * 
	 * @param clasz
	 * @return
	 */
	public static Class<?> getImplementSuitInterface(Class<?> clasz) {
		Class<?> interfaceClass = null;
		if (clasz.getInterfaces().length > 0) {
			for (Class<?> implementsInterface : clasz.getInterfaces()) {
				if (ClassUtils.isImplementSuit(clasz, implementsInterface)) {
					interfaceClass = implementsInterface;
					break;
				}
			}
		}
		return interfaceClass;
	}

	/**
	 * 获取当前类继承的接口的注解
	 * 
	 * @param field
	 * @return
	 */
	public static <T extends Annotation> T getInterfaceAnnotation(Class<?> clasz, Class<T> annotationClass) {
		for (Class<?> interfaceClass : clasz.getInterfaces()) {
			T anno = interfaceClass.getAnnotation(annotationClass);
			if (anno != null) {
				return anno;
			}
		}
		return null;
	}

	/**
	 * 获取当前类的注解(查找父类和接口)
	 * 
	 * @param field
	 * @return
	 */
	public static <T extends Annotation> T getAnnotation(Class<?> clasz, Class<T> annotationClass) {
		T anno = clasz.getAnnotation(annotationClass);
		if (anno != null) {
			return anno;
		}
		for (Class<?> interfaceClass : clasz.getInterfaces()) {
			anno = interfaceClass.getAnnotation(annotationClass);
			if (anno != null) {
				return anno;
			}
		}
		Class<?> parentClass = clasz.getSuperclass();
		while (parentClass != null) {
			anno = parentClass.getAnnotation(annotationClass);
			if (anno != null) {
				return anno;
			}
			parentClass = parentClass.getSuperclass();
		}

		return null;
	}

	/**
	 * 获取当前类继承的接口的注解
	 * 
	 * @param field
	 * @return
	 */
	public static Class<?> getInterfaceContainsAnnotation(Class<?> clasz, Class<? extends Annotation> annotationClass) {
		for (Class<?> interfaceClass : clasz.getInterfaces()) {
			Annotation anno = interfaceClass.getAnnotation(annotationClass);
			if (anno != null) {
				return interfaceClass;
			}
		}
		return null;
	}

	/**
	 * 获取类名列表
	 * 
	 * @param classList
	 * @return
	 */
	public static List<String> getClassName(Collection<Class<?>> classList) {
		if (classList == null) {
			return null;
		}
		List<String> nameList = new ArrayList<String>();
		for (Class<?> clasz : classList) {
			nameList.add(clasz.getName());
		}
		return nameList;
	}

	private static Set<Class<?>> list(ClassLoader CL) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Set<Class<?>> returnSet = new HashSet<Class<?>>();
		if (CL == null) {
			return returnSet;
		}
		Class CL_class = CL.getClass();
		java.lang.reflect.Field ClassLoader_classes_field = null;
		try {
			ClassLoader_classes_field = FieldUtils.getField(CL_class, "classes");
			ClassLoader_classes_field.setAccessible(true);
			Vector<Class<?>> classes = (Vector<Class<?>>) ClassLoader_classes_field.get(CL);
			for (Class<?> clasz : classes) {
				returnSet.add(clasz);
			}
			if (CL_class.getSuperclass() != null) {
				returnSet.addAll(list(CL.getParent()));
			}
			return returnSet;
		} catch (Exception e) {
			if (CL_class.getSuperclass() != null) {
				returnSet.addAll(list(CL.getParent()));
			} 
			return returnSet;
		}
	}

	public static void main(String args[]) throws Exception {
		ClassLoader myCL = Thread.currentThread().getContextClassLoader();
		System.out.println(list(myCL));
	}

	/**
	 * 判断一个类的方法是否被重载了
	 * 
	 * @param clasz
	 * @param superClass
	 *            父类
	 * @param method
	 *            方法名
	 * @param methodParameterTypes
	 *            方法参数
	 * @return
	 */
	public static boolean isOverride(Class<?> clasz, Class<?> superClass, String methodName, Class<?>... methodParameterTypes) throws NoSuchMethodException {
		return superClass.getMethod(methodName, methodParameterTypes) != clasz.getMethod(methodName, methodParameterTypes);
	}

	private static ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

	/**
	 * 获取方法参数名<br>
	 * 注意，对接口无效
	 * 
	 * @param method
	 * @return
	 */
	public static String[] getParameterNames(Method method) {
		return parameterNameDiscoverer.getParameterNames(method);
	}

	/**
	 * 获取构造函数参数名<br>
	 * 注意，对接口无效
	 * 
	 * @param ctor
	 * @return
	 */
	public static String[] getParameterNames(Constructor<?> ctor) {
		return parameterNameDiscoverer.getParameterNames(ctor);
	}
}
