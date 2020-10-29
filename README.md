# 一个非常实用的Java参数验证框架

<a name="xM5yL"></a>
# ![image.png](https://cdn.nlark.com/yuque/0/2020/png/2721112/1603949264338-850f9936-cfba-450e-999b-1da217ca33f4.png#align=left&display=inline&height=360&margin=%5Bobject%20Object%5D&name=image.png&originHeight=720&originWidth=1280&size=162620&status=done&style=none&width=640)
<a name="q5KPr"></a>
# 简介
在日常开发中，不论是在REST API还是RPC接口在代码上都会做参数校验，用来验证输入参数的合法性，举个最简单的例子：<br />
<br />（一）验证Spring MVC接口入参
```java
@RequestMapping(value = "helloworld")
@ResponseBody
public Object helloworld(String str) throws Exception {
	if(str == null || str.trim().length() == 0) {
    	throw new IllegalArgumentException("str is required");
    }
}
```
> Spring MVC 中也可以使用 @RequestParam(required = true) 注解来约束str参数为必填项，但这个框架层会做拦截，被拦截后的效果一般不是预期想要的


<br />（二）验证RPC接口中的入参
```java
class Request {
 String id;
 String name;
 getter ……
 setter ……
}
public Response helloworld(Request request) {
    if(StringUtils.isEmpty(request.getId()) || StringUtils.isEmpty(request.getName())) {
       throw new IllegalArgumentException("id or name is required");
    }
}
```
一个非空判断需要写一行代码、如果需要验证的参数很多或者还需要判断参数长度、取值范围、集合大小等，又或者参数是一个对象，**对象里面循环嵌套了对象**，每个对象中又有很多字段需要验证参数的合法性等等，这个时候如果仍然使用hard code来实现的话，实现、维护成本就太高了，而且代码的可读性也是非常糟糕的，因此给大家分享下Smart-Validate验证框架，用来解决以上的问题。<br />Smart-Validate，一个非常实用的Java参数验证框架，具有以下特点：
> - 足够轻，整个代码量不足500行
> - 基于注解声明规则，使用成本低
> - 内置常见的验证规则
> - 支持嵌套对象的验证
> - 可自由拓展验证规则


<br />框架内置以下规则列表

| **规则注解** | **说明** | **支持的数据类型** |
| --- | --- | --- |
| MaxLengthValidate | 验证最大长度 | String、Collection、Map、Array |
| MaxValueValidate | 验证最大值 | Integer、Long、Byte、Short、Double、Float |
| MinLengthValidate | 验证最小长度 | String、Collection、Map、Array |
| MinValueValidate | 验证最小值 | Integer、Long、Byte、Short、Double、Float |
| NotNullValidate | 验证非空 | Object、String |
| RangeLengthValidate | 验证长度范围 | String、Collection、Map、Array |
| RangeValueValidate | 验证值范围 | Integer、Long、Byte、Short、Double、Float |
| RegexpValidate | 验证正则规则 | String |

<a name="XqYtD"></a>
# 使用方法


<a name="VLurW"></a>
## 验证对象参数
```java
@ValidateBean
public class Request {
    //ID不能为空
    @NotNullValidate
    private String id;
    //name最小长度为5
    @MinLengthValidate(length = 5)
    private String name;
    public Request() {}
    getter ……
 	setter ……
}
public void helloworld(Request request) throws SmartValidateException {
    SmartValidate.validate(request);
    //code
}
public static void main(String[] args) {
    try {
        Request req = new Request();
        req.setId(null);
        req.setName("haha");
        new Demo().helloworld(req);
    } catch (SmartValidateException e) {
        System.err.println(e.getMessage());
    }
}
```
```
LOG Print: id为必填项
```


```java
public static void main(String[] args) {
    try {
        Request req = new Request();
        req.setId("1");
        req.setName("haha");
        new Demo().helloworld(req);
    } catch (SmartValidateException e) {
        System.err.println(e.getMessage());
    }
}
```
```
LOG Print: name的长度不能小于5
```


<a name="qAgb9"></a>
## 验证嵌套对象参数
```java
@ValidateBean
public class Request {
    @NotNullValidate
    private InnerRquest param;
    getter ……
 	setter ……
}
@ValidateBean
public class InnerRquest {
    @NotNullValidate
    private String id;
    @MaxValueValidate(value = "99")
    private Integer age;
    public InnerRquest() {}
   	getter ……
 	setter ……
}
public void helloworld(Request request) throws SmartValidateException {
    SmartValidate.validate(request);
    //code
}
public static void main(String[] args) {
    try {
        Request req = new Request();
        InnerRquest ireq = new InnerRquest();
        ireq.setId("1");
        ireq.setAge(100);
        req.setParam(ireq);
        new Demo().helloworld(req);
    } catch (SmartValidateException e) {
        System.err.println(e.getMessage());
    }
}
```
```
LOG Print: age的值不能大于99
```


<a name="XvLVi"></a>
## 验证集合中的对象
```java
@ValidateBean
public class Request {
    @NotNullValidate
    private List<InnerRquest> param;
  	getter ……
 	setter ……
}
public void helloworld(Request request) throws SmartValidateException {
    SmartValidate.validate(request);
    //code
}
public static void main(String[] args) {
     try {
         Request req = new Request();
         InnerRquest ireq = new InnerRquest();
         ireq.setId("1");
         ireq.setAge(100);
         req.setParam(Arrays.asList(ireq));
         new Demo().helloworld(req);
     } catch (SmartValidateException e) {
         System.err.println(e.getMessage());
     }
}

```
```
LOG Print: age的值不能大于99
```


<a name="e3c33"></a>
## 验证方法中的参数列表
```java
public void helloworld(
    @ValidateArgument(
        notNull=@NotNullValidate,
        maxLength=@MaxLengthValidate(length=1)
    )String str) throws Exception {
    //code
}
```

<br />配置拦截器
```xml
<aop:config>
	<!-- pointcut 配置成需要拦截的路径 -->
	<aop:advisor pointcut="execution(*.*(..))" advice-ref="smartValidateInterceptor"/>
</aop:config>
<bean id="smartValidateInterceptor" class="com.smart.validate.interceptor.SmartValidateInterceptor" />
```
<a name="M6oHE"></a>
## 拓展自定义验证规则

<br />（一）定义规则注解
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerValidate {
    String message () default "";
    String name () default ""; 
}
```

<br />（二）实现规则
```java
public class MatchCustomerValidate extends AbstractMatchValidate<CustomerValidate >{
    @Override
    public void validate(CustomerValidate t,
                         String fieldName,
                         Object value)
        throws SmartValidateException {
        //your code
    }
}
```

<br />（三）生效规则
```java
ValidateRulePool.mount(CustomerValidate.class, new MatchCustomerValidate());
```

<br />（四）使用 @CustomerValidate 注解来验证参数<br />

<a name="ZiiCd"></a>
## 使用多个验证规则
不论时是参数列表还是对象验证，都每个字段都可以支持多个验证规则。<br />
<br />（一）对象参数
```java
@NotNullValidate
@RangeValueValidate(min = "1", max = "100")
private Integer age;
```
（二）方法参数
```java
public void helloworld(
    @ValidateArgument(
        notNull=@NotNullValidate,
        rangeValue=@RangeValueValidate(min = "1", max = "100")
    )Integer age) throws Exception {}
```


<a name="W6iAj"></a>
## 自定义提示消息
内置的验证规则均支持自定义message，以NotNullValidate为例，在注解中声明可以声明name或message属性，例如：
<a name="IzEFx"></a>
### 自定义name属性
```java
//name=“年龄”，在验证不通过时message为 “年龄为必填项”
@NotNullValidate(name = "年龄")
private Integer age;
```
<a name="Chhw4"></a>
### 自定义message属性
```java
//message=“请输入年龄”，在验证不通过时message为 “请输入年龄”
@NotNullValidate(message = "请输入年龄")
private Integer age;
```
如果name和message都未定义，则message为默认值 “age为必填项”<br />

<a name="oEIIG"></a>
# 最佳实践
不论是Spring MVC的REST API参数，还是RPC中的对象入参，一般都会有相应的拦截器去处理日志打印、异常等相关的逻辑，因此参数验证也可以在这一层中使用SmartValidate.validate方法来统一接入，框架中也内置了基于Spring的SmartValidateInterceptor拦截器，也可以自己跟实际情况来实现自己的验证策略。<br />

<a name="TGfet"></a>
# 源码
[https://github.com/lichao4Java/smart-validate](https://github.com/lichao4Java/smart-validate)
