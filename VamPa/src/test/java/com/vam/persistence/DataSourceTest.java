package com.vam.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {

	//15,16오류나면 pom.xml에서 spring-test버전확인하기(현재 버전이랑 맞추기)
	//pom.xml에 입력할게 많음.
	
	@Autowired 
   private DataSource dataSource;
   
   @Autowired 
   private SqlSessionFactory sqlSessionFactory;
   
   @Test
   public void testConnection() throws Exception{
       
       try {
    		   Connection con = dataSource.getConnection();
               SqlSession session = sqlSessionFactory.openSession();
           
               System.out.println("con = " + con);
               System.out.println("session = " + session);
           
           } catch(Exception e) {
           
               e.printStackTrace();
           
           }
       
   }
   
}
