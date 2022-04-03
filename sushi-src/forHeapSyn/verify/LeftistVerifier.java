package forHeapSyn.verify;

import static common.Settings.SETTINGS_PATH;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;
import sushi.execution.evosuite.HeapSynParameters;

public class LeftistVerifier extends BasicLauncher {
	
	private final String METHOD_NAME;
	private final String METHOD_DESCRIPTOR;
	
	public LeftistVerifier(String methodName, String methodDesc) {
		System.out.println("INFO - start to verify " + methodName + " of class " + METHOD_CLASS);
		this.METHOD_NAME = methodName;
		this.METHOD_DESCRIPTOR = methodDesc;
	}
	
	private static final String PACKAGE			= "verify/leftistheap/";
	private static final String METHOD_CLASS	= PACKAGE + "LeftistHeap";
	
	@Override
	public void configureSushi(Options p) {
		super.configureSushi(p);
		p.setTargetMethod(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
		p.setCoverage(Coverage.UNSAFE);
		super.configureSushiScope(p, PACKAGE);
		p.setHEXFiles(SETTINGS_PATH.resolve("verify/leftist.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName(METHOD_CLASS.replace('/', '.'));
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, PACKAGE);
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("verify/leftist.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		new LeftistVerifier("check$ordered", "()V").startSushi();
		new LeftistVerifier("check$isLeftist", "()V").startSushi();
		new LeftistVerifier("check$wellFormed", "()V").startSushi();
		// new LeftistVerifier("check$nodeDisjoint", "(Lverify/leftistheap/LeftistHeap;)V").startSushi();
		// new LeftistVerifier("check$wellMerged", "(Lverify/leftistheap/LeftistHeap;)V").startSushi();
	}

}
