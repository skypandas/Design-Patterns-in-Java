/*
 *	饿汉式，线程安全
 *		- 单例的实例被声明成static和final，在第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的。
 *		- 它不是一种懒加载模式(lazy initialization)，即使没有调用getInstance()方法，实例也会被创建。
 *
 */

public class SingletonHungry {
	// 类加载时就初始化
	private static final SingletonHungry instance = new SingletonHungry();

	private SingletonHungry() {}

	public static SingletonHungry getInstance() {	
		return instance;
	}
}
