package utilities;

import java.util.ArrayList;

import joint.JointManager;
import core.JointMassDistribution;
import core.MassDistribution;
import core.Source;
import exception.JointNotPossibleException;
import exception.MassDistributionNotValidException;
import gui.NewSwingApp;

public class Main {

	/**
	 * @param args
	 * @throws CloneNotSupportedException
	 * @throws MassDistributionNotValidException
	 * @throws JointNotPossibleException
	 */
	public static void main(String[] args) throws CloneNotSupportedException,
			JointNotPossibleException, MassDistributionNotValidException {

		// String fileName = "Hypothesis.txt";
		// FrameOfDiscernment frameOfDiscernment = new FrameOfDiscernment(
		// (new HypothesisFileHandler()).readHypothesis(fileName));

		ArrayList<MassDistribution> masses = new ArrayList<MassDistribution>();

		Source feedback = new Source("Feedback");
		Source uddi = new Source("UDDI");
		Source trustAuthority = new Source("TrustAthority");

		MassDistribution feedbackMass = feedback
				.getMassDistribution("feedback.txt");
		feedbackMass.setSource(feedback);

		MassDistribution uddiMass = uddi.getMassDistribution("uddi.txt");
		uddiMass.setSource(uddi);

		MassDistribution trustAuthorityMass = trustAuthority
				.getMassDistribution("trustAuthority.txt");
		trustAuthorityMass.setSource(trustAuthority);

		masses.add(feedbackMass);
		masses.add(uddiMass);
		masses.add(trustAuthorityMass);

		ArrayList<MassDistribution> input = getNewArrayList(masses);

		JointMassDistribution demDistribution = JointManager
				.dempsterJoint(input);
		demDistribution = JointMassDistribution.order(demDistribution);

		JointMassDistribution yagerDistribution = JointManager
				.yagerJoint(input);

		// yagerDistribution = JointMassDistribution.order(yagerDistribution);

		// input = getNewArrayList(masses);
		JointMassDistribution averageDistribution = JointManager
				.averageJoint(input);
		averageDistribution = JointMassDistribution.order(averageDistribution);

		// input = getNewArrayList(masses);
		JointMassDistribution distanceDistribution = JointManager
				.distanceEvidenceJoint(input);
		distanceDistribution = JointMassDistribution
				.order(distanceDistribution);

		ArrayList<MassDistribution> results = new ArrayList<MassDistribution>();

		results.add(demDistribution);
		results.add(yagerDistribution);
		results.add(distanceDistribution);
		results.add(averageDistribution);

		showresults(results, getNewArrayList(masses));

	}

	private static void showresults(ArrayList<MassDistribution> results,
			ArrayList<MassDistribution> input) {
		NewSwingApp frame = new NewSwingApp();
		frame.setJointResults(results);
		frame.setInput(input);
		frame.initGUI();

	}

	private static ArrayList<MassDistribution> getNewArrayList(
			ArrayList<MassDistribution> oldArray)
			throws CloneNotSupportedException {
		ArrayList<MassDistribution> newArray = new ArrayList<MassDistribution>();

		for (int i = 0; i < oldArray.size(); i++) {
			MassDistribution m = ((MassDistribution) oldArray.get(i));
			MassDistribution newMAss = (MassDistribution) m.clone();
			newArray.add(newMAss);
		}
		return newArray;
	}
}
