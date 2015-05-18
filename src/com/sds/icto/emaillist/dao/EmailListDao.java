package com.sds.icto.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.emaillist.vo.EmailListVo;

@Repository
public class EmailListDao {
	private Connection getConnection()
		throws ClassNotFoundException, SQLException {
		Connection conn = null;
		//1. 드라이버 로딩
		Class.forName( "oracle.jdbc.driver.OracleDriver" );
		//2. connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");
		return conn;
	}
	
	public void insert( EmailListVo vo ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert into email_list values(email_list_no_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, vo.getFirstName() );
			pstmt.setString( 2, vo.getLastName() );
			pstmt.setString( 3, vo.getEmail() );
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null ) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delete( Long id ) {
	}
	
	public void delete() {
		Connection conn = null;
		Statement stmt  = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "delete from email_list";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<EmailListVo> fetchList() {
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from email_list";
			rs = stmt.executeQuery(sql);
			//4. 결과 처리
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String firstName = rs.getString( 2 );
				String lastName = rs.getString( 3 );
				String email = rs.getString( 4 );
				
				EmailListVo vo = new EmailListVo();
				vo.setNo( no );
				vo.setFirstName( firstName );
				vo.setLastName( lastName );
				vo.setEmail( email );
				
				list.add( vo );
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
