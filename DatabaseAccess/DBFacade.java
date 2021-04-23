package DatabaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import PMLogic.Activity;
import PMLogic.ActivityInstance;
import PMLogic.Event;
import PMLogic.ProcessInstance;
import PMLogic.ProcessModel;

public class DBFacade {
	private Connection Conn;
public
	DBFacade() {
		try {
			Conn = getConnection();
		} catch (SQLException e) {
			System.out.println("Couldn't connect to the database");
		}
	}

	public void closeConnection() throws SQLException {
		Conn.close();
	}
	private Connection getConnection() throws SQLException {
		 Connection conn = null;
		 Properties connectionProps = new Properties();
		 connectionProps.put("user", "root");
		 connectionProps.put("password", "root");
		 conn = DriverManager.getConnection(
			     "jdbc:" + "mysql" + "://" + "127.0.0.1" +":" + "3306" + "/",connectionProps);

		 return conn;
	}
	public ArrayList<ProcessModel> getProcessList() throws SQLException{
		
		ArrayList<ProcessModel> PM;
		
		PM = ProcessDAO.getProcessList(Conn);
		
		return PM;
		
		
	}
	public ArrayList<Activity> getActivityList(ArrayList<ProcessModel> PMList) throws SQLException{
		ArrayList<Activity> AList;
		
		AList = ActivityDAO.getActivityList(PMList, Conn);
		return AList;
	}
	
	public ArrayList<ActivityInstance> getActivityInstanceList(ArrayList<Activity> AList, ArrayList<ProcessInstance> PIList) throws SQLException{
		ArrayList<ActivityInstance> AI;
		
		AI = ActivityInstanceDAO.getActivityInstanceList(AList, PIList, Conn);
		
		
		return AI;
	}
	
	public ArrayList<Event> getEventList(ArrayList<ProcessInstance> PIList, ArrayList<ActivityInstance> AIList) throws SQLException{
		ArrayList<Event> EList;
		
		EList = EventDAO.getEventList(PIList,AIList,Conn);
	
		
		return EList;
	}
	public ArrayList<ProcessInstance> getProcessInstanceList(ArrayList<ProcessModel> PMList) throws SQLException{
		
		ArrayList<ProcessInstance> PIList;
		
		
		PIList = ProcessInstanceDAO.getProcessInstanceList(Conn, PMList);
		
		return PIList;
		
	}
	
	
	void insertEvent(int Timestamp, String Resource, int CaseID, String AIAct, String PMName) throws SQLException{
		
		insertProcessInstance(CaseID,PMName);
		
		
		insertActivityInstance(AIAct,CaseID);
		ArrayList<ProcessModel> PMList = getProcessList();
		ArrayList<Activity> AList = getActivityList(PMList);
		ArrayList<ProcessInstance> PIList = getProcessInstanceList(PMList);
		ArrayList<ActivityInstance> AIList = getActivityInstanceList(AList, PIList);
		int ActID = AIList.get(AIList.size()-1).getID();
		
		EventDAO.insertEvent(Timestamp,Resource,CaseID,ActID, Conn);
	};
	void insertProcess(String PMName) throws SQLException {
		ProcessDAO.insertProcessModel(PMName, Conn);
	};
	void insertActivity(String AName, String PMName) throws SQLException {
		ProcessModel PM = new ProcessModel(PMName);
		Activity A = new Activity(AName, PM);
		ActivityDAO.insertActivity(A, Conn);
	};
	void insertProcessInstance(int CaseID, String PM) throws SQLException {
		ArrayList<ProcessModel> PMList = getProcessList();
		ProcessInstanceDAO.insertProcessInstance(CaseID,PM,PMList, Conn);
	};
	void insertActivityInstance(String AIAct, int CaseID) throws SQLException {
		ActivityInstanceDAO.insertActivityInstance(AIAct, CaseID, Conn);
	};
	
	
	public static void main(String[] args) {
		DBFacade DBF = new DBFacade();
		try {
			
			DBF.insertEvent(0, "dmi", 3, "a", "trial_bpmn");
			
			ArrayList<ProcessModel> PMList = DBF.getProcessList();
			System.out.println("Processes:");
			for(int i=0; i<PMList.size(); i++)
				System.out.println(PMList.get(i).getName());
			System.out.println();
			ArrayList<Activity> AList = DBF.getActivityList(PMList);
			System.out.println("Activities:");
			for(int i=0; i<AList.size(); i++)
				System.out.println(AList.get(i).getName() + " " + AList.get(i).getPM().getName());
			System.out.println();
			ArrayList<ProcessInstance> PIList = DBF.getProcessInstanceList(PMList);
			System.out.println("Process instances:");
			for(int i=0; i<PIList.size(); i++)
				System.out.println(PIList.get(i).getCaseID() + " " + PIList.get(i).getP().getName());
			System.out.println();
			
			ArrayList<ActivityInstance> AIList = DBF.getActivityInstanceList(AList, PIList);
			System.out.println("Activity instances:");
			for(int i=0; i<AIList.size(); i++)
				System.out.println(AIList.get(i).getID() + " " + AIList.get(i).getPI().getCaseID() + " " + AIList.get(i).getA().getName());
			System.out.println();
			
			ArrayList<Event> EList = DBF.getEventList(PIList, AIList);
			System.out.println("Events:");
			for(int i=0;i<EList.size(); i++)
				System.out.println(EList.get(i).getID() + " " + EList.get(i).getT() + " " + EList.get(i).getResource() + " "+ EList.get(i).getPI().getCaseID() + " "+ EList.get(i).getAI().getID());
			System.out.println();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
