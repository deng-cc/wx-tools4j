# 1、概况

dulk.wx4j包中，目前主要写了三个部分：
- base 基础核心部分，主要是微信服务器的验证和用户消息的交互
- material 素材管理部分，上传素材、下载素材等
- pay 支付部分

# 2、如何使用
2.1 导入代码
- 目前没有单独打包为jar，所以直接复制所有源码的 .java 
- 相关依赖见项目中的pom.xml，其中注释表明为“必要依赖”的部分

2.2 配置文件
- 配置wxConfig.properties，该文件在wx4j.config文件下，部署时需放到classpath下
- log4j的配置不在阐述
- 在web.xml中配置监听器InitWxConfigListener，该监听器用以注入配置项和获取微信凭证

2.3 继承WxSupport
- 将Controller继承wx4j.base.dispatch中的WxSupport抽象类，并实现抽象方法
- 如果你的Controller已经继承了某个类，那么新建一个类继承WxSupport，并将该类作为Controller的属性
- handleXxx表如何处理用户发送的消息类型
- doXxx表如何处理用户触发的事件
- responseXxx表相应动作，通常结合handleXxx和doXxx使用，反馈信息给用户

2.4 demo
在demo包的WxAction中做了demo演示：

``` stylus
/**
 * 消息交互的主动执行入口
 * @param request
 * @param response
 * @return
 */
@Override
@RequestMapping(value = "/execute")
public void execute(HttpServletRequest request, HttpServletResponse response) {
    super.execute(request, response);
}

//消息处理
@Override
protected void handleText() {
    String userContent = wxRequestParams.getContent();
    responseText("您输入的信息是：" + userContent);
}

//...省略部分方法

@Override
protected void handleImage() {
    String mediaId = "sdS18-X3fbHeHOfRYaa172Ekufol0AKVBPyUTy-9EIKXp152VnWeTh6Ldu4AGhmg";
    responseImage(mediaId);
}

@Override
protected void handleLink() {
    responseText("你发送了一个链接：" + wxRequestParams.getUrl());
}

//事件处理
@Override
protected void doSubscribe() {
    responseText("Hi, 欢迎来到Dulk的测试公众号~");
}

//... 省略部分方法

@Override
protected void doMenu_Click() {
    responseText("你点击了菜单哦");
}

@Override
protected void doMenu_View() {
    System.out.println("用户点击了按钮网页浏览");
}
```

# 3、其他想说的
这个自用开发工具包，主要还是出于学习的目的，参考了github用户chengn的[wechat4j][1]，从中受益良多，感谢。

工具包中部分“限制”，尤其是素材管理部分，是按照微信文档进行编写的，如上传图片的类型，大小等等，但实际上的微信接口和文档限制并不完全一致，精力有限，没有进行一一核对，如若需要更改，可以到对应的XxxAPI类中进行修改。


# 4、部分方法附录
4.1 wx4j.base<br>
4.1.1 wx4j.base.dispatch.WxSupport
- execute() 微信信息和事件处理的执行入口，执行数据的接受和事件分发

4.1.2 wx4j.base.service.WxBaseService
- getNewAccessToken() 立即获取一个新的微信接口凭证

4.1.3 wx4j.base.util.NetUtil
- throwRequestGET() 请求微信url接口，返回字符串
- sendRequestGET() 请求微信url接口，返回JSONObject
- sendRequestGET() 请求微信url接口，获取File
- throwRequestPOST()
- sendRequestPOST()
- sendRequestPOST() 

4.2 wx4j.material<br>
4.2.1 wx4j.material.service
- uploadTempXxx() 上传临时Xxx素材
- uploadPermXxx() 上传永久Xxx素材
- downloadTempXxx() 下载临时Xxx素材
- downloadPermXxx() 下载永久Xxx素材

4.3 wx4j.message<br>
4.3.1 wx4j.message.service
- sendAllByTag() 根据标签群发消息


4.4 wx4j.pay<br>
4.4.1 wx4j.pay.service
- wxPayByJSAPI() 微信支付（JSAPI方式）
- wxPayByNATIVE() 微信支付（二维码方式）
- wxRefund() 微信退款
- afterWxPay() 微信支付后的业务处理和应答



  [1]: https://github.com/sword-org/wechat4j