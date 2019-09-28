/*
 *	懒汉式，线程不安全
 *		- 使用懒加载方式创建实例，当真正使用时，才去创建实例对象;
 *  	- 存在线程安全的问题，当有多个线程并行调用getInstance()的时候，可能会创建多个实例。
 */

public class SingletonLazyUnsafe {
	private static SingletonLazyUnsafe instance;

	private SingletonLazyUnsafe() {}

	public static SingletonLazyUnsafe getInstance() {
		// 判断实例是否已经创建，没有则创建
		// 多线程环境可能会创建多个实例
		if (instance == null) {
			instance = new SingletonLazyUnsafe();
		}
		return instance;
	}
}
