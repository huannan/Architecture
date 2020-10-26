package com.nan.day15_pattern_observer.simple4;

/**
 * 数据库的更新
 */
public class DatabaseManager {
    private static volatile DatabaseManager mInstance;

    private Observable<Member, Observer<Member>> mObservable;

    public DatabaseManager() {
        mObservable = new Observable<>();
    }

    public static DatabaseManager getInstance() {
        if (mInstance == null) {
            synchronized (DatabaseManager.class) {
                if (mInstance == null) {
                    mInstance = new DatabaseManager();
                }
            }
        }
        return mInstance;
    }

    public void insert(Member member) {
        // 插入数据库,省略了....

        // 通知观察者
        mObservable.addUpdate(member);
    }

    public void register(Observer<Member> observer) {
        mObservable.register(observer);
    }

    public void unregister(Observer<Member> observer) {
        mObservable.unregister(observer);
    }

}
