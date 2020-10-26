# day15

1. 观察者设计模式 - 定义
当一个对象的状态发生改变时，与他相关联的部分对象的状态同时也会发生改变。



2. 观察者设计模式 - 角色划分（会有哪些部分）

公众号，我比如订阅了微信 Android进阶之旅 公众号，没更新一篇文章都会及时的通知我 （观察者设计模式）

角色划分：

被观察者（Observable）：公众号
具体的被观察者（Concrete Observable）： Android进阶之旅-公众号

观察者（Observer）：微信的用户
具体的观察者（Concrete Observer）： Darren，hation ，着实很无奈

3. 观察者设计模式 - 公众订阅号
写一个事例代码：

被观察者 :  WXPublicObservable(公众号)
具体的被观察者 : WXAdvanceObservable（Android进阶之旅）

观察者: IWXUser （微信用户）
具体的观察者 : WXUser （Darren，高岩）

4.观察者设计模式 - 推拉模式
公众订阅号 ：比如我们的订阅号，主动推送一篇文章给我们（推送模式），然后我觉得文章很不错我想想看看往期的文章（拉模式）

拉模式：把公众号给我

还有推拉模式的结合：拉模式 + 推模式  （公众号就是推拉模式，即可以收到文章的推送又可以自己去拉取文章阅读）

区别：
拉模式：主动
推模式：被动

5.Java 自带的观察者设计模式


6.观察者设计模式 - 观察数据插入（实战）


7.观察者设计模式的使用
RXjava 源码 （后面 3 -4 次课程）
ListView 的 Adapter 的 setDataChange 的

// ListView 的方法 ，给 Adapter 注册一个 mDataSetObserver
mAdapter.registerDataSetObserver(mDataSetObserver);
1.public void notifyChanged() {
2.        synchronized(mObservers) {
3.            // since onChanged() is implemented by the app, it could do anything, including
4.            // removing itself from {@link mObservers} - and that could cause problems if
5.            // an iterator is used on the ArrayList {@link mObservers}.
6.            // to avoid such problems, just march thru the list in the reverse order.
7.            for (int i = mObservers.size() - 1; i >= 0; i--) {
8.                // onChanged 方法 这个方法就会来到  ListView 的 mDataSetObserver 中的 onChanged()
9.                mObservers.get(i).onChanged();
10.            }
11.        }
12.    }

来到 AdapterView 的 onChange() 方法，然后去更新 ListView 



