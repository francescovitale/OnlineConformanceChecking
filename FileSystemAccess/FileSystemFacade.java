package FileSystemAccess;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import PMLogic.*;

public class FileSystemFacade {
	private PetriNetReader PNR;
	private BPMNReader BPMNR;
	private volatile static FileSystemFacade FSF = null;

	private FileSystemFacade(String Path, String ProcessName) {
		PNR = new PetriNetReader(Path, ProcessName);
		BPMNR = new BPMNReader(Path, ProcessName);
	}
	public static FileSystemFacade getInstance(String Path, String ProcessName) {
		if(FSF==null) {
			synchronized(FileSystemFacade.class) {
				if(FSF==null) {
					FSF = new FileSystemFacade(Path, ProcessName);
				}
			}
		}
		return FSF;
	}
	
	public PetriNet getPetriNet(ProcessModel PM, ArrayList<Activity> A) throws FileNotFoundException, IOException {
		PetriNet PN = new PetriNet(PM,PNR.getPlaces(),PNR.getTransitions(A), PNR.getPT(), PNR.getTP(),PNR.getMarking());
		return PN;
	}
	
	public BPMN getBPMN(ProcessModel PM, ArrayList<Activity> A) throws FileNotFoundException {
		BPMN BPMN_model = new BPMN();
		BPMN_model.setPM(PM);
		BPMN_model.setMR(BPMNR.getMergeRelations(A));
		BPMN_model.setSR(BPMNR.getSplitRelations(A));
		return BPMN_model;
	}
	
	/*public static void main(String[] args) throws FileNotFoundException, IOException {
		ProcessModel PM = new ProcessModel("trial_bpmn");
		/*FileSystemFacade MyFSF = FileSystemFacade.getInstance("C:\\Users\\aceep\\OneDrive\\Desktop\\File_progetto", "trial_bpmn");
		PetriNet PN = new PetriNet(MyFSF.getPetriNet(PM));
		for(int i=0;i<PN.getMarking().size(); i++)
			System.out.println(PN.getMarking().get(PN.getPlaces().get(i)));*/
		/*
		FileSystemFacade MyFSF = FileSystemFacade.getInstance("C:\\Users\\aceep\\OneDrive\\Desktop\\File_progetto", "trial_bpmn");
		BPMN BPMN_model = new BPMN(MyFSF.getBPMN(PM));
		for(int i=0;i<BPMN_model.getSR().size(); i++) {
			System.out.println("Precedent activity: "+BPMN_model.getSR().get(i).getPrecedentActivity().getName());
			System.out.print("Successive activities: ");
			for(int j=0;j<BPMN_model.getSR().get(i).getSuccessiveActivities().size();j++)
				System.out.print(BPMN_model.getSR().get(i).getSuccessiveActivities().get(j).getName()+" ");
			System.out.println();
		}
	}*/
	
}
