package forHeapSyn.verify;

import static common.Settings.SETTINGS_PATH;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;
import sushi.execution.evosuite.HeapSynParameters;

public class AATreeVerifier extends BasicLauncher {
	
	private final String METHOD_NAME;
	
	public AATreeVerifier(String methodName) {
		System.err.println("INFO - start to verify " + methodName + " of class " + METHOD_CLASS);
		this.METHOD_NAME = methodName;
	}
	
	private static final String PACKAGE				= "verify/aatree/";
	private static final String METHOD_CLASS		= PACKAGE + "AATree";
	private static final String METHOD_DESCRIPTOR	= "()V";
	
	@Override
	public void configureSushi(Options p) {
		super.configureSushi(p);
		p.setTargetMethod(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
		p.setCoverage(Coverage.UNSAFE);
		super.configureSushiScope(p, PACKAGE);
		p.setHEXFiles(SETTINGS_PATH.resolve("verify/aatree.jbse"));
	}
	
	@Override
	public HeapSynParameters getHeapSynParameters() throws ClassNotFoundException {
		final Class<?> targetClass = Class.forName(METHOD_CLASS.replace('/', '.'));
		HeapSynParameters p = new HeapSynParameters();
		p.setFieldFilter(name -> true);
		p.setTargetClass(targetClass);
		super.configureHeapSynScope(p, PACKAGE);
		super.configureHeapSynHEXFile(SETTINGS_PATH.resolve("verify/aatree.jbse"));
		return p;
	}
	
	public static void main(String[] args) {
		new AATreeVerifier("check$ordered").startSushi("AAtree.ordered.txt");
		new AATreeVerifier("check$wellLevel").startSushi("AAtree.wellLevel.txt");
		new AATreeVerifier("check$wellFormed").startSushi("AAtree.wellFormed.txt");
	}

}
