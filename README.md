这是一个服务端参数验证框架，可以对JavaBean中的属性以及方法中参数列表进行验证

<h1>默认支持的验证规则</h1>

	MaxLengthValidate               验证最大长度
	MaxValueValidate	        验证最大值	
	MinLengthValidate        	验证最小长度	
	MinValueValidate        	验证最小值	
	NotNullValidate	                验证非空
	RangeLengthValidate	        验证长度范围	
	RangeValueValidate	        验证值范围	
	RegexpValidate	                验证正则规则
 
 
<h1>验证JavaBean</h1>

	@ValidateBean
	class JavaBean{
	    
	    //非空验证
	    @NotNullValidate
	    private String property1;
	     
	    //value的范围验证
	    @RangeValueValidate(min="1", max="120")
	    private Integer property2;
	 
	    //集合的长度验证
	    @MinLengthValidate(length=1)
	    //list中的AnotherJavaBean对象会递归验证
	    private List<AnotherJavaBean> list;
	 
	}

	@ValidateBean
	class AnotherJavaBean{
	     
	    @NotNullValidate
	    private String subProperty1;
	     
	    @RangeValueValidate(min="1", max="120")
	    private Integer subProperty2;
	 
	}
	
	
	SmartValidate.validate(new JavaBean());
 

<h1>验证方法参数</h1>

1 在方法中加入验证规则注解

	public void method(
	           @ValidateArgument(
	                   notNull=@NotNullValidate,
	                   maxLength=@MaxLengthValidate(length=1)
	           )
	           /** 验证单个参数 **/ 
	           String argument,
	           /** 验证JavaBean **/
	           JavaBean javaBean) {
	    
	 }
 
2 配置

	<aop:config>
		<!-- pointcut 配置成需要拦截的路径 -->
		<aop:advisor pointcut="execution(*.*(..))" advice-ref="smartValidateInterceptor"/>
	</aop:config>
	<bean id="smartValidateInterceptor" class="com.smart.validate.interceptor.SmartValidateInterceptor" />
	
 
<h1>拓展</h1>

1 定义自己的验证注解

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CustomerValidate {
	    String message () default "";
	     
	    String name () default "";
	     
	}
 
2 实现自定义验证规则

	public class MatchCustomerValidate extends AbstractMatchValidate<CustomerValidate >{
	    @Override
	    public void validate(CustomerValidate t,
	            String fieldName,
	            Object value)
	            throws SmartValidateException {
	             
	        //your code
	    }
 
3 生效自定义验证规则

	ValidateRulePool.mount(CustomerValidate.class, new MatchCustomerValidate());
 

<h1>Maven依赖</h1>

	<dependency>
	    <groupId>com.smart.validate</groupId>
	    <artifactId>smart-validate</artifactId>
	    <version>1.1-SNAPSHOT</version>
	</dependency>
