package DatabaseAccess;

import java.util.ArrayList;

import PMLogic.Activity;
import PMLogic.ActivityInstance;
import PMLogic.Event;
import PMLogic.ProcessInstance;
import PMLogic.ProcessModel;

public class DBFacade {
public
	DBFacade() {}
	public ArrayList<ProcessModel> getProcessList(){
		/*ProcessModel PM = new ProcessModel(ProcessName);
		ArrayList<Activity> ActivityList = getActivityList(ProcessName);
		PM.setAL(ActivityList);
		ArrayList<ProcessInstance> ProcessInstanceList = getProcessInstanceList();
		ArrayList<ProcessInstance> ProcessInstanceListForPM = new ArrayList<ProcessInstance>();
		boolean found = true;
		while(!found) {
			found = false;
			for(int i=0;i<ProcessInstanceList.size();i++)
				if(ProcessInstanceList.get(i).getP().getName().equals(ProcessName)) {
					found = true;
					ProcessInstanceListForPM.add(ProcessInstanceList.get(i));
				}		
		}
		PM.setPI(ProcessInstanceListForPM);
		return PM;*/
		
		ArrayList<ProcessModel> PM = new ArrayList<ProcessModel>();
		
		// fetch the process models from the DB
		
		PM.add(new ProcessModel("trial_bpmn")); // fake the fetch
		
		return PM;
		
		
	}
	public ArrayList<Activity> getActivityList(ArrayList<ProcessModel> P){
		ArrayList<Activity> A = new ArrayList<Activity>();
		
		// fetch the activities from the DB
		
		// for each activity find, among all the process models, the one that has the same name as the foreign key has
		
		A.add(new Activity("a",P.get(0))); // fake the procedure
		A.add(new Activity("b",P.get(0))); // fake the procedure
		A.add(new Activity("c",P.get(0))); // fake the procedure
		A.add(new Activity("d",P.get(0))); // fake the procedure
		A.add(new Activity("e",P.get(0))); // fake the procedure
		A.add(new Activity("f",P.get(0))); // fake the procedure
		A.add(new Activity("g",P.get(0))); // fake the procedure
		A.add(new Activity("a_merge_b_c",P.get(0))); // fake the procedure
		A.add(new Activity("b_split_a",P.get(0))); // fake the procedure
		A.add(new Activity("c_split_a",P.get(0))); // fake the procedure
		A.add(new Activity("b_merge_d_e",P.get(0))); // fake the procedure
		A.add(new Activity("c_merge_g",P.get(0))); // fake the procedure
		A.add(new Activity("d_split_b",P.get(0))); // fake the procedure
		A.add(new Activity("e_split_b",P.get(0))); // fake the procedure
		A.add(new Activity("d_merge_f",P.get(0))); // fake the procedure
		A.add(new Activity("e_merge_f",P.get(0))); // fake the procedure
		A.add(new Activity("d_e_split_f",P.get(0))); // fake the procedure
		A.add(new Activity("f_merge_g",P.get(0))); // fake the procedure
		A.add(new Activity("f_c_split_g",P.get(0))); // fake the procedure
		
		
		
		
		
		return A;
	}
	
	public ArrayList<ActivityInstance> getActivityInstanceList(ArrayList<Activity> A, ArrayList<ProcessInstance> PI){
		ArrayList<ActivityInstance> AI = new ArrayList<ActivityInstance>();
		
		// fetch the activity instances from the DB
		
		// for each activity instance find, among all the activities, the one that has the same name as the foreign key has
		// for each activity instance find, among all the process instances, the one that has the same CaseID as the foreign key has
		
		AI.add(new ActivityInstance(0,PI.get(0), A.get(0))); // fake the procedure
		AI.add(new ActivityInstance(1,PI.get(0), A.get(1))); // fake the procedure
		AI.add(new ActivityInstance(2,PI.get(0), A.get(2))); // fake the procedure
		AI.add(new ActivityInstance(3,PI.get(0), A.get(3))); // fake the procedure
		AI.add(new ActivityInstance(4,PI.get(0), A.get(4))); // fake the procedure
		AI.add(new ActivityInstance(5,PI.get(0), A.get(5))); // fake the procedure
		AI.add(new ActivityInstance(6,PI.get(0), A.get(6))); // fake the procedure
		
		AI.add(new ActivityInstance(6,PI.get(1), A.get(0))); // fake the procedure
		AI.add(new ActivityInstance(7,PI.get(1), A.get(1))); // fake the procedure
		AI.add(new ActivityInstance(8,PI.get(0), A.get(2))); // fake the procedure
		AI.add(new ActivityInstance(9,PI.get(0), A.get(3))); // fake the procedure
		AI.add(new ActivityInstance(10,PI.get(0), A.get(4))); // fake the procedure
		AI.add(new ActivityInstance(11,PI.get(0), A.get(5))); // fake the procedure
		AI.add(new ActivityInstance(12,PI.get(0), A.get(6))); // fake the procedure
		
		return AI;
	}
	
	public ArrayList<Event> getEventList(ArrayList<ProcessInstance> PI, ArrayList<ActivityInstance> AI){
		ArrayList<Event> E = new ArrayList<Event>();
		
		// fetch the events from the DB
		
		// for each event find, among all the process instances, the one that has the same CaseID as the foreign key has
		// for each event find, among all the activity instances, the one that has the same ID as the foreign key has
		
		E.add(new Event(0,0,"dmi",PI.get(0),AI.get(0))); // fake the procedure
		E.add(new Event(1,1,"dmi",PI.get(0),AI.get(1))); // fake the procedure
		E.add(new Event(2,2,"dmi",PI.get(0),AI.get(2))); // fake the procedure
		E.add(new Event(3,3,"dmi",PI.get(0),AI.get(3))); // fake the procedure
		E.add(new Event(4,4,"dmi",PI.get(0),AI.get(4))); // fake the procedure
		E.add(new Event(5,5,"dmi",PI.get(0),AI.get(5))); // fake the procedure
		E.add(new Event(6,6,"dmi",PI.get(0),AI.get(6))); // fake the procedure
		
		E.add(new Event(6,0,"dmi",PI.get(1),AI.get(7))); // fake the procedure
		E.add(new Event(7,3,"dmi",PI.get(1),AI.get(8))); // fake the procedure
		E.add(new Event(8,2,"dmi",PI.get(1),AI.get(9))); // fake the procedure
		E.add(new Event(9,1,"dmi",PI.get(1),AI.get(10))); // fake the procedure
		E.add(new Event(10,5,"dmi",PI.get(1),AI.get(11))); // fake the procedure
		E.add(new Event(11,4,"dmi",PI.get(1),AI.get(12))); // fake the procedure
		E.add(new Event(12,6,"dmi",PI.get(1),AI.get(13))); // fake the procedure
		
		return E;
	}
	public ArrayList<ProcessInstance> getProcessInstanceList(ArrayList<ProcessModel> PM){
		
		ArrayList<ProcessInstance> PI = new ArrayList<ProcessInstance>();
		
		// fetch the process instances from the DB
		
		// for each process instance find, among all the process models, the one that has the same name as the foreign key has
		
		PI.add(new ProcessInstance(0, PM.get(0))); // fake the procedure
		PI.add(new ProcessInstance(1, PM.get(0))); // fake the procedure
		
		return PI;
		
	}
	
	
	void insertEvent(Event E){};
}
