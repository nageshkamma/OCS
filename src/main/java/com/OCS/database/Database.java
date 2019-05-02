package com.OCS.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class Database
{
	public enum DBType
	{
		EDS, MBS, SYSDEV, MBSAPP
	}

	//@Test
	public static void executeQuery(String query, String desc, Database.DBType dbType, String env) throws Throwable
	{
		if (!query.isEmpty())
		{
			String connection = "";
			String dbUser = "";
			String dbPswd = "";

			switch (dbType)
			{
			case EDS:
			//	dbUser = USERNAME_EDS;
				if (env.equalsIgnoreCase("STAGING"))
				{
				//	dbPswd = PASSWORD_EDS_prod; // password will change for PROD
												// connection, not for STAGING
					connection = "jdbc:oracle:thin:@STAGING";
				}
				else if (env.equalsIgnoreCase("UAT"))
				{
				//	dbPswd = PASSWORD_EDS_uat;
					connection = "jdbc:oracle:thin:@EDS_UAT";
				}
				else if (env.equalsIgnoreCase("DEV"))
				{
				//	dbPswd = PASSWORD_EDS_dev;
					connection = "jdbc:oracle:thin:@EDS_DEV";
				}
				else
				{
					System.out.println("Error: Invalid connection!");
				}

				break;
			case MBS:
				//dbUser = USERNAME_MBS;
				if (env.equalsIgnoreCase("PROD"))
				{
				//	dbPswd = PASSWORD_MBS_prod; // password will change for PROD
												// connection, not for DEV/UAT
					connection = "jdbc:oracle:thin:@MBS_PROD";
				}
				else if (env.equalsIgnoreCase("UAT"))
				{
				//	dbPswd = PASSWORD_MBS_uat;
					connection = "jdbc:oracle:thin:@MBS_UAT";
				}
				else if (env.equalsIgnoreCase("DEV"))
				{
				//	dbPswd = PASSWORD_MBS_dev;
					connection = "jdbc:oracle:thin:@MBS_DEV";
				}
				else
				{
					System.out.println("Error: Invalid connection!");
				}

				break;
			case SYSDEV:
				//dbUser = USERNAME_SQL;
				if (env.equalsIgnoreCase("PROD"))
				{
					//dbPswd = PASSWORD_SQL_prod;
					try
					{
						// for local user - windows authentication
						connection = "jdbc:sqlserver://CSS-DB1\\CSS_SQL2:1186;Database=sysdev;integratedSecurity=true";
					}
					catch(Exception e)
					{
						// for server - sql account
						connection = String.format("jdbc:sqlserver://CSS-PRIME:1186;Database=sysdev;user=%s;password=%s",dbUser, dbPswd);
					}
				}
				else if (env.equalsIgnoreCase("UAT"))
				{
				//	dbPswd = PASSWORD_SQL_uat;
					try
					{
						// for local user - windows authentication
						connection = "jdbc:sqlserver://CST-DB1\\CST_SQL2:1186;Database=sysdev;integratedSecurity=true";
					}
					catch (Exception e)
					{
						// for server - sql account
						 connection = String.format("jdbc:sqlserver://CSD-PRIME:1186;Database=sysdev;user=%s;password=%s",dbUser, dbPswd); // for server
					}
				}
				else if (env.equalsIgnoreCase("DEV"))
				{
				//	dbPswd = PASSWORD_SQL_dev;
					try
					{
						// for local user - windows authentication
						connection = "jdbc:sqlserver://CSD-DB1\\CSD_SQL2:1186;Database=sysdev;integratedSecurity=true";
					}
					catch (Exception e)
					{
						// for server - sql account
						connection = String.format("jdbc:sqlserver://CSD-DB1\\CSD_SQL2:1186;Database=sysdev;user=%s;password=%s",dbUser, dbPswd); // for server
					}
				}
				else
				{
					System.out.println("Error: Invalid connection!");
				}
				break;
			case MBSAPP:
				//dbUser = USERNAME_SQL;
				if (env.equalsIgnoreCase("PROD"))
				{
				//	dbPswd = PASSWORD_SQL_prod;
					try
					{
						// for local user - windows authentication
						connection = "jdbc:sqlserver://CSS-DB1\\CSS_SQL2:1186;Database=MBSAPP;integratedSecurity=true";
					}
					catch (Exception e)
					{
						// for server - sql account
						connection = String.format("jdbc:sqlserver://CSS-DB1\\CSS_SQL2:1186;Database=MBSAPP;user=%s;password=%s",dbUser, dbPswd);
					}
				}
				else if (env.equalsIgnoreCase("UAT"))
				{
				//	dbPswd = PASSWORD_SQL_uat;
					try
					{
						// for local user - windows authentication
						connection = "jdbc:sqlserver://CST-DB1\\CST_SQL2:1186;Database=MBSAPP;integratedSecurity=true";
					}
					catch (Exception e)
					{
						// for server - sql account
						connection = String.format("jdbc:sqlserver://CST-DB1\\CST_SQL2:1186;Database=MBSAPP;user=%s;password=%s",dbUser, dbPswd); // for server
					}
				}
				else if (env.equalsIgnoreCase("DEV"))
				{
				//	dbPswd = PASSWORD_SQL_dev;
					try
					{
						// for local user - windows authentication
						connection = "jdbc:sqlserver://CSD-DB1\\CSD_SQL2:1186;Database=MBSAPP;integratedSecurity=true";
					}
					catch (Exception e)
					{
						// for server - sql account
						connection = String.format("jdbc:sqlserver://CSD-DB1\\CSD_SQL2:1186;Database=MBSAPP;user=%s;password=%s",dbUser, dbPswd); // for server\
					}
				}
				else
				{
					System.out.println("Error: Invalid connection!");
				}
				break;

			}

			// check to see if the db class exists; it not, log the error and
			// exit the function
			try
			{
				switch (dbType)
				{
				case EDS:
				case MBS:
					System.setProperty("oracle.net.tns_admin", "C:\\Oracle\\Ora11g\\network\\admin");
					Class.forName("oracle.jdbc.driver.OracleDriver");
					break;
				case SYSDEV:
				case MBSAPP:
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					break;
				}
				//logger.info("Connected to: " + dbType + " " + env);
			}
			catch (ClassNotFoundException ex)
			{
			//	Reporters.failureReport("dberror", "Class not loaded:" + ex.getMessage(), false);
				System.out.println("Error: unable to load driver class!");
			//	gStrErrMsg = ex.getMessage();
				return;
			}

			try (Connection conn = DriverManager.getConnection(connection, dbUser, dbPswd))
			{
				try (Statement stmt = conn.createStatement())
				{
					switch (dbType)
					{
					case EDS:
					case MBS:
						stmt.executeQuery(query);
						break;
					case SYSDEV:
					case MBSAPP:
						stmt.execute(query);
						break;
					}

					int updateCount = stmt.getUpdateCount();
					//logger.info("Affected number of rows: " + updateCount);
					//Reporters.successReport(desc, "Affected number of rows: " + updateCount);
				}
				catch (Exception e)
				{
					//logger.warning("Unable to execute query: " + desc + " " + e.getMessage());
					//Reporters.failureReport(desc + " ", e.getMessage(), false);
				}
			}
			catch (SQLException e)
			{
				System.out.println(String.format("dberror: %s", e.getMessage()));
				//logger.warning("DB connection Error: " + e.getMessage());
				throw new Exception("DB connection error: " + e.getMessage());
			}
		} //end if query not empty
	} // end of executeQuery
	
	public static void print (String query)
	{
		System.out.println(query);
	}
	
}
