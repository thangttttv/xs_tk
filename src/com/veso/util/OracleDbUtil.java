/*
 * @(#)OracleDbUtil.java        
 *
 * Copyright (c) 2006 Inet Media Co. Ltd 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of iNET Media, Inc.
 * ("Confidential Information").  You shall not disclose such Confidential Information 
 * and shall use it only in accordance with the terms of the license agreement you 
 * entered into with iNET Media.
 */
package com.veso.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author thangtt This is description of class, which used to get current value
 *         of sequence
 * 
 */
public class OracleDbUtil {

	public static Object getCurrentValue(Connection connection, String sequenceName)
			throws SQLException {
		Object rtn = null;

		final String sql = "SELECT " + sequenceName + ".currval FROM DUAL";

		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);

			ResultSet rset = pstmt.executeQuery();
			if (rset.next()) {
				rtn = rset.getObject(1);
			}
		} catch (SQLException exp) {
			throw exp;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return rtn;
	}

}
