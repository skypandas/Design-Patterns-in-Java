/*
 *	懒汉式，线程安全
 *		- 使用synchronized关键字对getInstance()方法进行同步
 *		- 解决多线程环境下创建多实例的问题
 *		- 效率较低
 */

public class SingletonLazySafe {
	private static SingletonLazySafe instance;
	private SingletonLazySafe() {}

	// 使用synchronized对方法加锁
	// 每次获取实例时，都需要加锁，效率低
	public static synchronized SingletonLazySafe getInstance() {
		if (instance == null) {
			instance == new SingletonLazySafe();
		}
		return instance;
	}
}
