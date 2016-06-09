package com.demo.spring.transaction.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Club;
import com.demo.spring.orm.ClubDAO;
import com.demo.spring.orm.EmployeeDAO;
import com.demo.spring.transaction.ClubService;

@Service
public class ClubServiceImpl implements ClubService{

	@Resource
	private EmployeeDAO employeeDao;
	
	@Resource
	private ClubDAO clubDao;
	
	/*
	@Transactional 
		只能用在接口，类， 接口方法和类的public方法；在类上声明等同于在类中所有public方法上声明；
		因为注解不能继承，所以不要用在接口上；
		方法上的注解大于类上的；
		参数默认值：
			propagation：PROPAGATION_REQUIRED
			isolation：ISOLATION_DEFAULT
			readOnly：false， 如果为true，则只读，但不能插入/更新/删除 
			timeout: 依赖事务本身，单位秒
			rollbackFor： 回滚异常类，必须是Throwable的子类的《实例》，遇到时 必须 进行回滚。
						   默认情况下checked exceptions不进行回滚（例如IOException），仅unchecked exceptions（即RuntimeException的子类）才进行事务回滚；
			rollbackForClassname： 类似rollbackFor， 异常类名
			noRollbackFor： rollbackFor的反例， 遇到时必须不回滚， 一般是程序抛出的自定义业务异常
		传播级别：
			Propagation.REQUIRED： 如果有事务,那么加入事务,没有的话新建一个(默认)
			Propagation.REQUIRES_NEW： 不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
			Propagation.MANDATORY： 必须在一个已有的事务中执行,否则抛出异常
			Propagation.NEVER：必须在一个没有的事务中执行,否则抛出异常(与Propagation.MANDATORY相反)
			Propagation.NOT_SUPPORTED：容器不为这个方法开启事务，只查不写，一般与readOnly=true一起使用
			Propagation.SUPPORTS： 如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务
			Propagation.NESTED： other失败了不会影响 本类的修改提交成功，本类update的失败,other也失败
				public void methodName(){
			       // 本类的修改方法 1
			       update();
			       // 调用其他类的修改方法
			       otherBean.update();
			       // 本类的修改方法 2
			       update();
			    }
			    
	*/
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, 
			isolation=Isolation.READ_COMMITTED) 
			//noRollbackFor={BusinessException.class},
	public void addClub(Club club) {
		clubDao.addClub(club);
		Club newClub = clubDao.getClubByName(club.getName());
		employeeDao.joinClub(5, newClub.getId());
		
	}
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED) 
	public void deleteClubById(int clubId){
		clubDao.deleteMembershipByClubId(clubId);
		clubDao.deleteClub(clubId);
	}

}
