# 基于MVVM架构JetPack组件的 WanAndroid 客户端

[看app截图点这里](https://www.jianshu.com/p/b6cae51ecaa1)
### 项目中使用到的WanAndroid的接口放在了api模块下，有需要的可以直接使用。
#### 导入方式
##### 将JitPack存储库添加到您的构建文件中(项目根目录下build.gradle文件)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
##### 添加依赖项
```
dependencies {
	        implementation 'com.github.keluokeda:WanAndroid:1.0.0'
	}
```
##### 构建属于你的WanApiServie
```
@Provides
    @Singleton
    fun provideWanApiService(okHttpClient: OkHttpClient): WanApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WanApiService.BASE_URL)
            .build().create(WanApiService::class.java)
    }
```
## 主要功能
- [x] 首页、项目、公众号、体系、我的五大模块
- [x] 夜间模式设置功能
- [x] 文章浏览功能
- [x] 登录注册功能
- [x] 我的积分：积分查看以及积分记录
- [x] 积分排行查看
- [x] 查看某个用户分享的文章
- [x] 我的收藏：收藏文章、取消收藏文章和查看已收藏的文章
- [x] 我的分享：查看分享的文章和分享文章功能
- [x] 历史记录：记录功能、查看历史记录功能及删除历史记录功能
- [x] 公众号以及项目主分类排序、启用和禁用功能
- [x] 退出登录功能
- [x] 稍后阅读：添加文章进入稍后阅读以及删除稍后阅读功能
- [x] 搜索：热门搜索、查看搜索历史以及删除搜索历史

## 项目技术点
- 使用kotlin语言开发
- 使用MVVM架构
- 使用JetPack组件
- 使用Material Design设计语言
- 使用ConstraintLayout减少布局嵌套
- 使用viewBinding
- 模块化，不同的功能分放在不同的模块内
- 使用ARouter导航
- 使用Hilt实现依赖注入
- 使用kotlin协程处理耗时操作
- 使用Room保存数据

