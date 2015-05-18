package com.sds.icto.emaillist.test;

import java.util.List;

import com.sds.icto.emaillist.dao.EmailListDao;
import com.sds.icto.emaillist.vo.EmailListVo;

public class TestEmailListDao {

	public static void main(String[] args) {
		try {
			//0.  deleteTest
			//testDelete();
			//1. 인서트 테스트
			testInsert();
			//2. fectList 테스트
			testFetchList();
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	public static void testDelete() 
		throws Exception{
		EmailListDao dao = new EmailListDao();
		dao.delete();
	}
	
	public static void testFetchList() 
		throws Exception{
		EmailListDao dao = new EmailListDao();
		List<EmailListVo> list = dao.fetchList();
		
		for( EmailListVo vo : list ) {
			System.out.print( vo.getNo() + ":" );
			System.out.print( vo.getFirstName() + ":" );
			System.out.print( vo.getLastName() + ":" );
			System.out.print( vo.getEmail() );
			System.out.print( "\n" );
		}
	}

	public static void testInsert() 
		throws Exception {
		EmailListDao dao = new EmailListDao();

		EmailListVo vo = new EmailListVo();
		vo.setFirstName( "Ahn" );
		vo.setLastName( "D.Hyuck" );
		vo.setEmail( "kickscar@sunnyvale.co.kr" );
		dao.insert( vo );
		
		vo = new EmailListVo();
		vo.setFirstName( "Ahn2" );
		vo.setLastName( "D.Hyuck2" );
		vo.setEmail( "kickscar@gmail.com" );
		
		dao.insert( vo );
		
	}
}
