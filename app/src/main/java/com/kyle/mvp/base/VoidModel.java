package com.kyle.mvp.base;

/**
 * @Description描述: 忽略Model，考虑可能会存在只有View 和Presenter的情况，但是在建立关系的时候，必须表明依赖的
 * 类，该类是为了解决，在没有model的情况下，声明VoidModel类，在client 组合关系的时候，就不会创建Model的实例。
 * 在Presenter.getModel() == null
 * @Author作者: Kyle
 * @Date日期: 2017/11/8
 */
public class VoidModel implements IModel {
}
