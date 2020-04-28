# 象律师

## 不需要创建Presenter的activity可以这样
```
 // 如果没presenter
 //你可以这样写
 //
 @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 
 @Inject constructor() : IPresenter {
 
     override fun onStart() {
     }
 
     override fun onDestroy() {
     }
     
 }
 
```

## java.lang.BootstrapMethodError: Exception from call site #0 bootstrap method
```
//app build.gradle
compileOptions {
        targetCompatibility "8"
        sourceCompatibility "8"
}
//https://developer.android.com/studio/write/java8-support
```

## 状态栏和布局顶部重叠问题
```
// 在每个根布局添加
android:fitsSystemWindows="true"
// 不要在fragment布局中添加
```

## 尽量在项目中使用AndroidX中的控件

## 腾讯直播和实时音视频通话SDK会冲突
![https://cloud.tencent.com/document/product/454/7873](使用专业版（Professional）)

## Provider
Provider不能共享，provider android:name="" 需要指定唯一的全类名