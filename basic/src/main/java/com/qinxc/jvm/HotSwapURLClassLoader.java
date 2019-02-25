package com.qinxc.jvm;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qxc
 * @date 2019/2/22.
 */
public class HotSwapURLClassLoader extends URLClassLoader {

    public static Map<String, Long> cacheLastModifyTimeMap = new HashMap<>();

    public static String projectClassPath = "/Users/qxc/Documents/work/stone/basic/target/classes/";

    public static String packagePath = "com/qinxc/jvm/";

    private static HotSwapURLClassLoader hcl = new HotSwapURLClassLoader();

    public static HotSwapURLClassLoader getClassLoader() {
        return hcl;
    }

    public HotSwapURLClassLoader() {
        super(getMyURLs());
    }

    private static URL[] getMyURLs() {
        URL url = null;
        try {
            url = new File(projectClassPath).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new URL[]{url};
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz = null;
        clazz = findLoadedClass(name);
        if (clazz != null) {
            if (resolve) {
                resolveClass(clazz);
            }
            if (isModify(name)) {
                hcl = new HotSwapURLClassLoader();
                clazz = customLoad(name, hcl);
            }
            return clazz;
        }
        if (name.startsWith("java.")) {
            try {
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve)
                        resolveClass(clazz);
                    return clazz;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return customLoad(name, this);
    }


    public Class customLoad(String name, ClassLoader cl) throws ClassNotFoundException {
        return customLoad(name, false, cl);
    }

    public Class customLoad(String name, boolean resolve, ClassLoader cl) throws ClassNotFoundException {
        Class clazz = ((HotSwapURLClassLoader) cl).findClass(name);
        if (resolve)
            ((HotSwapURLClassLoader) cl).resolveClass(clazz);
        long lastModifyTime = getClassLastModifyTime(name);
        cacheLastModifyTimeMap.put(name, lastModifyTime);
        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    private long getClassLastModifyTime(String name) {
        String path = getClassCompletePath(name);
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException(new FileNotFoundException(name));
        }
        return file.lastModified();
    }

    private boolean isModify(String name) {
        long lastModify = getClassLastModifyTime(name);
        long previousModifyTime = cacheLastModifyTimeMap.get(name);
        if (lastModify > previousModifyTime) {
            return true;
        }
        return false;
    }

    private String getClassCompletePath(String name) {
        String simpleName = name.substring(name.lastIndexOf(".") + 1);
        return projectClassPath + packagePath + simpleName + ".class";
    }
}
