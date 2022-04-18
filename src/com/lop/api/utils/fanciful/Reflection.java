package com.wandy.api.utils.fanciful;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;

public final class Reflection
{
  private static String _versionString;
  
  public static synchronized String getVersion()
  {
    if (_versionString == null)
    {
      if (Bukkit.getServer() == null) {
        return null;
      }
      String name = Bukkit.getServer().getClass().getPackage().getName();
      _versionString = name.substring(name.lastIndexOf('.') + 1) + ".";
    }
    return _versionString;
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
private static final Map<String, Class<?>> _loadedNMSClasses = new HashMap();
  @SuppressWarnings({ "unchecked", "rawtypes" })
private static final Map<String, Class<?>> _loadedOBCClasses = new HashMap();
  
  @SuppressWarnings("rawtypes")
public static synchronized Class<?> getNMSClass(String className)
  {
    if (_loadedNMSClasses.containsKey(className)) {
      return (Class)_loadedNMSClasses.get(className);
    }
    String fullName = "net.minecraft.server." + getVersion() + className;
    Class<?> clazz = null;
    try
    {
      clazz = Class.forName(fullName);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      _loadedNMSClasses.put(className, null);
      return null;
    }
    _loadedNMSClasses.put(className, clazz);
    return clazz;
  }
  
  @SuppressWarnings("rawtypes")
public static synchronized Class<?> getOBCClass(String className)
  {
    if (_loadedOBCClasses.containsKey(className)) {
      return (Class)_loadedOBCClasses.get(className);
    }
    String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
    Class<?> clazz = null;
    try
    {
      clazz = Class.forName(fullName);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      _loadedOBCClasses.put(className, null);
      return null;
    }
    _loadedOBCClasses.put(className, clazz);
    return clazz;
  }
  
  public static synchronized Object getHandle(Object obj)
  {
    try
    {
      return getMethod(obj.getClass(), "getHandle", new Class[0]).invoke(obj, new Object[0]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
private static final Map<Class<?>, Map<String, Field>> _loadedFields = new HashMap();
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static synchronized Field getField(Class<?> clazz, String name)
  {
    Map<String, Field> loaded = null;
    if (!_loadedFields.containsKey(clazz))
    {
      Map<String, Field> loaded1 = new HashMap();
      _loadedFields.put(clazz, loaded1);
    }
    else
    {
      loaded = (Map)_loadedFields.get(clazz);
    }
    if (loaded.containsKey(name)) {
      return (Field)loaded.get(name);
    }
    try
    {
      Field field = clazz.getDeclaredField(name);
      field.setAccessible(true);
      loaded.put(name, field);
      return field;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      loaded.put(name, null);
    }
    return null;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
private static final Map<Class<?>, Map<String, Map<ArrayWrapper<Class<?>>, Method>>> _loadedMethods = new HashMap();
  
  public static synchronized Method getMethod(final Class<?> clazz, final String name, final Class<?>... args) {
      if (!Reflection._loadedMethods.containsKey(clazz)) {
          Reflection._loadedMethods.put(clazz, new HashMap<String, Map<ArrayWrapper<Class<?>>, Method>>());
      }
      final Map<String, Map<ArrayWrapper<Class<?>>, Method>> loadedMethodNames = Reflection._loadedMethods.get(clazz);
      if (!loadedMethodNames.containsKey(name)) {
          loadedMethodNames.put(name, new HashMap<ArrayWrapper<Class<?>>, Method>());
      }
      final Map<ArrayWrapper<Class<?>>, Method> loadedSignatures = loadedMethodNames.get(name);
      final ArrayWrapper<Class<?>> wrappedArg = new ArrayWrapper<Class<?>>(args);
      if (loadedSignatures.containsKey(wrappedArg)) {
          return loadedSignatures.get(wrappedArg);
      }
      Method[] arrayOfMethod;
      for (int j = (arrayOfMethod = clazz.getMethods()).length, i = 0; i < j; ++i) {
          final Method m = arrayOfMethod[i];
          if (m.getName().equals(name) && Arrays.equals(args, m.getParameterTypes())) {
              m.setAccessible(true);
              loadedSignatures.put(wrappedArg, m);
              return m;
          }
      }
      loadedSignatures.put(wrappedArg, null);
      return null;
  }
}
