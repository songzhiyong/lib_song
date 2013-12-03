#菜鸟上路
<p>&nbsp;&nbsp;&nbsp;作为一个Android开发的菜鸟，总结用到的一些工具框架(感谢框架的开发大牛O(∩_∩)O~),我会一直提升知识储备并更新这个项目.</p>
##集成开源项目
- 2012年最受欢迎开源项目第5名:afinal android框架
- 2012年最受欢迎开源项目第3名:阿里开源代码fastjson框架
- android orm映射框架 ormlite (类似于hibernate)
- sourceforge.net中的汉语字符串转换为拼音的jar包
- 网络通信Async-http-client框架
- google plus和google now listview 动画 alike
- ProgressButton控件
- UniversalImageLoader开源项目
- SlidingMenu控件 ps:SlidingFragmentActivity未引入 依赖ABS
- ViewPagerIndicator ViewPager滑动时的标识方式
- PhotoProcessing，通过JNI调用C代码进行图片的处理 用于图片加滤镜效果
- SlideExpandableListView 展开操作栏的ListView，操作栏中可已放置对该条目的操作按钮
- PopupMenu点击条目弹出操作框，操作框中有对该条目的相关操作（对比SlideExpandableListView）
- 移动分析平台Countly
##基本搭建类
- BaseActivity：基础Activity--基本生命周期的监控，常用方法的实现
- BaseApplication：基本Application类，包含一些程序基本信息,供继承拓展
- DeviceInfo：设备基本信息，包括{品牌，型号，系统版本，设备唯一标识，是否为平板判断}
- EasyBaseAdapter：采用泛型，更加简洁的Adapter基类。
- Log：对android.util.Log的封装，可以根据Level控制log的输出。 默认级别VERBOSE,打印所有,建议用Log.d Log.i 软件发布时调整级别为SUPPRESS.
- SimplePagerAdapter：基本ViewPager适配器类.

##工具类
####-加密
- AES
- DES
- MD5
- DecryptionZipUtil：AES加密压缩解压

####-设备相关
- MemoryStatus:Android系统存储状况
- MorseCodeConverter： 摩斯密码转换器(apidemo)
- NetUtils:系统联网状态
- SystemService: Android系统启动其他自带程序方法
- Settings:系统设置相关

####-尺寸
- DimenUtils：转换dp<-->px

####-文件
- SharedPreUtils：对SharedPreferences的封装
- ZipUtils:Android文件解压压缩
- FileUtils:文件读取为字符串、字节数组；文件大小格式转换
- HtmlUtils:网页文件转换为字符串时相关转义字符的替换

####-格式相关
- DateTimeFormatter：时间日期格式转换
- PinyinUtil：汉字转换为拼音
- ByteConvert：数据类型之间的转换
####-多媒体
- BitmapUtils：Bitmap相关操作及滤镜特效
- ImageUtils:处理图片的工具类（Terry_龙）
- MediaFile:判断媒体文件的具体类型的工具类

##-交流方式
- Weibo:http://weibo.com/zhiyongs
- E-mail:songzhiyong1121@gmail.com
- QQ:780834790
![Alt text](https://lh3.googleusercontent.com/-CWe8YiPPnjk/Udq70RxEi8I/AAAAAAAAA-M/scPW1-_yKQI/w640-h480-no/201307080955460_meitu_1.jpg)

