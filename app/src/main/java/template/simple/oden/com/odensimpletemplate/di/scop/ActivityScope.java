package template.simple.oden.com.odensimpletemplate.di.scop;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {}