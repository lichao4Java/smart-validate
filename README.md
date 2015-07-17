# smart-validate
This is a Automatic Verification Java Bean Framework

Can be used to handle simple and complex verification, has provided 11 kinds of predefined validation rules, and can freely expand the custom validation rules

## A simple JavaBean validate example
i have a method that require id can not be NULL, you only need 3 steps with smart-validate.

1.write a java bean 

    public class GetById {
	private String id;
	public GetById() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    }


2.Add com.smart.validate.Validate annotation on Class, Add com.smart.validate.rule.NotNullValidate annotation on id field it looks like:

    import com.smart.validate.Validate;
    import com.smart.validate.rule.NotNullValidate;

    @Validate
    public class GetById {
	@NotNullValidate
	private String id;
	public GetById() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    }
3.Now we can run it!

    SmartValidate.validate(new GetById());

## A complex JavaBean validate example
we simulate a request for a multiple levels structure submission

1 Define a simple multiple levels structure with smart-validate annotation 

    @Validate
    public class MakeOrderRequest {

	@NotNullValidate
	private String userId;

	@NotNullValidate
	private List<Sku> skus;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Sku> getSkus() {
		return skus;
	}
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}
    }

    @Validate
    public class Sku {

	@NotNullValidate
	private String skuId;
	
	@MinValueValidate(value="1")
	private Integer buyNum;
	
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
    }

all property of MakeOrderRequest will be validated and every each Sku's property also will be validated

2.Now we can run it!

    SmartValidate.validate(new MakeOrderRequest());
    
