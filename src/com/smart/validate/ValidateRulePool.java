package com.smart.validate;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.smart.validate.match.AbstractMatchValidate;
import com.smart.validate.match.MatchMaxCollectionsSizeValidate;
import com.smart.validate.match.MatchMaxLengthValidate;
import com.smart.validate.match.MatchMaxValueValidate;
import com.smart.validate.match.MatchMinCollectionsSizeValidate;
import com.smart.validate.match.MatchMinLengthValidate;
import com.smart.validate.match.MatchMinValueValidate;
import com.smart.validate.match.MatchNotNullValidate;
import com.smart.validate.match.MatchRangeCollectionsSizeValidate;
import com.smart.validate.match.MatchRangeLengthValidate;
import com.smart.validate.match.MatchRangeValueValidate;
import com.smart.validate.match.MatchRegexpValidate;
import com.smart.validate.rule.MaxCollectionsSizeValidate;
import com.smart.validate.rule.MaxLengthValidate;
import com.smart.validate.rule.MaxValueValidate;
import com.smart.validate.rule.MinCollectionsSizeValidate;
import com.smart.validate.rule.MinLengthValidate;
import com.smart.validate.rule.MinValueValidate;
import com.smart.validate.rule.NotNullValidate;
import com.smart.validate.rule.RangeCollectionsSizeValidate;
import com.smart.validate.rule.RangeLengthValidate;
import com.smart.validate.rule.RangeValueValidate;
import com.smart.validate.rule.RegexpValidate;


public class ValidateRulePool {

	private static final Map<Class<? extends Annotation>, AbstractMatchValidate<? extends Annotation>> matchValidatePool = new HashMap<>();

	static {
		
		mount(MaxLengthValidate.class, new MatchMaxLengthValidate());
		mount(MaxValueValidate.class, new MatchMaxValueValidate());
		mount(MinLengthValidate.class, new MatchMinLengthValidate());
		mount(MinValueValidate.class, new MatchMinValueValidate());
		mount(NotNullValidate.class, new MatchNotNullValidate());
		mount(RangeLengthValidate.class, new MatchRangeLengthValidate());
		mount(RangeValueValidate.class, new MatchRangeValueValidate());
		mount(RegexpValidate.class, new MatchRegexpValidate());
		mount(MaxCollectionsSizeValidate.class, new MatchMaxCollectionsSizeValidate());
		mount(MinCollectionsSizeValidate.class, new MatchMinCollectionsSizeValidate());
		mount(RangeCollectionsSizeValidate.class, new MatchRangeCollectionsSizeValidate());

	}
	private ValidateRulePool() {
		
	}
	
	public static void mount(Class<? extends Annotation> alias, AbstractMatchValidate<? extends Annotation> handler) {
		
		matchValidatePool.put(alias, handler);
		
	}
	
	public static AbstractMatchValidate<? extends Annotation> get(Class<? extends Annotation> alias) {

		return matchValidatePool.get(alias);
	}
}
