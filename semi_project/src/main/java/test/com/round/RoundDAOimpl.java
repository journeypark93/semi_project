package test.com.round;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.com.utils.DB_oracle;

public class RoundDAOimpl implements RoundDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public RoundDAOimpl() {
		try {
			Class.forName(DB_oracle.DRIVER_NAME);
			System.out.println("Driver Successed...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(RoundVO vo) {
		
		int flag = 0;

		try {
			conn = DriverManager.getConnection(DB_oracle.URL, DB_oracle.USER,
					DB_oracle.PASSWORD);

			pstmt = conn.prepareStatement(DB_oracle.ROUND_INSERT); // 쿼리문이 들어감.
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getCourse());
			pstmt.setInt(3, vo.getTotal_people());
			pstmt.setString(4, vo.getRound_date().toString());
			pstmt.setString(5, vo.getImage_url());

			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // close가 있어서 finally 해줘야ㅕ 됨.
			if (rs != null) {
				try {
					rs.close(); // 나중에 쓴걸 먼저 닫는다.
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return flag;
	}

	@Override
	public int update(RoundVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoundVO> selectAll() {
		List<RoundVO> vos = new ArrayList<RoundVO>();

		try {
			conn = DriverManager.getConnection(DB_oracle.URL, DB_oracle.USER, DB_oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(DB_oracle.ROUND_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				RoundVO vo = new RoundVO();
				vo.setRound_id(rs.getLong("round_id"));
				vo.setName(rs.getString("name"));
				vo.setCourse(rs.getString("course"));
				vo.setTotal_people(rs.getInt("total_people"));
				vo.setRound_date(rs.getString("round_date"));
				vo.setImage_url(rs.getString("image_url"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vos;
	}

	@Override
	public List<RoundVO> searchList(String searchKey, String searchWord) {
		List<RoundVO> list = new ArrayList<RoundVO>();
		searchKey = "round";
		try {
			conn = DriverManager.getConnection(DB_oracle.URL, DB_oracle.USER, DB_oracle.PASSWORD);

			if (searchKey.equals("round")) {
				pstmt = conn.prepareStatement(DB_oracle.ROUND_SEARCH_LIST_NAME); // TEL 이 넘어오면
			}

			pstmt.setString(1, "%" + searchWord + "%"); // 여기에 물음표를 넣어주어야 하네.

			rs = pstmt.executeQuery();

			// rs.next() 읽어올 것이 있으면
			while (rs.next()) {
				RoundVO vo = new RoundVO();

				vo.setRound_id(rs.getLong("round_id"));
				vo.setName(rs.getString("name"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // close가 있어서 finally 해줘야ㅕ 됨.
			if (rs != null) {
				try {
					rs.close(); // 나중에 쓴걸 먼저 닫는다.
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally
		System.out.println(list);
		System.out.println("서치확인");
		return list;
	}
	
	@Override
	public List<RoundVO> mySelectAll(String member_id) {
		System.out.println("mySelectAll()...");
		List<RoundVO> vos = new ArrayList<RoundVO>();
		try {
			conn = DriverManager.getConnection(DB_oracle.URL, DB_oracle.USER, DB_oracle.PASSWORD);
			System.out.println("Conn Successed...");
			pstmt = conn.prepareStatement(DB_oracle.SQL_MY_ROUND_SELECT_ALL);
			pstmt.setLong(1, Long.parseLong(member_id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoundVO vo = new RoundVO();
				vo.setRound_id(rs.getLong("round_id"));
				vo.setName(rs.getString("name"));
				vo.setRound_date(rs.getString("round_date"));
				vo.setCourse(rs.getString("course"));
				vo.setTotal_people(rs.getInt("total_people"));
				vo.setCurrent_people(rs.getInt("current_people"));
				vo.setImage_url(rs.getString("image_url"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vos;
	}

	@Override
	public RoundVO selectOne(RoundVO vo) {
		RoundVO vo2 = new RoundVO();
		try {
			conn = DriverManager.getConnection(DB_oracle.URL, DB_oracle.USER, DB_oracle.PASSWORD);

			pstmt = conn.prepareStatement(DB_oracle.ROUND_SELECT_ONE); // 쿼리문이 들어감.

			pstmt.setLong(1, vo.getRound_id());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2.setRound_id(rs.getLong("round_id"));
				vo2.setName(rs.getString("name"));
				vo2.setCourse(rs.getString("course"));
				vo2.setTotal_people(rs.getInt("total_people"));
				vo2.setRound_date(rs.getString("round_date"));
				vo2.setImage_url(rs.getString("image_url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // close가 있어서 finally 해줘야됨.
			if (rs != null) {
				try {
					rs.close(); // 나중에 쓴걸 먼저 닫는다.
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return vo2;
	}

}
