/*
 *	双重检验锁
 *		- 需要对同步块加锁；
 *		- 两次检查instance == null，一次在同步块外，一次在同步块内
 *		- 实例需要使用volatile关键字进行声明
 *
 * 	volatile的作用：
 * 		instance = new SingletonDoubleCheckedLock()这条语句不是一个原子操作。JVM在把new操作分为三步：
 * 			1.给instance对象在堆上分配内存；
 * 			2.调用构造函数初始化成员变量；
 * 			3.将instance引用指向分配的内存空间。
 * 		但是JVM中存在指令重排序优化，也就是说，JVM会对上面的3步指令进行重排。如果重排后的顺序为1-3-2，
 *		线程1执行完3后，instance就为非null了，但在执行2之前，被线程2抢占cpu执行权，判断instance已经是
 *		非null了(但是还没有初始化)，所以线程2会直接返回一个还没有初始化的instance，这样就会产生错误。
 *
 *		因此我们需要将instance变量声明为volatile，禁止指令重排序。
 */


public class SingletonDoubleCheckedLock {
	// 使用volatile关键字禁止指令重排序
	private static volatile SingletonDoubleCheckedLock instance;

	private SingletonDoubleCheckedLock() {}

	public static SingletonDoubleCheckedLock getInstance() {
		// first checked
		// 没有创建实例，通过第一次检查，可以同时有多个线程进入
		if (instance == null) {
			synchronized(SingletonDoubleCheckedLock.class) {
				// second checked
				// 创建实例时加锁，防止多个线程创建多个实例
				if (instance == null) {
					instance = new SingletonDoubleCheckedLock();
				}
			}
		}
		return instance;
	}
}
