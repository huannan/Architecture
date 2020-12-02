# day23

组合设计模式：
         定义：将对象组合成树形结构以表示 “部分-整体” 的层次结构，使得用户对单个对象和组合对象的使用具有一致性
         与门面设计模式的区别，组合是 “部分-整体” 的关系，门面是单独的关系（聚合关系）
         使用：ViewGroup , 用一个ListView 去实现一个 ExpendListView (两个ListView嵌套的效果)
        
         // View与ViewGroup就是组合
         // Child views of this ViewGroup
         private View[] mChildren;

10000  小时定律